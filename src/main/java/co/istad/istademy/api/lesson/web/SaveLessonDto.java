package co.istad.istademy.api.lesson.web;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record SaveLessonDto(
        @NotNull(message = "Section is required!!!")
        Integer section,
        @NotBlank(message = "Title is required!!!") String title,
        @NotBlank(message = "Description is required!!!") String description
) {
}
