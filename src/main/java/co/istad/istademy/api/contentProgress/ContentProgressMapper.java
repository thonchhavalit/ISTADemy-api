package co.istad.istademy.api.contentProgress;

import org.apache.ibatis.annotations.*;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ContentProgressMapper {

    @SelectProvider(ContentProgressProvider.class)
    @Results(
            id = "contentProgressMapper",
            value = {
                    @Result(column = "is_read", property = "isRead"),
                    @Result(column = "user_id", property = "userId"),
                    @Result(column = "course_id", property = "courseId"),
                    @Result(column = "content_id", property = "contentId"),
                    @Result(column = "completed_at", property = "completedAt"),
            }
    )
    List<ContentProgress> buildSelectContentProgressesSql(Integer userId, Integer contentId);

    @InsertProvider(ContentProgressProvider.class)
    @Options(useGeneratedKeys = true,keyColumn = "id", keyProperty = "id")
    Boolean buildSetIsContentReadSql(@Param("cp") ContentProgress content);

    @Select("SELECT EXISTS (SELECT * FROM content_progresses WHERE user_id=#{userId} AND content_id=#{contentId} AND is_read=true)")
    boolean isContentProgressExists(@Param("userId") Integer userId, @Param("contentId") Integer contentId);

}
