package co.istad.istademy.api.level.web;

import jakarta.validation.constraints.NotBlank;

public record AddLevelDto(
        @NotBlank(message = "Level is required!!!!")
        String name
) {
}