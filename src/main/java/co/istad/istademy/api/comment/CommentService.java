package co.istad.istademy.api.comment;

import co.istad.istademy.api.comment.web.CommentDto;
import co.istad.istademy.api.comment.web.SaveCommentDto;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CommentService {
    /**
     * use to retrieve all comments from database and response with pagination
     *
     * @param page  : location page
     * @param limit : size of page
     * @return PageInfo of Tutorial is pagination
     */

    PageInfo<CommentDto> selectAllComments(int page, int limit, Integer lessonId);

    /**
     * insert comment
     *
     * @param saveCommentDto : is data need to insert and require
     * @return CommentDto define response data
     */
    CommentDto insertComment(SaveCommentDto saveCommentDto);

    /**
     * select comment by uuid
     *
     * @param uuid : needed id in order to do the searching
     * @return CommentDto define response data
     */
    CommentDto selectCommentByUuid(String uuid);

    /**
     * select comment by id
     *
     * @param id : needed id in order to do the searching
     * @return CommentDto define response data
     */
    CommentDto selectCommentById(Integer id);

    /**
     * use to delete comment by uuid, and it just updates status from false to true
     *
     * @param uuid is required to search before delete
     * @return uuid they deleted
     */
    String deleteCommentByUuid(String uuid);

    /**
     * update comment
     *
     * @param updateCommentDto : data need to update
     * @return :CommentDto use to response that necessary to response
     */
    CommentDto updateCommentByUuid(String uuid, SaveCommentDto updateCommentDto);

    List<Comment> selectCommentByUserId(Integer id);

    List<Comment> selectCommentByLessonId(Integer id);
}
