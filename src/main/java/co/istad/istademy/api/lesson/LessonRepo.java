package co.istad.istademy.api.lesson;

import co.istad.istademy.api.course.Course;
import co.istad.istademy.api.level.Level;
import co.istad.istademy.api.section.Section;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Mapper
@Repository
public interface LessonRepo {
    @SelectProvider(LessonProvider.class)
    @Results(
            id = "lessonMapper",
            value = {
                    @Result(column = "id", property = "id"),
                    @Result(column = "section_id", property = "section"),
                    @Result(column = "is_disabled", property = "isDisabled"),
                    @Result(column = "created_at", property = "createdAt"),
                    @Result(column = "updated_at" , property = "updatedAt"),
                    @Result(property = "contents", javaType = List.class, column = "id",
                            many = @Many(select = "co.istad.istademy.api.content.ContentRepo.buildSelectContentByLessonIdSql")),
                    @Result(property = "quizzes", javaType = List.class, column = "id",
                            many = @Many(select = "co.istad.istademy.api.quiz.QuizRepo.buildSelectQuizByLessonIdSql")),
                    @Result(property = "exercises", javaType = List.class, column = "id",
                            many = @Many(select = "co.istad.istademy.api.exercise.ExerciseRepo.buildSelectExerciseByLessonIdSql"))
            }
    )
    List<Lesson> buildSelectAllLessonSql(Integer sectionId);

    @Select("SELECT * FROM lessons where section_id = #{id}")
    @ResultMap("lessonMapper")
    List<Lesson> buildSelectLessonBySectionIdSql(@Param("id") Integer id);

    @SelectProvider(LessonProvider.class)
    @ResultMap("lessonMapper")
    Optional<Lesson> buildSelectLessonByIdSql(@Param("id") Integer id);

    @SelectProvider(LessonProvider.class)
    @ResultMap("lessonMapper")
    Optional<Lesson> buildSelectLessonByUuidSql(@Param("uuid") String uuid);

    @InsertProvider(LessonProvider.class)
    @Options(useGeneratedKeys = true, keyColumn = "id" , keyProperty = "id")
    boolean buildCreateLessonSql(@Param("l") Lesson lesson);

    @UpdateProvider(LessonProvider.class)
    boolean buildUpdateLessonByUuidSql(@Param("l") Lesson lesson);

    @UpdateProvider(LessonProvider.class)
    boolean buildDeleteLessonByUuidSql(@Param("uuid") String uuid);

    @Select("SELECT EXISTS (SELECT * FROM lessons WHERE is_disabled=false)")
    boolean isLessonIdExists(@Param("uuid") String uuid);
}