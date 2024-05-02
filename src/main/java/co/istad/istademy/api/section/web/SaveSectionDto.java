package co.istad.istademy.api.section.web;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record SaveSectionDto(
        @NotNull(message = "CourseId is required")
        Integer course,
        @NotBlank(message = "Title is required!!!!") String title,
        @NotBlank(message = "Description is required!!!!")String description
) {
}
