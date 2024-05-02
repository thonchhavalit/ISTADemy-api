package co.istad.istademy.api.quizOption;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@Mapper
public interface QuizOptionRepo {
    @SelectProvider(QuizOptionProvider.class)
    @Results(
            id = "quizOptionMapper",
            value = {
                    @Result(column = "quiz_id",property = "quizId"),
                    @Result(column = "is_corrected", property = "isCorrected")
            }
    )
    List<QuizOption> buildSelectQuizOptionSql();

    @SelectProvider(QuizOptionProvider.class)
    @ResultMap("quizOptionMapper")
    Optional<QuizOption> buildSelectQuizOptionByUuidSql(String uuid);

    @SelectProvider(QuizOptionProvider.class)
    @ResultMap("quizOptionMapper")
    Optional<QuizOption> buildSelectQuizOptionByIdSql(Integer id);

    @Select("SELECT * FROM quiz_options WHERE quiz_id = #{id}")
    @ResultMap("quizOptionMapper")
    List<QuizOption> buildSelectQuizOptionByQuizId(Integer id);

    @InsertProvider(QuizOptionProvider.class)
    @Options(useGeneratedKeys = true,keyProperty = "id",keyColumn = "id")
    boolean buildInsertQuizOptionSql(@Param("quo") QuizOption quizOption);

    @UpdateProvider(QuizOptionProvider.class)
    boolean buildUpdateQuizOptionByUuidSql(@Param("quo") QuizOption quizOption);

    @DeleteProvider(QuizOptionProvider.class)
    boolean buildDeleteQuizOptionByUuidSql(@Param("uuid") String uuid);
}
