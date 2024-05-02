package co.istad.istademy.api.quiz;

import co.istad.istademy.api.quiz.web.QuizDto;
import co.istad.istademy.api.quiz.web.SaveQuizDto;
import com.github.pagehelper.PageInfo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface QuizMapStruct {
    QuizDto quizToQuizDto(Quiz model);
    @Mapping(source = "lesson", target = "lesson.id")
    Quiz createQuizDtoToQuiz(SaveQuizDto model);
    PageInfo<QuizDto> pageInfoQuizToPageInfoQuizDto(PageInfo<Quiz> model);

}
