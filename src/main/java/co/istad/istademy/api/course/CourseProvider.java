package co.istad.istademy.api.course;

import org.apache.ibatis.builder.annotation.ProviderMethodResolver;
import org.apache.ibatis.jdbc.SQL;

public class CourseProvider implements ProviderMethodResolver {

    private static final String tblName = "courses";

    public static String buildSelectCoursesSql(){
        return new SQL(){{
            SELECT("*");
            FROM(tblName);
            ORDER_BY("created_at");
        }}.toString();
    }
    public String buildInsertCourseSql() {

        return new SQL() {{
            INSERT_INTO(tblName);
            VALUES("uuid", "#{c.uuid}");
            VALUES("title", "#{c.title}");
            VALUES("description", "#{c.description}");
            VALUES("level_id", "#{c.level.id}");
            VALUES("image", "#{c.image}");
            VALUES("required_time", "#{c.requiredTime}");
            VALUES("created_at", "#{c.createdAt}");
        }}.toString();
    }

    public String buildFindCourseByIdSql() {
        return new SQL() {{
            SELECT("*");
            FROM(tblName);
            WHERE("id=#{id}", "is_disabled=false");
        }}.toString();
    }

    public String buildFindCourseByUuidSql() {
        return new SQL() {{
            SELECT("*");
            FROM(tblName);
            WHERE("uuid=#{uuid}", "is_disabled=false");
        }}.toString();
    }


    public String buildSelectCourseByLevelId() {
        return new SQL() {{
            SELECT("*");
            FROM(tblName);
            WHERE("level_id=#{id}", "is_disabled=false");
        }}.toString();
    }

    public String buildDeleteCourseByUuidSql() {
        return new SQL() {{
            UPDATE(tblName);
            SET("is_disabled=true");
            WHERE("uuid=#{uuid}", "is_disabled=false");
        }}.toString();
    }

    public String buildUpdateCourseByUuidSql() {
        return new SQL() {{
            UPDATE(tblName);
            SET("title=#{c.title}");
            SET("description=#{c.description}");
            SET("image=#{c.image}");
            SET("level_id=#{c.level.id}");
            SET("required_time=#{c.requiredTime}");
            SET("updated_at=#{c.updatedAt}");
            WHERE("uuid=#{c.uuid}", "is_disabled=false");
        }}.toString();
    }

}