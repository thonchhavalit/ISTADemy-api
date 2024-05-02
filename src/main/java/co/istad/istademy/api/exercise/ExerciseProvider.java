package co.istad.istademy.api.exercise;

import org.apache.ibatis.builder.annotation.ProviderMethodResolver;
import org.apache.ibatis.jdbc.SQL;

public class ExerciseProvider implements ProviderMethodResolver {
    private static final String table = "exercises";

    public String buildSelectExerciseSql(){
        return new SQL(){{
            SELECT("*");
            FROM(table);
        }}.toString();
    }

    public String buildSelectExerciseByIdSql(){
        return new SQL(){{
            SELECT("*");
            FROM(table);
            WHERE("id = #{id}","is_disabled=false");
        }}.toString();
    }

    public String buildSelectExerciseByUuidSql(){
        return new SQL(){{
            SELECT("*");
            FROM(table);
            WHERE("uuid = #{uuid}","is_disabled=false");
        }}.toString();
    }

    public String buildInsertExerciseSql(){
        return new SQL(){{
            INSERT_INTO(table);
            VALUES("uuid","#{ex.uuid}");
            VALUES("lesson_id","#{ex.lesson.id}");
            VALUES("question","#{ex.question}");
            VALUES("correct_answer","#{ex.correctAnswer}");
            VALUES("prompt","#{ex.prompt}");
            VALUES("created_at","#{ex.createdAt}");
        }}.toString();
    }

    public String buildUpdateExerciseByUuidSql(){
        return new SQL(){{
            UPDATE(table);
            SET("lesson_id =#{ex.lesson.id}");
            SET("question =#{ex.question}");
            SET("correct_answer =#{ex.correctAnswer}");
            SET("prompt =#{ex.prompt}");
            SET("updated_at =#{ex.updatedAt}");
            WHERE("uuid =#{ex.uuid}" ,"is_disabled=false");
        }}.toString();
    }

    public String buildDeleteExerciseByUuidSql(){
        return new SQL(){{
            UPDATE(table);
            SET("is_disabled=true");
            WHERE("uuid =#{uuid}","is_disabled=false");
        }}.toString();
    }

    public String buildSelectExerciseByLessonIdSql(){
        return new SQL(){{
            SELECT("*");
            FROM(table);
            WHERE("lesson_id = #{id}" ,"is_disabled=false");
        }}.toString();
    }
}
