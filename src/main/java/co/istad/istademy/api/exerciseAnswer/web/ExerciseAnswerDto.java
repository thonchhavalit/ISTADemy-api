package co.istad.istademy.api.exerciseAnswer.web;

import java.sql.Timestamp;

public record ExerciseAnswerDto(
        String uuid,
        Integer userId,
        Integer exerciseId,
        String answer,
        Boolean isCorrected,
        Timestamp createdAt
) {
}