package co.istad.istademy.api.content;

import co.istad.istademy.api.course.Course;
import co.istad.istademy.api.lesson.Lesson;
import co.istad.istademy.api.level.Level;
import co.istad.istademy.api.section.Section;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Mapper
@Repository
public interface ContentRepo {

    @SelectProvider(ContentProvider.class)
    @Results(
            id = "contentMapper",
            value = {
                    @Result(column = "lesson_id", property = "lesson" , one = @One(select = "buildSelectLessonIdSql")),
                    @Result(column = "is_read", property = "isRead"),
                    @Result(column = "is_code", property = "isCode"),
                    @Result(column = "is_disabled", property = "isDisabled"),
                    @Result(column = "created_at", property = "createdAt"),
                    @Result(column = "updated_at" , property = "updatedAt")
            }
    )
    List<Content> buildSelectContentSql();

    @Select("SELECT * FROM sections where id = #{id}")
    @Results({
            @Result(column = "is_completed", property = "isCompleted"),
            @Result(column = "is_disabled", property = "isDisabled"),
            @Result(column = "created_at", property = "createdAt"),
            @Result(column = "updated_at", property = "updatedAt")
    })
    Section buildSelectLessonBySectionIdSql (Integer id);

    @Select("SELECT * FROM lessons where id = #{id}")
    @Results({
            @Result(column = "is_disabled", property = "isDisabled"),
            @Result(column = "created_at", property = "createdAt"),
            @Result(column = "updated_at" , property = "updatedAt")
    })
    Lesson buildSelectLessonIdSql(Integer id);

    @Select("SELECT * FROM courses where id = #{id}")
    @Results({
            @Result(column = "created_at", property = "createdAt"),
            @Result(column = "updated_at", property = "updatedAt"),
            @Result(column = "level_id", property = "level", many = @Many(select = "buildSelectCourseLevelByIdSql")),
            @Result(column = "required_time", property = "requiredTime"),
            @Result(column = "is_disabled", property = "isDisabled")
    })
    Course buildSelectSectionByCourseId (Integer id);

    @Select("SELECT * FROM levels where id = #{id}")
    List<Level> buildSelectCourseLevelByIdSql (Integer id);

    @Select("SELECT * FROM contents WHERE lesson_id = #{id} AND is_disabled = false ORDER BY created_at")
    @ResultMap("contentMapper")
    List<Content> buildSelectContentByLessonIdSql(Integer id);

    @SelectProvider(ContentProvider.class)
    @ResultMap("contentMapper")
    Optional<Content> buildSelectContentByIdSql(Integer id);

    @SelectProvider(ContentProvider.class)
    @ResultMap("contentMapper")
    Optional<Content> buildSelectContentByUuidSql(String uuid);

    @InsertProvider(ContentProvider.class)
    @Options(useGeneratedKeys = true,keyColumn = "id", keyProperty = "id")
    boolean buildInsertContentSql(@Param("cn") Content content);

    @UpdateProvider(ContentProvider.class)
    boolean buildUpdateContentByUuidSql(@Param("cn") Content content);

    @DeleteProvider(ContentProvider.class)
    boolean buildDeleteContentByUuidSql(@Param("uuid") String uuid);

    @Select("SELECT EXISTS (SELECT * FROM contents WHERE is_disabled=false)")
    boolean isContentUuidExists(@Param("uuid") String uuid);
}
