package co.istad.istademy.api.auth.web;

import co.istad.istademy.api.auth.AuthMapper;
import co.istad.istademy.api.auth.AuthService;
import co.istad.istademy.api.user.User;
import co.istad.istademy.base.BaseRest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@Slf4j
public class AuthRestController {

    private final AuthService authService;
    private final JavaMailSender javaMailSender;
    private final AuthMapper authMapper;

    @PostMapping("/refresh")
    public BaseRest<?> refreshToken(@RequestBody TokenDto tokenDto) {

        AuthDto authDto = authService.refreshToken(tokenDto);

        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .message("Token has been refreshed")
                .timestamp(LocalDateTime.now())
                .data(authDto)
                .build();
    }

    @PostMapping("/login")
    public BaseRest<?> login(@Valid @RequestBody LogInDto logInDto) {
        AuthDto authDto = authService.login(logInDto);
        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .message("You have been logged in successfully")
                .timestamp(LocalDateTime.now())
                .data(authDto)
                .build();
    }

    @PostMapping("/register")
    public BaseRest<?> register(@Valid @RequestBody RegisterDto registerDto) {
        authService.register(registerDto);
        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .message("You have been registered successfully")
                .timestamp(LocalDateTime.now())
                .data(registerDto.email())
                .build();
    }

    @PostMapping("/verify")
    public BaseRest<?> verify(@RequestParam String email ,String verifiedCode) {
        AuthVerified authVerified = authService.verify(email,verifiedCode);
        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .message("Please check email and verify")
                .timestamp(LocalDateTime.now())
                .data(authVerified)
                .build();
    }

    @GetMapping("/check-verify")
    public BaseRest<?> checkVerify(@RequestParam String email,
                                   @RequestParam String verifiedCode) {

        log.info("Email: {}", email);
        log.info("Verified Code: {}", verifiedCode);

        authService.checkVerify(email, verifiedCode);

        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .message("You have been verified successfully")
                .timestamp(LocalDateTime.now())
                .data(email)
                .build();
    }

    @GetMapping("/me")
    public BaseRest<?> getAuthProfile(Authentication authentication) {

        ResponseProfileDto responseProfileDto = authService.getAuthMe(authentication);

        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .message("You have retrieved profile successfully.")
                .timestamp(LocalDateTime.now())
                .data(responseProfileDto)
                .build();
    }


    @GetMapping("/oauth/{uuid}")
    @PreAuthorize("#user.uuid == #uuid")
    public BaseRest<?> getCurrentUser(@AuthenticationPrincipal User user, @PathVariable String uuid){
        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .message("All current users have been retrieved successfully.")
                .data(authMapper.findByUuid(uuid))
                .build();
    }


    @PostMapping("/send-verification-code")
    public ResponseEntity<Map<String, String>> sendVerificationCode(@RequestBody String email) {
        // Generate a verification code
        String code = generateVerificationCode();

        // Send an email with the verification code
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("Email Verification Code");
        message.setText("Your verification code is: " + code);
        javaMailSender.send(message);

        // Return the verification code and email address in the response body
        Map<String, String> responseBody = new HashMap<>();
        responseBody.put("email", email);
        responseBody.put("code", code);

        return ResponseEntity.ok().body(responseBody);
    }

    private String generateVerificationCode() {
        // Generate a random 6-digit code
        return String.format("%06d", new Random().nextInt(999999));
    }

    @PostMapping("/register/google")
    public BaseRest<?> registerWithGoogle(@RequestBody UserWithGoogleDto userWithGoogleDto) {
        authService.registerWithGoogle(userWithGoogleDto);
        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .data(userWithGoogleDto)
                .timestamp(LocalDateTime.now())
                .message("Successfully register with google.")
                .build();
    }

    @GetMapping("/me/google/{email}/{username}")
    public BaseRest<?>  getMeFromGoogle(@PathVariable String email, @PathVariable String username){
        UserDto userDto = authService.getMeFromGoogle(email, username);
        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .timestamp(LocalDateTime.now())
                .message("Successfully get email and username from user with google.")
                .data(userDto)
                .build();
    }

    @PostMapping("/register/github")
    public BaseRest<?> registerWithGithub(@RequestBody UserWithGithubDto userWithGithubDto){
        authService.registerWithGithub(userWithGithubDto);
        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .data(userWithGithubDto)
                .timestamp(LocalDateTime.now())
                .message("Successfully register with github.")
                .build();
    }

    @GetMapping("/me/github/{email}/{username}")
    public BaseRest<?>  getMeFromGithub(@PathVariable String email, @PathVariable String username){
        UserDto userDto = authService.getMeFromGithub(email, username);
        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .timestamp(LocalDateTime.now())
                .message("Successfully get email and username from user with github.")
                .data(userDto)
                .build();
    }

}
