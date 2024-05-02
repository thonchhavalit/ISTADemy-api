package co.istad.istademy.api.certificate.web;

import co.istad.istademy.api.course.Course;
import co.istad.istademy.api.user.User;

import java.sql.Timestamp;

public record CertificateDto(
        String uuid,
        User user,
        Course course,
        Timestamp dateEarned
){
}