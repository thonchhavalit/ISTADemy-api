package co.istad.istademy.api.exercise;

import co.istad.istademy.api.exercise.web.ExerciseDto;
import co.istad.istademy.api.exercise.web.SaveExerciseDto;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface ExerciseService {

    /**
     * use to retrieve all exercises from database and response with pagination
     *
     * @param page  : location page
     * @param limit : size of page
     * @return PageInfo of Tutorial is pagination
     */
    PageInfo<ExerciseDto> selectAllExercise(int page, int limit);

    /**
     * select exercise by id
     *
     * @param id : needed id in order to do the searching
     * @return ExerciseDto define response data
     */
    ExerciseDto selectExerciseById(Integer id);

    /**
     * select exercise by uuid
     *
     * @param uuid : needed id in order to do the searching
     * @return ExerciseDto define response data
     */
    ExerciseDto selectExerciseByUuid(String uuid);

    /**
     * insert exercise
     *
     * @param saveExerciseDto : is data need to insert and require
     * @return ExerciseDto define response data
     */
    ExerciseDto insertExercise(SaveExerciseDto saveExerciseDto);

    /**
     * update exercise
     *
     * @param updateExerciseDto : data need to update
     * @return :ExerciseDto use to response that necessary to response
     */
    ExerciseDto updateExerciseByUuid(String uuid, SaveExerciseDto updateExerciseDto);

    /**
     * use to delete exercise by uuid, and it just updates status from false to true
     *
     * @param uuid is required to search before delete
     * @return uuid they deleted
     */
    String deleteExerciseByUuid(String uuid);

    List<Exercise> selectExerciseByLessonId(Integer id);
}
