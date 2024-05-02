package co.istad.istademy.api.quizProgresses;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface QuizProgressMapper {

    @SelectProvider(QuizProgressProvider.class)
    @Results(
            id = "quizProgressMapper",
            value = {
                    @Result(column = "is_taken", property = "isTaken"),
                    @Result(column = "user_id", property = "userId"),
                    @Result(column = "course_id", property = "courseId"),
                    @Result(column = "quiz_id", property = "quizId"),
                    @Result(column = "completed_at", property = "completedAt"),
            }
    )
    List<QuizProgress> buildSelectQuizProgressesSql(Integer userId, Integer contentId);

    @InsertProvider(QuizProgressProvider.class)
    @Options(useGeneratedKeys = true,keyColumn = "id", keyProperty = "id")
    Boolean buildSetIsQuizTakenSql(@Param("qp") QuizProgress quizProgress);

    @Select("SELECT EXISTS (SELECT * FROM quiz_progresses WHERE user_id=#{userId} AND quiz_id=#{quizId} AND is_taken=true)")
    boolean isQuizProgressExists(Integer userId, Integer quizId);

}
