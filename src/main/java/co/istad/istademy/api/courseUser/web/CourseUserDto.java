package co.istad.istademy.api.courseUser.web;

import java.sql.Timestamp;

public record CourseUserDto(
        String uuid,
        Integer courseId,
        Integer userId,
        Timestamp enrolledAt,
        Timestamp finishedAt
) {
}