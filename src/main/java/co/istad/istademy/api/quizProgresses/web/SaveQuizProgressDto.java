package co.istad.istademy.api.quizProgresses.web;

import jakarta.validation.constraints.NotNull;

public record SaveQuizProgressDto(
        @NotNull(message = "Quiz is required!!!")
        Integer quizId,
        @NotNull(message = "User is required!!!!")
        Integer userId,

        Integer courseId
) {

}
