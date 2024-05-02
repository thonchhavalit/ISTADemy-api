package co.istad.istademy.api.quiz;

import co.istad.istademy.api.quiz.web.QuizDto;
import co.istad.istademy.api.quiz.web.SaveQuizDto;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface QuizService {
    /**
     * use to retrieve all quizzes from database and response with pagination
     *
     * @param page  : location page
     * @param limit : size of page
     * @return PageInfo of Tutorial is pagination
     */
    PageInfo<QuizDto> selectAllQuiz(int page, int limit);

    /**
     * select quiz by id
     *
     * @param id : needed id in order to do the searching
     * @return QuizDto define response data
     */
    QuizDto selectQuizById(Integer id);

    /**
     * select quiz by uuid
     *
     * @param uuid : needed id in order to do the searching
     * @return QuizDto define response data
     */
    QuizDto selectQuizByUuid(String uuid);

    /**
     * insert quiz
     *
     * @param saveQuizDto : is data need to insert and require
     * @return QuizDto define response data
     */
    QuizDto insertQuiz(SaveQuizDto saveQuizDto);

    /**
     * update quiz
     *
     * @param updateQuizDto : data need to update
     * @return :QuizDto use to response that necessary to response
     */
    QuizDto updateQuizByUuid(String uuid, SaveQuizDto updateQuizDto);

    /**
     * use to delete quiz by uuid, and it just updates status from false to true
     *
     * @param uuid is required to search before delete
     * @return uuid they deleted
     */
    String deleteQuizByUuid(String uuid);

    List<Quiz> selectQuizByLessonId(Integer id);

}
