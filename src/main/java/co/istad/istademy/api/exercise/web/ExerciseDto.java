package co.istad.istademy.api.exercise.web;

import co.istad.istademy.api.lesson.Lesson;

import java.sql.Timestamp;

public record ExerciseDto(
        Lesson lesson,
        String uuid,
        String question,
        String correctAnswer,
        Boolean isTaken,
        String prompt,
        Boolean isDisabled,
        Timestamp createdAt,
        Timestamp updatedAt
) {
}
