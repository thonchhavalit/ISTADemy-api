package co.istad.istademy.api.commentLike;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@Mapper
public interface CommentLikeRepo {

    @SelectProvider(CommentLikeProvider.class)
    @Results(
            id = "commentLikeMapper",
            value = {
                    @Result(column = "comment_id", property = "commentId"),
                    @Result(column = "user_id", property = "userId"),
                    @Result(column = "created_at", property = "createdAt"),
                    @Result(column = "updated_at", property = "updatedAt")
            }
    )
    List<CommentLike> buildSelectCommentLikeSql(Integer cmtId);

    @SelectProvider(CommentLikeProvider.class)
    @ResultMap("commentLikeMapper")
    Optional<CommentLike> buildSelectCommentLikeByUuidSql(String uuid);

    @SelectProvider(CommentLikeProvider.class)
    @ResultMap("commentLikeMapper")
    Optional<CommentLike> buildSelectCommentLikeByIdSql(Integer id);

    @InsertProvider(CommentLikeProvider.class)
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    boolean buildInsertCommentLikeSql(@Param("cml") CommentLike commentLike);

    @UpdateProvider(CommentLikeProvider.class)
    boolean buildUpdateCommentLikeByUuidSql(@Param("cml") CommentLike commentLike);

    @DeleteProvider(CommentLikeProvider.class)
    boolean buildDeleteCommentLikeByUuidSql(@Param("uuid") String uuid);

    @Delete("DELETE FROM comment_likes WHERE comment_id = #{cmtId} AND user_id = #{userId}")
    boolean buildDeleteCommentLikeSql(Integer userId, Integer cmtId);

    @Select("SELECT EXISTS(SELECT * FROM comment_likes WHERE user_id = #{userId} AND comment_id = #{cmtId})")
    boolean buildCheckIfLikedByUserIdAndCmtId(Integer userId, Integer cmtId);
}
