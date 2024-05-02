package co.istad.istademy.api.feedback;

import co.istad.istademy.api.user.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Mapper
@Repository
public interface FeedBackRepo {

    @SelectProvider(FeedBackProvider.class)
    @Results(
            id = "feedbackMapper",
            value = {
                    @Result(column = "user_id", property = "userId", one = @One(select = "buildSelectUserByIdSql")),
                    @Result(column = "created_at",property = "createdAt")
            }
    )
    List<FeedBack> buildSelectFeedBackSql();

    @Select("SELECT * FROM users where id = #{id}")
    User buildSelectUserByIdSql (User id);

    @SelectProvider(FeedBackProvider.class)
    @ResultMap("feedbackMapper")
    Optional<FeedBack> buildSelectFeedBackByIdSql(@Param("id") Integer id);

    @SelectProvider(FeedBackProvider.class)
    @ResultMap("feedbackMapper")
    Optional<FeedBack> buildSelectFeedBackByUuidSql(@Param("uuid") String uuid);

    @InsertProvider(FeedBackProvider.class)
    @Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "id")
    boolean buildInsertFeedBackSql(@Param("fd") FeedBack feedBack);

    @UpdateProvider(FeedBackProvider.class)
    boolean buildUpdateFeedBackByUuidSql(@Param("fd") FeedBack feedBack);

    @DeleteProvider(FeedBackProvider.class)
    boolean buildDeleteFeedBackByUuidSql(@Param("uuid") String uuid);

}
