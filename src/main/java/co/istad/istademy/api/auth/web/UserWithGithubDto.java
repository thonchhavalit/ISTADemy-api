package co.istad.istademy.api.auth.web;

import co.istad.istademy.api.user.validator.email.EmailUnique;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UserWithGithubDto(
        @NotBlank(message = "Email is required!")
        @Email
        @EmailUnique
        String email,
        @NotBlank(message = "Username is required!")
        String username
) {
}
