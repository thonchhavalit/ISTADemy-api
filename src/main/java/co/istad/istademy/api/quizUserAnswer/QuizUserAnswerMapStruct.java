package co.istad.istademy.api.quizUserAnswer;

import co.istad.istademy.api.quizUserAnswer.web.QuizUserAnswerDto;
import co.istad.istademy.api.quizUserAnswer.web.SaveQuizUserAnswerDto;
import com.github.pagehelper.PageInfo;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface QuizUserAnswerMapStruct {
    QuizUserAnswerDto QuizUserAnswerToQuizUserAnswerDto(QuizUserAnswer model);
    QuizUserAnswer saveQuizUserAnswerDtoToQuizUserAnswer(SaveQuizUserAnswerDto model);
    PageInfo<QuizUserAnswerDto> pageInfoQuizUserAnswerToPageInfoQuizUserAnswerDto(PageInfo<QuizUserAnswer> model);
}
