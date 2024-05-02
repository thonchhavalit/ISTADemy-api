package co.istad.istademy.api.quizProgresses.web;

import co.istad.istademy.api.lesson.Lesson;

import java.sql.Timestamp;

public record QuizProgressDto(
        Integer id,
        String uuid,
        String content,
        Boolean isRead,
        Boolean isDisabled,
        Timestamp createdAt,
        Boolean isCode,
        Timestamp updatedAt,
        Lesson lesson
) {

}
