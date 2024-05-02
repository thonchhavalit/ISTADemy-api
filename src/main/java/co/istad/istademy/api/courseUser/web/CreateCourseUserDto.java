package co.istad.istademy.api.courseUser.web;

import jakarta.validation.constraints.NotNull;

public record CreateCourseUserDto(
        @NotNull(message = "Course is required!!!")
        Integer courseId,
        @NotNull(message = "User is required!!!!")
        Integer userId
) {
}