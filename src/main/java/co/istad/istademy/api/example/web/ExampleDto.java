package co.istad.istademy.api.example.web;

import co.istad.istademy.api.lesson.Lesson;

import java.sql.Timestamp;

public record ExampleDto(
        Lesson lessonId,
        String uuid,
        String exampleCode,
        String description,
        String output,
        Boolean isDisabled,
        Timestamp createdAt,
        Timestamp updatedAt
) {
}
