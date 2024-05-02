package co.istad.istademy.api.lesson;


import org.apache.ibatis.builder.annotation.ProviderMethodResolver;
import org.apache.ibatis.jdbc.SQL;



public class LessonProvider implements ProviderMethodResolver {

    private static final String table = "lessons";
    public String buildSelectAllLessonSql(Integer sectionId){
        return new SQL(){{
            SELECT("*");
            FROM(table);
            ORDER_BY("created_at");
            if(sectionId != 0){
                WHERE("section_id = #{sectionId}");
            }
        }}.toString();
    }

    public String buildSelectLessonByIdSql(){
        return new SQL(){{
            SELECT("*");
            FROM(table);
            WHERE("id = #{id}","is_disabled=false");
        }}.toString();
    }

    public String buildSelectLessonByUuidSql(){
        return new SQL(){{
            SELECT("*");
            FROM(table);
            WHERE("uuid = #{uuid}","is_disabled=false");
        }}.toString();
    }

    public String buildCreateLessonSql(){
        return new SQL(){{
            INSERT_INTO(table);
            VALUES("uuid","#{l.uuid}");
            VALUES("section_id","#{l.section}");
            VALUES("title","#{l.title}");
            VALUES("description","#{l.description}");
            VALUES("created_at","#{l.createdAt}");
        }}.toString();
    }

    public String buildUpdateLessonByUuidSql(){
        return new SQL(){{
            UPDATE(table);
            SET("section_id = #{l.section}");
            SET("title = #{l.title}");
            SET("description = #{l.description}");
            SET("updated_at = #{l.updatedAt}");
            WHERE("uuid=#{l.uuid}", "is_disabled=false");
        }}.toString();
    }
    public String buildDeleteLessonByUuidSql(){
        return new SQL(){{
            UPDATE(table);
            SET("is_disabled=true");
            WHERE("uuid = #{uuid}", "is_disabled=false");
        }}.toString();
    }

    public String buildSelectLessonBySectionIdSql(){
        return new SQL(){{
            SELECT("*");
            FROM(table);
            WHERE("section_id = #{id}");
        }}.toString();
    }
}