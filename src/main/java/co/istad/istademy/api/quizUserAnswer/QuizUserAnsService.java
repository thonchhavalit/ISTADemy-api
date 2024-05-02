package co.istad.istademy.api.quizUserAnswer;


import co.istad.istademy.api.quizUserAnswer.web.QuizUserAnswerDto;
import co.istad.istademy.api.quizUserAnswer.web.SaveQuizUserAnswerDto;
import com.github.pagehelper.PageInfo;

public interface QuizUserAnsService {

    /**
     * use to retrieve all quizUserAnswer from database and response with pagination
     *
     * @param page  : location page
     * @param limit : size of page
     * @return PageInfo of Tutorial is pagination
     */

    PageInfo<QuizUserAnswerDto> selectAllQuizUserAnswer(int page, int limit);

    /**
     * insert quizUserAnswer
     *
     * @param saveQuizUserAnswerDto : is data need to insert and require
     * @return QuizUserAnswerDto define response data
     */
    QuizUserAnswerDto insertQuizUserAnswer(SaveQuizUserAnswerDto saveQuizUserAnswerDto);

    /**
     * select quizUserAnswer by uuid
     *
     * @param uuid : needed id in order to do the searching
     * @return QuizUserAnswerDto define response data
     */
    QuizUserAnswerDto selectQuizUserAnswerByUuid(String uuid);

    /**
     * select quizUserAnswer by id
     *
     * @param id : needed id in order to do the searching
     * @return QuizUserAnswerDto define response data
     */
    QuizUserAnswerDto selectQuizUserAnswerById(Integer id);

    /**
     * use to delete quizUserAnswer by uuid, and it just updates status from false to true
     *
     * @param uuid is required to search before delete
     * @return uuid they deleted
     */
    String deleteQuizUserAnsByUuid(String uuid);
    /**
     * update quizUserAnswer
     *
     * @param  updateQuizUserAnsDto: data need to update
     * @return :QuizUserAnswerDto use to response that necessary to response
     */
    QuizUserAnswerDto updateQuizUserAnsByUuid(String uuid, SaveQuizUserAnswerDto updateQuizUserAnsDto);
}
