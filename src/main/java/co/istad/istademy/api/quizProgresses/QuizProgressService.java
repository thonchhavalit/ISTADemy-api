package co.istad.istademy.api.quizProgresses;

import co.istad.istademy.api.quizProgresses.web.SaveQuizProgressDto;
import com.github.pagehelper.PageInfo;

public interface QuizProgressService {

    /**
     * use to retrieve all contents from database and response with pagination
     *
     * @param page  : location page
     * @param limit : size of page
     * @return PageInfo of Tutorial is pagination
     */
    PageInfo<QuizProgress> selectQuizProgresses(int page, int limit, Integer userId, Integer contentId);

    /**
     * insert content
     *
     * @param saveQuizProgressDto: is data need to insert and require
     * @return ContentDto define response data
     */
    QuizProgress insertSetIsQuizTaken(SaveQuizProgressDto saveQuizProgressDto);

    Boolean selectIsQuizProgressExist(Integer userId, Integer contentId);
}
