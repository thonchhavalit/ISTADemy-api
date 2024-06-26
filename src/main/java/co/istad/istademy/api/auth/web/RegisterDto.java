package co.istad.istademy.api.auth.web;

import co.istad.istademy.api.user.validator.email.EmailUnique;
import co.istad.istademy.api.user.validator.password.Password;
import co.istad.istademy.api.user.validator.password.PasswordMatch;
import co.istad.istademy.api.user.validator.role.RoleIdConstraint;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

@PasswordMatch(password = "password",
        confirmedPassword = "confirmedPassword",
        message = "Your password is not match")
public record RegisterDto(
        @NotBlank(message = "Email is required!")
        @EmailUnique
        @Email
        String email,

        @NotBlank(message = "Username is required!")
        String username,
        @NotBlank(message = "Password is required!")
        @Password
        String password,
        @NotBlank(message = "Confirmed password is required!")
        @Password
        String confirmedPassword,
        @NotNull(message = "Roles are required!")
        @RoleIdConstraint
        List<Integer> roleIds)
       {
}
