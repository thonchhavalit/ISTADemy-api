package co.istad.istademy.api.content.web;

import co.istad.istademy.api.lesson.Lesson;

import java.sql.Timestamp;

public record ContentDto(
        String uuid,
        Boolean isCode,
        String content,
        Boolean isRead,
        Boolean isDisabled,
        Timestamp createdAt,
        Timestamp updatedAt,

        Lesson lesson
) {
}
