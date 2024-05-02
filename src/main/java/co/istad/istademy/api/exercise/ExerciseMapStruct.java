package co.istad.istademy.api.exercise;

import co.istad.istademy.api.exercise.web.ExerciseDto;
import co.istad.istademy.api.exercise.web.SaveExerciseDto;
import com.github.pagehelper.PageInfo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ExerciseMapStruct {
    ExerciseDto exerciseToExerciseDto(Exercise model);
    @Mapping(source = "lesson", target = "lesson.id")
    Exercise createExercieDtoToExercise(SaveExerciseDto model);
    PageInfo<ExerciseDto> pageInfoExerciseToPageInfoExerciseDto(PageInfo<Exercise> model);
}
