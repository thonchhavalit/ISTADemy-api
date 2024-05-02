package co.istad.istademy.api.quizOption;

import co.istad.istademy.api.quizOption.web.QuizOptionDto;
import co.istad.istademy.api.quizOption.web.SaveQuizOptionDto;
import com.github.pagehelper.PageInfo;

public interface QuizOptionService {
    /**
     * use to retrieve all quizOption from database and response with pagination
     *
     * @param page  : location page
     * @param limit : size of page
     * @return PageInfo of Tutorial is pagination
     */

    PageInfo<QuizOptionDto> selectAllQuizOption(int page, int limit);

    /**
     * insert quizOption
     *
     * @param saveQuizOptionDto : is data need to insert and require
     * @return QuizOptionDto define response data
     */
    QuizOptionDto insertQuizOption(SaveQuizOptionDto saveQuizOptionDto);

    /**
     * select quizOption by uuid
     *
     * @param uuid : needed id in order to do the searching
     * @return QuizOptionDto define response data
     */
    QuizOptionDto selectQuizOptionByUuid(String uuid);

    /**
     * select quizOption by id
     *
     * @param id : needed id in order to do the searching
     * @return QuizOptionDto define response data
     */
    QuizOptionDto selectQuizOptionById(Integer id);

    /**
     * use to delete quizOption by uuid, and it just updates status from false to true
     *
     * @param uuid is required to search before delete
     * @return uuid they deleted
     */
    String deleteQuizOptionByUuid(String uuid);
    /**
     * update quizOption
     *
     * @param updateQuizOptionDto : data need to update
     * @return :QuizOptionDto use to response that necessary to response
     */
    QuizOptionDto updateQuizOptionByUuid(String uuid, SaveQuizOptionDto updateQuizOptionDto);
}
