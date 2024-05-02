package co.istad.istademy.api.exercise.web;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record SaveExerciseDto(
        @NotNull(message = "Lesson is required!!!!")
        Integer lesson,
        @NotBlank(message = "Question is required!!!!")
        String question,
        @NotBlank(message = "Correct Answer is required!!!!")
        String correctAnswer,
        @NotBlank(message = "Prompt is required!!!!")
        String prompt
) {
}
