package co.istad.istademy.api.example.web;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record SaveExampleDto(
        @NotNull(message = "Lesson is required!!!")
        Integer lessonId,
        @NotBlank(message = "Example is required!!!!")
        String exampleCode,
        @NotBlank(message = "Description is required!!!")
        String description,
        @NotBlank(message = "Output is required!!!")
        String output
) {
}
