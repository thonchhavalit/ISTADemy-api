package co.istad.istademy.api.exercise;

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
public interface ExerciseRepo {

    @SelectProvider(ExerciseProvider.class)
    @Results(
            id = "exerciseMapper",
            value = {
                    @Result(column = "lesson_id",property = "lesson" , one = @One(select = "buildSelectByLessonIdSql")),
                    @Result(column = "correct_answer",property = "correctAnswer"),
                    @Result(column = "is_taken", property = "isTaken"),
                    @Result(column = "is_disabled",property = "isDisabled"),
                    @Result(column = "created_at",property = "createdAt"),
                    @Result(column = "updated_at",property = "updatedAt")
            }
    )
    List<Exercise> buildSelectExerciseSql();

    @Select("SELECT * FROM lessons WHERE id = #{id}")
    @Results({
            @Result(column = "section_id", property = "section" , one = @One(select = "buildSelectLessonBySectionIdSql")),
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
    Section buildSelectLessonBySectionIdSql (Integer id);


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

    @SelectProvider(ExerciseProvider.class)
    @ResultMap("exerciseMapper")
    List<Exercise> buildSelectExerciseByLessonIdSql(Integer id);

    @SelectProvider(ExerciseProvider.class)
    @ResultMap("exerciseMapper")
    Optional<Exercise> buildSelectExerciseByIdSql(@Param("id") Integer id);

    @SelectProvider(ExerciseProvider.class)
    @ResultMap("exerciseMapper")
    Optional<Exercise> buildSelectExerciseByUuidSql(@Param("uuid") String uuid);

    @InsertProvider(ExerciseProvider.class)
    @Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "id")
    boolean buildInsertExerciseSql(@Param("ex") Exercise exercise);

    @UpdateProvider(ExerciseProvider.class)
    boolean buildUpdateExerciseByUuidSql(@Param("ex") Exercise exercise);

    @DeleteProvider(ExerciseProvider.class)
    boolean buildDeleteExerciseByUuidSql(@Param("uuid") String uuid);

    @Select("SELECT EXISTS (SELECT * FROM exercises WHERE is_disabled=false)")
    boolean isExerciseUuidExists(@Param("uuid") String uuid);
}
