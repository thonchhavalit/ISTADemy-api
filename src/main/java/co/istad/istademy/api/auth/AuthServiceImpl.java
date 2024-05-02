package co.istad.istademy.api.auth;

import co.istad.istademy.api.auth.web.*;
import co.istad.istademy.api.user.Role;
import co.istad.istademy.api.user.User;
import co.istad.istademy.api.user.UserMapStruct;
import co.istad.istademy.util.MailUtil;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.security.oauth2.server.resource.authentication.BearerTokenAuthenticationToken;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationProvider;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import org.springframework.web.server.ResponseStatusException;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthServiceImpl implements AuthService {

    private final AuthMapper authMapper;
    private final UserMapStruct userMapStruct;
    private final PasswordEncoder encoder;
    private final MailUtil mailUtil;

    private final DaoAuthenticationProvider daoAuthenticationProvider;
    private final JwtAuthenticationProvider jwtAuthenticationProvider;

    private final JwtEncoder jwtAccessTokenEncoder;
    private  JwtEncoder jwtRefreshTokenEncoder;
    private final AuthMapStruct authMapStruct;


    @Autowired
    public void setJwtRefreshTokenEncoder(@Qualifier("jwtRefreshTokenEncoder") JwtEncoder jwtRefreshTokenEncoder) {
        this.jwtRefreshTokenEncoder = jwtRefreshTokenEncoder;
    }

    @Value("${spring.mail.username}")
    private String appMail;

    @Override
    public AuthDto refreshToken(TokenDto tokenDto) {

        Authentication authentication = new BearerTokenAuthenticationToken(tokenDto.refreshToken());
        authentication = jwtAuthenticationProvider.authenticate(authentication);

        Instant now = Instant.now();

        Jwt jwt = (Jwt) authentication.getCredentials();

        System.out.println(jwt.getSubject());
        System.out.println(jwt.getClaims());
        System.out.println(jwt.getClaimAsString("scope"));

        JwtClaimsSet jwtAccessTokenClaimSet = JwtClaimsSet.builder()
                .issuer("self")
                .issuedAt(now)
                .subject(jwt.getSubject())
                .expiresAt(now.plus(1, ChronoUnit.SECONDS))
                .claim("scope", jwt.getClaimAsString("scope"))
                .build();

        String accessToken = jwtAccessTokenEncoder.encode(
                JwtEncoderParameters.from(jwtAccessTokenClaimSet)
        ).getTokenValue();

        return new AuthDto("Bearer",
                accessToken,
                tokenDto.refreshToken());
    }

    @Override
    public AuthDto login(LogInDto logInDto) {

        Authentication authentication = new UsernamePasswordAuthenticationToken(logInDto.email(), logInDto.password());

        authentication = daoAuthenticationProvider.authenticate(authentication);

        // Create time now
        Instant now = Instant.now();

        // Define scope
        String scope = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .filter(auth -> !auth.startsWith("ROLE_"))
                .collect(Collectors.joining(" "));

        JwtClaimsSet jwtClaimsSet = JwtClaimsSet.builder()
                .issuer("self")
                .issuedAt(now)
                .subject(authentication.getName())
                .expiresAt(now.plus(1, ChronoUnit.HOURS))
                .claim("scope", scope)
                .build();

        JwtClaimsSet jwtRefreshClaimsSet = JwtClaimsSet.builder()
                .issuer("self")
                .issuedAt(now)
                .subject(authentication.getName())
                .expiresAt(now.plus(1, ChronoUnit.HOURS))
                .claim("scope", scope)
                .build();

        String accessToken = jwtAccessTokenEncoder.encode(
                JwtEncoderParameters.from(jwtClaimsSet)
        ).getTokenValue();

        String refreshToken = jwtRefreshTokenEncoder.encode(
                JwtEncoderParameters.from(jwtRefreshClaimsSet)
        ).getTokenValue();

        return new AuthDto("Bearer",
                accessToken,
                refreshToken);
    }

    @Transactional
    @Override
    public void register(RegisterDto registerDto) {

        User user = userMapStruct.registerDtoToUser(registerDto);
        user.setUuid(UUID.randomUUID().toString());
        user.setIsVerified(false);
        user.setPassword(encoder.encode(user.getPassword()));

        log.info("User: {}", user.getEmail());
        log.info("Username: {}", user.getUsername());

        if (authMapper.register(user)) {
            // Create user role
            for (Integer role : registerDto.roleIds()) {
                authMapper.createUserRole(user.getId(), role);
            }
        }
    }

    @Override
    public AuthVerified verify(String email, String verifiedCode) {

        User authVerified = authMapper.selectByEmail(email).orElseThrow(()
                -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Email has not been found"));

        verifiedCode = UUID.randomUUID().toString();

        if (authMapper.updateVerifiedCode(email, verifiedCode)) {
            authVerified.setVerifiedCode(verifiedCode);
            log.info("VerifiedCode :{}", authVerified.getVerifiedCode());
        } else {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                    "User cannot be verified");
        }

        MailUtil.Meta<?> meta = MailUtil.Meta.builder()
                .to(email)
                .from(appMail)
                .subject("Account Verification")
                .templateUrl("verify")
                .data(authVerified)
                .build();

        try {
            mailUtil.send(meta);
            return new AuthVerified(email,verifiedCode);
        } catch (MessagingException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                    e.getMessage());
        }

    }


    @Override
    public void checkVerify(String email, String verifiedCode) {

        User user = authMapper.selectByEmailAndVerifiedCode(email, verifiedCode)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User is not exist in the database"));

        if (!user.getIsVerified()) {
            authMapper.verify(email, verifiedCode);
        }

    }

    @Override
    public ResponseProfileDto getAuthMe(Authentication authentication) {
        User user = authMapper.loadUserByUsername(authentication.getName()).orElseThrow(()
                -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Your profile is unavailable!"));

        log.info("User: {}", user);

        return userMapStruct.toResponseProfileDto(user);
    }


    @Override
    public void registerWithGoogle(UserWithGoogleDto userWithGoogleDto){
        try {
            User user = authMapStruct.createdUserWithGoogle(userWithGoogleDto);
            String uuid = UUID.randomUUID().toString();
            user.setIsVerified(true);
            user.setEmail(userWithGoogleDto.email());
            user.setUsername(userWithGoogleDto.username());
            user.setUuid(uuid);
            user.setCreatedAt(new Timestamp(System.currentTimeMillis()));
            Role role = authMapper.findRoleByName("USER");
            log.info("user register with google {}",user.getEmail());
            if (authMapper.createUserOauth2(user)){
                log.info("USER, {}", user);
                authMapper.createUserRole(user.getId(), role.getId());
            }
        }catch (Exception ex){
            ex.printStackTrace();
            System.out.println("Exception Occur! Failed to register with google!"+ex.getMessage());
        }
    }

        @Override
        public UserDto getMeFromGoogle(String email, String username) {

            return getUserDto(email, username);
        }

    @Override
    public void registerWithGithub(UserWithGithubDto userWithGithubDto) {
        try {

            User user = authMapStruct.createdUserWithGithub(userWithGithubDto);
            user.setEmail(userWithGithubDto.email());
            user.setUsername(userWithGithubDto.username());
            user.setIsVerified(true);
            user.setCreatedAt(new Timestamp(System.currentTimeMillis()));
            String uuid = UUID.randomUUID().toString();
            user.setUuid(uuid);
            Role role = authMapper.findRoleByName("USER");
            log.info("user register with github {}",user.getEmail());
            if (authMapper.createUserOauth2(user)){
                log.info("USER, {}", user);
                authMapper.createUserRole(user.getId(), role.getId());
            }
        }catch (Exception ex){
            ex.printStackTrace();
            System.out.println("Exception Occur! Failed to register with github!"+ex.getMessage());
        }
    }

    @Override
    public UserDto getMeFromGithub(String email, String username) {

        return getUserDto(email, username);
    }

    private UserDto getUserDto(String email, String username) {
        try {
            UserDto user = authMapper.findUserGoogle(email, username);

            if (!ObjectUtils.isEmpty(user)) {
                return user;
            } else {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                        String.format("User cannot find with %s email %s and %s username %s",
                                "email", email, "username", username));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,"Fail to get email and username user with google");
        }
    }


}
