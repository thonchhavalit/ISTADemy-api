package co.istad.istademy.api.quizProgresses;

import co.istad.istademy.api.content.Content;
import co.istad.istademy.api.quizProgresses.web.QuizProgressDto;
import co.istad.istademy.api.quizProgresses.web.SaveQuizProgressDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface QuizProgressMapStruct {
    QuizProgressDto quizProgressToQuizProgressDto (Content model);

    QuizProgress createQuizProgressDtoToQuizProgress(SaveQuizProgressDto model);
}
