package co.istad.istademy.api.exerciseAnswer;

import org.apache.ibatis.builder.annotation.ProviderMethodResolver;
import org.apache.ibatis.jdbc.SQL;

public class ExerciseAnswerProvider implements ProviderMethodResolver {
    private static final String exerciseAns = "exercise_correct_answers";

    public static String buildSelectExerciseAnswerSql(){
        return new SQL(){{
            SELECT("*");
            FROM(exerciseAns);
        }}.toString();
    }

    public String buildSelectExerciseAnswerByUuidSql(){
        return new SQL(){{
            SELECT("*");
            FROM(exerciseAns);
            WHERE("uuid=#{uuid}");
        }}.toString();
    }

    public String buildSelectExerciseAnswerByIdSql(){
        return new SQL(){{
            SELECT("*");
            FROM(exerciseAns);
            WHERE("id=#{id}");
        }}.toString();
    }

    public String buildInsertExerciseAnswerSql(){
        return new SQL(){{
            INSERT_INTO(exerciseAns);
            VALUES("uuid","#{exAns.uuid}");
            VALUES("user_id","#{exAns.userId}");
            VALUES("exercise_id","#{exAns.exerciseId}");
            VALUES("answer","#{exAns.answer}");
            VALUES("is_corrected","#{exAns.isCorrected}");
            VALUES("created_at","#{exAns.createdAt}");
        }}.toString();
    }

    public String buildUpdateExerciseAnswerByUuidSql(){
        return new SQL(){{
            UPDATE(exerciseAns);
            SET("user_id=#{exAns.userId}");
            SET("exercise_id=#{exAns.exerciseId}");
            SET("answer=#{exAns.answer}");
            SET("is_corrected=#{exAns.isCorrected}");
            WHERE("uuid=#{exAns.uuid}");
        }}.toString();
    }

    public String buildDeleteExerciseAnswerByUuidSql(){
        return new SQL(){{
            DELETE_FROM(exerciseAns);
            WHERE("uuid=#{uuid}");
        }}.toString();
    }
}
