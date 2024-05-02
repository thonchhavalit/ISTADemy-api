package co.istad.istademy.api.quizOption.web;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record SaveQuizOptionDto(
        @NotNull(message = "Quiz is required!!!!")
        Integer quizId,
        @NotBlank(message = "Choice is required!!!!")
        String choice,
        Boolean isCorrected
) {
}
