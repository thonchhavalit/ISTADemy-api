package co.istad.istademy.api.quiz.web;

import co.istad.istademy.api.lesson.Lesson;

import java.sql.Timestamp;

public record QuizDto(
        Lesson lesson,
        String uuid,
        String question,
        Boolean isTaken,
        Boolean isDisabled,
        Timestamp createdAt,
        Timestamp updatedAt
) {
}
