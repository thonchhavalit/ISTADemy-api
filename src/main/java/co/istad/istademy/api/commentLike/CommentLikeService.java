package co.istad.istademy.api.commentLike;

import co.istad.istademy.api.commentLike.web.CommentLikeDto;
import co.istad.istademy.api.commentLike.web.SaveCommentLikeDto;
import com.github.pagehelper.PageInfo;

public interface CommentLikeService {

    /**
     * use to retrieve all commentLikes from database and response with pagination
     *
     * @param page  : location page
     * @param limit : size of page
     * @return PageInfo of Tutorial is pagination
     */

    PageInfo<CommentLikeDto> selectAllCommentLike(int page, int limit, Integer cmtId);

    /**
     * insert commentLike
     *
     * @param saveCommentLikeDto : is data need to insert and require
     * @return CommentLikeDto define response data
     */
    CommentLikeDto insertCommentLike(SaveCommentLikeDto saveCommentLikeDto);

    /**
     * select commentLike by uuid
     *
     * @param uuid : needed id in order to do the searching
     * @return CommentLikeDto define response data
     */
    CommentLikeDto selectCommentLikeByUuid(String uuid);

    /**
     * select commentLike by id
     *
     * @param id : needed id in order to do the searching
     * @return CommentLikeDto define response data
     */
    CommentLikeDto selectCommentLikeById(Integer id);

    Integer deleteCommentLike(Integer userId, Integer cmtId);

    /**
     * use to delete commentLike by uuid, and it just updates status from false to true
     *
     * @param uuid is required to search before delete
     * @return uuid they deleted
     */
    String deleteCommentLikeByUuid(String uuid);

    Boolean checkIfLiked(Integer userId, Integer cmtId);

    /**
     * update commentLike
     *
     * @param updateCommentLikeDto : data need to update
     * @return :CommentLikeDto use to response that necessary to response
     */
    CommentLikeDto updateCommentLikeByUuid(String uuid, SaveCommentLikeDto updateCommentLikeDto);
}
