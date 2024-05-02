package co.istad.istademy.api.quizUserAnswer;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@Mapper
public interface QuizUserAnsRepo {

    @SelectProvider(QuizUserAnswerProvider.class)
    @Results(
            id = "quizUserAnsMapper",
            value = {
                    @Result(column = "user_id",property = "userId"),
                    @Result(column = "quiz_id", property = "quizId"),
                    @Result(column = "option_id", property = "optionId")
            }
    )
    List<QuizUserAnswer> buildSelectQuizUserAnswerSql();

    @SelectProvider(QuizUserAnswerProvider.class)
    @ResultMap("quizUserAnsMapper")
    Optional<QuizUserAnswer> buildSelectQuizUserAnswerByUuidSql(String uuid);

    @SelectProvider(QuizUserAnswerProvider.class)
    @ResultMap("quizUserAnsMapper")
    Optional<QuizUserAnswer> buildSelectQuizUserAnswerByIdSql(Integer id);

    @InsertProvider(QuizUserAnswerProvider.class)
    @Options(useGeneratedKeys = true,keyColumn = "id", keyProperty = "id")
    boolean buildInsertQuizUserAnswerSql(@Param("qAns")QuizUserAnswer quizUserAnswer);

    @UpdateProvider(QuizUserAnswerProvider.class)
    boolean buildUpdateQuizUserAnswerByUuidSql(@Param("qAns")QuizUserAnswer quizUserAnswer);

    @DeleteProvider(QuizUserAnswerProvider.class)
    boolean buildDeleteQuizUserAnswerByUuidSql(@Param("uuid") String uuid);
}
