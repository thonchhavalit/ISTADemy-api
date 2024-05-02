package co.istad.istademy.api.content;

import org.apache.ibatis.builder.annotation.ProviderMethodResolver;
import org.apache.ibatis.jdbc.SQL;

public class ContentProvider implements ProviderMethodResolver {

    private static final String table = "contents";

    public String buildSelectContentSql(){
        return new SQL(){{
            SELECT("*");
            FROM(table);
        }}.toString();
    }

    public String buildSelectContentByUuidSql(){
        return new SQL(){{
            SELECT("*");
            FROM(table);
            WHERE("uuid=#{uuid}", "is_disabled=false");
        }}.toString();
    }

    public String buildSelectContentByIdSql(){
        return new SQL(){{
            SELECT("*");
            FROM(table);
            WHERE("id=#{id}", "is_disabled=false");
            ORDER_BY("created_at");
        }}.toString();
    }

    public String buildInsertContentSql(){
        return new SQL(){{
            INSERT_INTO(table);
            VALUES("uuid","#{cn.uuid}");
            VALUES("lesson_id","#{cn.lesson.id}");
            VALUES("is_code","#{cn.isCode}");
            VALUES("content","#{cn.content}");
            VALUES("created_at","#{cn.createdAt}");
        }}.toString();
    }

    public String buildUpdateContentByUuidSql(){
        return new SQL(){{
            UPDATE(table);
            SET(" lesson_id=#{cn.lesson.id}");
            SET(" content=#{cn.content}");
            VALUES("is_code","#{cn.isCode}");
            SET(" updated_at=#{cn.updatedAt}");
            WHERE("uuid=#{cn.uuid}", "is_disabled=false");
        }}.toString();
    }

    public String buildDeleteContentByUuidSql(){
        return new SQL(){{
            UPDATE(table);
            SET("is_disabled=true");
            WHERE("uuid=#{uuid}", "is_disabled=false");
        }}.toString();
    }

    public String buildSelectContentByLessonIdSql(){
        return new SQL(){{
        SELECT("*");
        FROM(table);
        WHERE("lesson_id = #{id}");
        }}.toString();
    }
}
