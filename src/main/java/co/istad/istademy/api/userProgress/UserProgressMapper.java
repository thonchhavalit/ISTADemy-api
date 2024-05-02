package co.istad.istademy.api.userProgress;
import co.istad.istademy.api.contentProgress.ContentProgress;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface UserProgressMapper {

    @Select("SELECT * FROM content_progresses WHERE user_id = #{userId} AND content_id = #{contentId}")
    List<ContentProgress> selectContentProgresses(@Param("userId") Integer userId, @Param("contentId") Integer contentId);

    @SelectProvider(UserProgressProvider.class)
    @Results(
            id = "userProgressMapper",
            value = {
                    @Result(column = "course_name", property = "courseName"),
//                    @Result(column = "user_id", property = "userId"),
                    @Result(column = "course_id", property = "courseId"),
                    @Result(column = "content_completed", property = "contentCompleted"),
                    @Result(column = "lesson_completed", property = "lessonCompleted"),
                    @Result(column = "quizzes_completed", property = "quizzesCompleted"),
                    @Result(column = "total_content", property = "totalContent"),
                    @Result(column = "total_quiz", property = "totalQuiz"),
                    @Result(column = "total_lesson", property = "totalLesson"),
                    @Result(column = "course_image", property = "courseImage"),
                    @Result(column = "course_completion_percentage", property = "courseCompletionPercentage")
            })
    List<UserProgress> buildSelectUserProgressSql(@Param("userId") Integer userId);

    @SelectProvider(UserProgressProvider.class)
    @ResultMap("userProgressMapper")
    List<UserProgress> buildSelectCompletedCoursesSql(@Param("userId") Integer userId);
}
