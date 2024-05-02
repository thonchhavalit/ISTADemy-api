package co.istad.istademy.api.user;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;

public class UserProvider {
    private static final String tableName = "users";

    public static String buildCreateUser(){
        return new SQL()
                .INSERT_INTO(tableName)
                .VALUES("uuid", "#{u.uuid}")
                .VALUES("username","#{u.username}")
                .VALUES("email","#{u.email}")
                .VALUES("password","#{u.password}")
                .VALUES("dob","#{u.dob}")
                .VALUES("profile","#{u.profile.uuid}")
                .VALUES("github_url","#{u.githubUrl}")
                .VALUES("bio","#{u.bio}")
                .VALUES("location","#{u.location}")
                .toString();
    }
    public String buildGetAllUsersSql(@Param("username") String username,@Param("email")String email) {
        return new SQL() {{
            SELECT("*");
            FROM(tableName);
            WHERE("username ILIKE '%' || #{username} || '%'" ," is_disabled = FALSE");
            AND();
            WHERE("email ILIKE '%' || #{email} || '%'","is_disabled = FALSE");
        }}.toString();
    }
    public String buildFindUserByIdSql() {
        return new SQL() {{
            SELECT("*");
            FROM(tableName);
            WHERE("id=#{id}", "is_disabled=FALSE");
        }}.toString();
    }

    public String buildFindUserByUuidSql() {
        return new SQL() {{
            SELECT("*");
            FROM(tableName);
            WHERE("uuid=#{uuid}", "is_disabled=FALSE");
        }}.toString();
    }

    public String buildDeleteUserByUuidSql() {
        return new SQL() {{
            DELETE_FROM(tableName);
            WHERE("uuid=#{uuid}");
        }}.toString();
    }
    public String buildUpdateUserByUuidSql() {
        return new SQL() {{
            UPDATE(tableName);
            SET("username=#{u.username}");
            SET("email=#{u.email}");
            SET("profile=#{u.profile.uuid}");
            SET("location=#{u.location}");
            SET("dob=#{u.dob}");
            SET("github_url=#{u.githubUrl}");
            SET("bio=#{u.bio}");
            WHERE("uuid=#{u.uuid}");
        }}.toString();
    }
}
