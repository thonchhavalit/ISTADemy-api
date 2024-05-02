package co.istad.istademy.api.quiz;

import org.apache.ibatis.builder.annotation.ProviderMethodResolver;
import org.apache.ibatis.jdbc.SQL;

public class QuizProvider implements ProviderMethodResolver {
    private static final String table = "quizzes";

    public String buildSelectQuizSql(){
        return new SQL(){{
            SELECT("*");
            FROM(table);
        }}.toString();
    }

    public String buildSelectQuizByIdSql(){
        return new SQL(){{
            SELECT("*");
            FROM(table);
            WHERE("id = #{id}","is_disabled=false");
        }}.toString();
    }

    public String buildSelectQuizByUuidSql(){
        return new SQL(){{
            SELECT("*");
            FROM(table);
            WHERE("uuid = #{uuid}","is_disabled=false");
        }}.toString();
    }
    public String buildInsertQuizSql(){
        return new SQL(){{
            INSERT_INTO(table);
            VALUES("uuid","#{q.uuid}");
            VALUES("lesson_id","#{q.lesson.id}");
            VALUES("question","#{q.question}");
            VALUES("created_at","#{q.createdAt}");
        }}.toString();
    }
    public String buildUpdateQuizByUuidSql(){
        return new SQL(){{
            UPDATE(table);
            SET("lesson_id = #{q.lesson.id}");
            SET("question = #{q.question}");
            SET("updated_at = #{q.updatedAt}");
            WHERE("uuid = #{q.uuid}", "is_disabled=false");
        }}.toString();
    }
    public String buildDeleteQuizByUuidSql(){
        return new SQL(){{
            UPDATE(table);
            SET("is_disabled=true");
            WHERE("uuid = #{uuid}", "is_disabled=false");
        }}.toString();
    }

    public String buildSelectQuizByLessonIdSql(){
        return new SQL(){{
            SELECT("*");
            FROM(table);
            WHERE("lesson_id = #{id}", "is_disabled=false");
        }}.toString();
    }
}
