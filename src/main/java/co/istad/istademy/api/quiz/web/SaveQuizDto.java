package co.istad.istademy.api.quiz.web;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record SaveQuizDto(
        @NotNull(message = "Lesson is required!!!")
        Integer lesson,
        @NotBlank(message = "Question is required!!!")
        String question
) {
}
