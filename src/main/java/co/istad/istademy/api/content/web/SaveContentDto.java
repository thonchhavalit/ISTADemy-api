package co.istad.istademy.api.content.web;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record SaveContentDto(
        @NotNull(message = "Lesson is required!!!")
        Integer lesson,
        @NotBlank(message = "Content is required!!!!")
        String content,

        Boolean isCode
) {
}
