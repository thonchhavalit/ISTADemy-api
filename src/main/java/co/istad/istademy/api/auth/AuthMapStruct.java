package co.istad.istademy.api.auth;

import co.istad.istademy.api.auth.web.UserWithGithubDto;
import co.istad.istademy.api.auth.web.UserWithGoogleDto;
import co.istad.istademy.api.user.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AuthMapStruct {

        User createdUserWithGoogle(UserWithGoogleDto userDto);

        User createdUserWithGithub(UserWithGithubDto userWithGithubDto);
}
