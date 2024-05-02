package co.istad.istademy.api.auth;

import co.istad.istademy.api.auth.web.*;
import org.springframework.security.core.Authentication;

public interface AuthService {

    AuthDto refreshToken(TokenDto tokenDto);

    AuthDto login(LogInDto logInDto);

    void register(RegisterDto registerDto);

    AuthVerified verify(String email,String verifiedCode);

    void checkVerify(String email, String verifiedCode);

    ResponseProfileDto getAuthMe(Authentication authentication);


    void registerWithGoogle(UserWithGoogleDto userWithGoogleDto);

    UserDto getMeFromGoogle(String email, String username);

    void registerWithGithub(UserWithGithubDto userWithGithubDto);

    UserDto getMeFromGithub(String email, String username);
}
