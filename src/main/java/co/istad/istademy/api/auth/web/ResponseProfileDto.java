package co.istad.istademy.api.auth.web;

import co.istad.istademy.api.image.Image;

public record ResponseProfileDto(
        Integer id,
        String uuid,
        String githubUrl,
        String username,
        String email,
        Image profile,
        String location,
        String bio,
        String dob,
        String role
) {
}
