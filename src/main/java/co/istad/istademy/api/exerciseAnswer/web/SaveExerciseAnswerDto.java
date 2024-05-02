package co.istad.istademy.api.exerciseAnswer.web;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record SaveExerciseAnswerDto(
        @NotNull(message = "User is required !!!!")
        Integer userId,
        @NotNull(message = "Exercise is required!!!!")
        Integer exerciseId,
        @NotBlank(message = "Answer is required!!!!")
        String answer,
        Boolean isCorrected
) {
}
