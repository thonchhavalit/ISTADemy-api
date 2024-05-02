package co.istad.istademy.api.quizOption;

import org.apache.ibatis.builder.annotation.ProviderMethodResolver;
import org.apache.ibatis.jdbc.SQL;

public class QuizOptionProvider implements ProviderMethodResolver {

    private static final String quizOp = "quiz_options";

    public static String buildSelectQuizOptionSql(){
        return new SQL(){{
            SELECT("*");
            FROM(quizOp);
        }}.toString();
    }

    public String buildSelectQuizOptionByUuidSql(){
        return new SQL(){{
            SELECT("*");
            FROM(quizOp);
            WHERE("uuid=#{uuid}");
        }}.toString();
    }
    public String buildSelectQuizOptionByIdSql(){
        return new SQL(){{
            SELECT("*");
            FROM(quizOp);
            WHERE("id=#{id}");
        }}.toString();
    }

    public String buildInsertQuizOptionSql(){
        return new SQL(){{
            INSERT_INTO(quizOp);
            VALUES("uuid", "#{quo.uuid}");
            VALUES("quiz_id", "#{quo.quizId}");
            VALUES("choice", "#{quo.choice}");
            VALUES("is_corrected", "#{quo.isCorrected}");
        }}.toString();
    }

    public String buildUpdateQuizOptionByUuidSql(){
        return new SQL(){{
            UPDATE(quizOp);
            SET("quiz_id=#{quo.quizId}");
            SET("choice=#{quo.choice}");
            SET("is_corrected=#{quo.isCorrected}");
            WHERE("uuid=#{quo.uuid}");
        }}.toString();
    }

    public String buildDeleteQuizOptionByUuidSql(){
        return new SQL(){{
            DELETE_FROM(quizOp);
            WHERE("uuid=#{uuid}");
        }}.toString();
    }
}
