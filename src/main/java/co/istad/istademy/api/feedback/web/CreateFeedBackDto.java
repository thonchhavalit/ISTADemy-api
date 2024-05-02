package co.istad.istademy.api.feedback.web;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateFeedBackDto(
        @NotNull(message = "User is required!!!")
        Integer userId,
        @NotBlank(message = "Contents are required!!!")
        String contents
) {
}
