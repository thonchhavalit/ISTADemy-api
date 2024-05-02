package co.istad.istademy.api.quizUserAnswer.web;

public record QuizUserAnswerDto(
        Integer userId,
        Integer quizId,
        Integer optionId,
        String uuid
) {
}
