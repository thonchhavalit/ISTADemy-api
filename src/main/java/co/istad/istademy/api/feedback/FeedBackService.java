package co.istad.istademy.api.feedback;

import co.istad.istademy.api.feedback.web.CreateFeedBackDto;
import co.istad.istademy.api.feedback.web.FeedBackDto;
import com.github.pagehelper.PageInfo;

public interface FeedBackService {
    /**
     * use to retrieve all feedbacks from database and response with pagination
     *
     * @param page  : location page
     * @param limit : size of page
     * @return PageInfo of Tutorial is pagination
     */

    PageInfo<FeedBackDto> selectAllFeedBacks(int page, int limit);

    /**
     * insert feedback
     *
     * @param createFeedBackDto : is data need to insert and require
     * @return FeedBackDto define response data
     */
    FeedBackDto insertFeedbacks(CreateFeedBackDto createFeedBackDto);

    /**
     * select feedback by id
     *
     * @param id : needed id in order to do the searching
     * @return FeedBackDto define response data
     */
    FeedBackDto selectFeedBackById(Integer id);

    /**
     * select feedback by uuid
     *
     * @param uuid : needed uuid in order to do the searching
     * @return FeedBackDto define response data
     */
    FeedBackDto selectFeedBackByUuid(String uuid);

    /**
     * use to delete feedback by uuid, and it just updates status from false to true
     *
     * @param uuid is required to search before delete
     * @return uuid they deleted
     */
    String deleteFeedBackByUuid(String uuid);

    /**
     * update course
     *
     * @param updateFeedBackDto : data need to update
     * @return :FeedBackDto use to response that necessary to response
     */
    FeedBackDto updateFeedBackByUuid(String uuid, CreateFeedBackDto updateFeedBackDto);
}
