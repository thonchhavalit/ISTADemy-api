package co.istad.istademy.api.example;

import org.apache.ibatis.builder.annotation.ProviderMethodResolver;
import org.apache.ibatis.jdbc.SQL;

public class ExampleProvider implements ProviderMethodResolver {
    private static final String table = "examples";

    public String buildSelectExampleSql(){
        return new SQL(){{
            SELECT("*");
            FROM(table);
        }}.toString();
    }

    public String buildSelectExampleByIdSql(){
        return new SQL(){{
            SELECT("*");
            FROM(table);
            WHERE("id=#{id}", "is_disabled=false");
        }}.toString();
    }

    public String buildSelectExampleByUuidSql(){
        return new SQL(){{
            SELECT("*");
            FROM(table);
            WHERE("uuid=#{uuid}", "is_disabled=false");
        }}.toString();
    }

    public String buildInsertExampleSql(){
        return new SQL(){{
            INSERT_INTO(table);
            VALUES("uuid", "#{ex.uuid}");
            VALUES("lesson_id", "#{ex.lessonId.id}");
            VALUES("example_code", "#{ex.exampleCode}");
            VALUES("description", "#{ex.description}");
            VALUES("output", "#{ex.output}");
            VALUES("created_at", "#{ex.createdAt}");
        }}.toString();
    }

    public String buildUpdatedExampleByUuidSql(){
        return new SQL(){{
            UPDATE(table);
            SET("lesson_id=#{ex.lessonId.id}");
            SET("example_code=#{ex.exampleCode}");
            SET("description=#{ex.description}");
            SET("output=#{ex.output}");
            SET("updated_at=#{ex.updatedAt}");
            WHERE("uuid=#{ex.uuid}", "is_disabled=false");
        }}.toString();
    }

    public String buildDeleteExampleByUuidSql(){
        return new SQL(){{
            UPDATE(table);
            SET("is_disabled=true");
            WHERE("uuid=#{uuid}", "is_disabled=false");
        }}.toString();
    }
    public String buildSelectExampleByLessonIdSql(){
        return new SQL(){{
            SELECT("*");
            FROM(table);
            WHERE("lesson_id=#{id}","is_disabled=false");
        }}.toString();
    }
}
