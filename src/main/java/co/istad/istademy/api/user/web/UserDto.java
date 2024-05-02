package co.istad.istademy.api.user.web;

import co.istad.istademy.api.image.Image;

import java.sql.Timestamp;
import java.util.Date;

public record UserDto(
        String uuid,
        String username,
        String email,
        String password,
        Date dob,
        String githubUrl,
        String bio,
        Timestamp updatedAt,
        Timestamp createdAt,
        Boolean isVerified,
        String verifiedCode,
        Image profile,
        Date lastLogin,
        Boolean isDisabled,
        String location) {


}
