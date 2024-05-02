package co.istad.istademy.api.section;

import org.apache.ibatis.builder.annotation.ProviderMethodResolver;
import org.apache.ibatis.jdbc.SQL;


public class SectionProvider implements ProviderMethodResolver {


    private static final String table = "sections";
    public String buildSelectSectionSql(int courseId){

        return new SQL(){{
            SELECT("*");
            FROM(table);
            ORDER_BY("created_at");
            if(courseId != 0){
                WHERE("course_id = #{courseId}");
            }
        }}.toString();
    }

    public String buildSelectSectionByIdSql(){
        return new SQL(){{
            SELECT("*");
            FROM(table);
            WHERE("id = #{id}","is_disabled=false");
        }}.toString();
    }

    public String buildSelectSectionByUuidSql(){
        return new SQL(){{
            SELECT("*");
            FROM(table);
            WHERE("uuid = #{uuid}","is_disabled=false");
        }}.toString();
    }

    public String buildSelectSectionByCourseIdSql(){
        return new SQL(){{
            SELECT("*");
            FROM(table);
            WHERE("course_id = #{id}","is_disabled=false");
        }}.toString();
    }

    public String buildCreateSectionSql() {
        return new SQL() {{
            INSERT_INTO(table);
            VALUES("uuid", "#{s.uuid}");
            VALUES("course_id", "#{s.course}");
            VALUES("title", "#{s.title}");
            VALUES("description", "#{s.description}");
            VALUES("created_at","#{s.createdAt}");
        }}.toString();
    }

    public String buildUpdateSectionByUuidSql(){
        return new SQL(){{
            UPDATE(table);
            SET("course_id = #{s.course}");
            SET("title = #{s.title}");
            SET("description = #{s.description}");
            SET("updated_at = #{s.updatedAt}");
            WHERE("uuid = #{s.uuid}", "is_disabled=false");
        }}.toString();
    }

    public String buildDeleteSectionByUuidSql(){
        return new SQL(){{
            UPDATE(table);
            SET("is_disabled=true");
            WHERE("uuid = #{uuid}", "is_disabled=false");
        }}.toString();
    }


}