package co.istad.istademy.api.user.web;

import jakarta.validation.constraints.NotBlank;

import java.util.Date;

public record ModifyUserDto(@NotBlank(message = "Name is required!") String username,
                            @NotBlank(message = "Email is required!") String email,
                            String location,
                            String profile,
                            Date dob,
                            String bio,
                            String githubUrl

                            ) {
}
