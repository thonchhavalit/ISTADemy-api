package co.istad.istademy.api.exerciseAnswer;

import co.istad.istademy.api.exerciseAnswer.web.ExerciseAnswerDto;
import co.istad.istademy.api.exerciseAnswer.web.SaveExerciseAnswerDto;
import com.github.pagehelper.PageInfo;

public interface ExerciseAnswerService {
    /**
     * use to retrieve all exerciseAnswers from database and response with pagination
     *
     * @param page  : location page
     * @param limit : size of page
     * @return PageInfo of Tutorial is pagination
     */

    PageInfo<ExerciseAnswerDto> selectAllExerciseAnswer(int page, int limit);

    /**
     * insert ExerciseAnswer
     *
     * @param saveExerciseAnswerDto : is data need to insert and require
     * @return ExerciseAnswerDto define response data
     */
    ExerciseAnswerDto insertExerciseAnswer(SaveExerciseAnswerDto saveExerciseAnswerDto);

    /**
     * select ExerciseAnswer by uuid
     *
     * @param uuid : needed id in order to do the searching
     * @return ExerciseAnswerDto define response data
     */
    ExerciseAnswerDto selectByExerciseAnswerUuid(String uuid);

    /**
     * select ExerciseAnswer by id
     *
     * @param id : needed id in order to do the searching
     * @return ExerciseAnswerDto define response data
     */
    ExerciseAnswerDto selectByExerciseAnswerId(Integer id);

    /**
     * use to delete ExerciseAnswer by uuid, and it just updates status from false to true
     *
     * @param uuid is required to search before delete
     * @return uuid they deleted
     */
    String deleteExerciseAnswerByUuid(String uuid);

    /**
     * update ExerciseAnswer
     *
     * @param updateExerciseAnswerDto : data need to update
     * @return :ExerciseAnswerDto use to response that necessary to response
     */
    ExerciseAnswerDto updateExerciseAnswerByUuid(String uuid, SaveExerciseAnswerDto updateExerciseAnswerDto);
}
