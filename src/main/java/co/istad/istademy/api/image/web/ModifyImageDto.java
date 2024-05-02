package co.istad.istademy.api.image.web;

import jakarta.validation.constraints.NotBlank;

public record ModifyImageDto(
        @NotBlank(message = "Name is required!!!")
        String name,
        @NotBlank(message = "Url is required!!!")
        String url

) {
}
