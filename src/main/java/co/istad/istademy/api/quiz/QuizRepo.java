package co.istad.istademy.api.quiz;

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
public interface QuizRepo {
    
    @SelectProvider(QuizProvider.class)
    @Results(
            id = "quizMapper",
            value = {
//                    @Result(column = "lesson_id",property = "lesson" , one = @One(select = "buildSelectByLessonIdSql")),
                    @Result(column = "is_taken", property = "isTaken"),
                    @Result(column = "is_disabled",property = "isDisabled"),
                    @Result(column = "created_at",property = "createdAt"),
                    @Result(column = "updated_at",property = "updatedAt"),
                    @Result(property = "quizOptions", javaType = List.class, column = "id",
                            many = @Many(select = "co.istad.istademy.api.quizOption.QuizOptionRepo.buildSelectQuizOptionByQuizId"))
            }
    )
    List<Quiz> buildSelectQuizSql();

    @Select("SELECT * FROM lessons WHERE id = #{id}")
    @Results({
            @Result(column = "is_disabled", property = "isDisabled"),
            @Result(column = "created_at", property = "createdAt"),
            @Result(column = "updated_at" , property = "updatedAt")
    })
    Lesson buildSelectByLessonIdSql(Integer lessonId);

    @Select("SELECT * FROM sections where id = #{id}")
    @Results({
            @Result(column = "course_id", property = "course", one = @One(select = "buildSelectSectionByCourseId")),
            @Result(column = "is_completed", property = "isCompleted"),
            @Result(column = "is_disabled", property = "isDisabled"),
            @Result(column = "created_at", property = "createdAt"),
            @Result(column = "updated_at", property = "updatedAt")
    })
    Section buildSelectQuizBySectionIdSql (Integer id);


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

    @SelectProvider(QuizProvider.class)
    @ResultMap("quizMapper")
    List<Quiz> buildSelectQuizByLessonIdSql(Integer id);

    @SelectProvider(QuizProvider.class)
    @ResultMap("quizMapper")
    Optional<Quiz> buildSelectQuizByIdSql(@Param("id") Integer id);

    @SelectProvider(QuizProvider.class)
    @ResultMap("quizMapper")
    Optional<Quiz> buildSelectQuizByUuidSql(@Param("uuid") String uuid);

    @InsertProvider(QuizProvider.class)
    @Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "id")
    boolean buildInsertQuizSql(@Param("q") Quiz quiz);

    @UpdateProvider(QuizProvider.class)
    boolean buildUpdateQuizByUuidSql(@Param("q") Quiz quiz);

    @DeleteProvider(QuizProvider.class)
    boolean buildDeleteQuizByUuidSql(@Param("uuid") String uuid);

    @Select("SELECT EXISTS (SELECT * FROM quizzes WHERE is_disabled=false)")
    boolean isQuizUuidExists(@Param("uuid") String uuid);
}
