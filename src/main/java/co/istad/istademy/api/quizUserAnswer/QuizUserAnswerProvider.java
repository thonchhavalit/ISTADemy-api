package co.istad.istademy.api.quizUserAnswer;

import org.apache.ibatis.builder.annotation.ProviderMethodResolver;
import org.apache.ibatis.jdbc.SQL;

public class QuizUserAnswerProvider implements ProviderMethodResolver {

    private static final String quizAnsTable = "quiz_user_answers";

    public static String buildSelectQuizUserAnswerSql(){
        return new SQL(){{
            SELECT("*");
            FROM(quizAnsTable);
        }}.toString();
    }
    public String buildSelectQuizUserAnswerByUuidSql(){
        return new SQL(){{
            SELECT("*");
            FROM(quizAnsTable);
            WHERE("uuid=#{uuid}");
        }}.toString();
    }

    public String buildSelectQuizUserAnswerByIdSql(){
        return new SQL(){{
            SELECT("*");
            FROM(quizAnsTable);
            WHERE("id=#{id}");
        }}.toString();
    }

    public String buildInsertQuizUserAnswerSql(){
        return new SQL(){{
            INSERT_INTO(quizAnsTable);
            VALUES("uuid","#{qAns.uuid}");
            VALUES("user_id","#{qAns.userId}");
            VALUES("quiz_id","#{qAns.quizId}");
            VALUES("option_id","#{qAns.optionId}");
        }}.toString();
    }

    public String buildUpdateQuizUserAnswerByUuidSql(){
        return new SQL(){{
            UPDATE(quizAnsTable);
            SET("user_id=#{qAns.userId}");
            SET("quiz_id=#{qAns.quizId}");
            SET("option_id=#{qAns.optionId}");
            WHERE("uuid=#{qAns.uuid}");
        }}.toString();
    }

    public String buildDeleteQuizUserAnswerByUuidSql(){
        return new SQL(){{
            DELETE_FROM(quizAnsTable);
            WHERE("uuid=#{uuid}");
        }}.toString();
    }
}
