package co.istad.istademy.api.certificate.web;

import jakarta.validation.constraints.NotNull;


public record CreateCertificateDto(
        @NotNull(message = "User is required!!!!")
        Integer user,
        @NotNull(message = "Course is required!!!!")
        Integer course
) {
}
