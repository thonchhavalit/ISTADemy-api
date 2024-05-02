package co.istad.istademy.api.course.web;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record SaveCourseDto(
        @NotBlank(message = "Title is required.")
        String title,
        @NotBlank(message = "Description is required.")
        String description,
        @NotNull(message = "Image is required.")
        String image,
        @NotNull(message = "Level is required.")
        Integer level,
        @NotNull(message = "Time required is required.")
        Float requiredTime
) {
}
