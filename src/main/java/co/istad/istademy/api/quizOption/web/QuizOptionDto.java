package co.istad.istademy.api.quizOption.web;

public record QuizOptionDto(
        String uuid,
        Integer quizId,
        String choice,
        Boolean isCorrected
) {
}
