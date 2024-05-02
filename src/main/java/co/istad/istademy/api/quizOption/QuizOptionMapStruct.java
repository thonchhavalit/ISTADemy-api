package co.istad.istademy.api.quizOption;

import co.istad.istademy.api.quizOption.web.QuizOptionDto;
import co.istad.istademy.api.quizOption.web.SaveQuizOptionDto;
import com.github.pagehelper.PageInfo;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface QuizOptionMapStruct {
    QuizOptionDto QUizOptionToQuizOptionDto(QuizOption model);
    QuizOption saveQuizOptionDtoToQuizOption(SaveQuizOptionDto model);
    PageInfo<QuizOptionDto> pageInfoQuizOptionToQuizOptionDto(PageInfo<QuizOption> model);
}
