package co.istad.istademy.api.quizUserAnswer.web;

import jakarta.validation.constraints.NotNull;

public record SaveQuizUserAnswerDto(
        @NotNull(message = "User is required!!!!")
        Integer userId,
        @NotNull(message = "Quiz is required!!!")
        Integer quizId,
        @NotNull(message = "Option is required!!!!")
        Integer optionId
) {
}
