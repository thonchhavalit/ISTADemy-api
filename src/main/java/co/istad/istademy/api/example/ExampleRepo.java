package co.istad.istademy.api.example;

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
public interface ExampleRepo {

    @SelectProvider(ExampleProvider.class)
    @Results(
            id = "exampleMapper",
            value = {
                    @Result(column = "lesson_id", property = "lessonId", one = @One(select = "buildSelectLessonIdSql")),
                    @Result(column = "example_code", property = "exampleCode"),
                    @Result(column = "is_disabled", property = "isDisabled"),
                    @Result(column = "created_at", property = "createdAt"),
                    @Result(column = "updated_at", property = "updatedAt")
            }
    )
    List<Example> buildSelectExampleSql();

    @Select("SELECT * FROM sections where id = #{id}")
    @Results({
            @Result(column = "course_id", property = "course", one = @One(select = "buildSelectSectionByCourseId")),
            @Result(column = "is_completed", property = "isCompleted"),
            @Result(column = "is_disabled", property = "isDisabled"),
            @Result(column = "created_at", property = "createdAt"),
            @Result(column = "updated_at", property = "updatedAt")
    })
    Section buildSelectLessonBySectionIdSql (Integer id);

    @Select("SELECT * FROM lessons where id = #{id}")
    @Results({
            @Result(column = "section_id", property = "section" , one = @One(select = "buildSelectLessonBySectionIdSql")),
            @Result(column = "is_disabled", property = "isDisabled"),
            @Result(column = "created_at", property = "createdAt"),
            @Result(column = "updated_at" , property = "updatedAt")
    })
    Lesson buildSelectLessonIdSql(Integer lessonId);

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

    @SelectProvider(ExampleProvider.class)
    @ResultMap("exampleMapper")
    List<Example> buildSelectExampleByLessonIdSql(Integer id);

    @SelectProvider(ExampleProvider.class)
    @ResultMap("exampleMapper")
    Optional<Example> buildSelectExampleByIdSql(Integer id);

    @SelectProvider(ExampleProvider.class)
    @ResultMap("exampleMapper")
    Optional<Example> buildSelectExampleByUuidSql(String uuid);

    @InsertProvider(ExampleProvider.class)
    @Options(useGeneratedKeys = true,keyColumn = "id", keyProperty = "id")
    boolean buildInsertExampleSql(@Param("ex") Example example);

    @UpdateProvider(ExampleProvider.class)
    boolean buildUpdatedExampleByUuidSql(@Param("ex") Example example);

    @DeleteProvider(ExampleProvider.class)
    boolean buildDeleteExampleByUuidSql(@Param("uuid") String uuid);

    @Select("SELECT EXISTS (SELECT * FROM examples WHERE is_disabled=false)")
    boolean isExampleUuidExists(@Param("uuid") String uuid);
}
