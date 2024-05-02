package co.istad.istademy.api.exerciseAnswer;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@Mapper
public interface ExerciseAnswerRepo {

    @SelectProvider(ExerciseAnswerProvider.class)
    @Results(
            id = "exerciseAnswerMapper",
            value = {
                    @Result(column = "user_id",property = "userId"),
                    @Result(column = "exercise_id",property = "exerciseId"),
                    @Result(column = "is_corrected",property = "isCorrected"),
                    @Result(column = "created_at",property = "createdAt")
            }
    )
    List<ExerciseAnswer> buildSelectExerciseAnswerSql();

    @SelectProvider(ExerciseAnswerProvider.class)
    @ResultMap("exerciseAnswerMapper")
    Optional<ExerciseAnswer> buildSelectExerciseAnswerByUuidSql(String uuid);

    @SelectProvider(ExerciseAnswerProvider.class)
    @ResultMap("exerciseAnswerMapper")
    Optional<ExerciseAnswer> buildSelectExerciseAnswerByIdSql(Integer id);

    @InsertProvider(ExerciseAnswerProvider.class)
    @Options(useGeneratedKeys = true,keyColumn = "id",keyProperty = "id")
    boolean buildInsertExerciseAnswerSql(@Param("exAns") ExerciseAnswer exerciseAnswer);

    @UpdateProvider(ExerciseAnswerProvider.class)
    boolean buildUpdateExerciseAnswerByUuidSql(@Param("exAns") ExerciseAnswer exerciseAnswer);

    @DeleteProvider(ExerciseAnswerProvider.class)
    boolean buildDeleteExerciseAnswerByUuidSql(@Param("uuid") String uuid);
}
