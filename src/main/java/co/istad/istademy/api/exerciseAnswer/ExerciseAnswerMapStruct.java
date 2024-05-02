package co.istad.istademy.api.exerciseAnswer;

import co.istad.istademy.api.exerciseAnswer.web.ExerciseAnswerDto;
import co.istad.istademy.api.exerciseAnswer.web.SaveExerciseAnswerDto;
import com.github.pagehelper.PageInfo;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ExerciseAnswerMapStruct {
    ExerciseAnswerDto ExerciseAnswerToExerciseAnswerDto(ExerciseAnswer model);
    ExerciseAnswer saveExerciseAnswerDtoToExerciseAnswer(SaveExerciseAnswerDto model);
    PageInfo<ExerciseAnswerDto> pageInfoExerciseAnswerToPageInfoExerciseAnswerDto(PageInfo<ExerciseAnswer> model);
}
