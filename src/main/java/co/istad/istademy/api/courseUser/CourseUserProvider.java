package co.istad.istademy.api.courseUser;

import co.istad.istademy.api.user.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.builder.annotation.ProviderMethodResolver;
import org.apache.ibatis.jdbc.SQL;

public class CourseUserProvider implements ProviderMethodResolver {
    private static final String courseUserTable = "course_users";

    public static String buildSelectCourseUserSql(){
        return new SQL(){{
            SELECT("*");
            FROM(courseUserTable);
        }}.toString();
    }


    public String buildSelectCourseUserByUuidSql(){
        return new SQL(){{
            SELECT("*");
            FROM(courseUserTable);
            WHERE("uuid=#{uuid}");
        }}.toString();
    }

    public String buildCreateCourseUserSql(){
        return new SQL(){{
            INSERT_INTO(courseUserTable);
            VALUES("uuid","#{cu.uuid}");
            VALUES("course_id", "#{cu.courseId}");
            VALUES("user_id", "#{cu.userId}");
            VALUES("enrolled_at", "#{cu.enrolledAt}");
        }}.toString();
    }

    public String buildEditCourseUserSql(){
        return new SQL(){{
            UPDATE(courseUserTable);
            SET(" course_id=#{cu.courseId}");
            SET(" user_id=#{cu.userId}");
            SET(" finished_at=#{cu.finishedAt}");
            WHERE("uuid=#{cu.uuid}");
        }}.toString();
    }

    public String buildDeleteCourseUserByUuidSql(){
        return new SQL(){{
            DELETE_FROM(courseUserTable);
            WHERE("uuid=#{uuid}");
        }}.toString();
    }

    public String buildFindCourseByUserIdSql(@Param("u")User user,@Param("cu")CourseUser courseUser){
        return new SQL(){{
            SELECT("c.*");
            FROM("courses as c");
            INNER_JOIN("course_users as cu on c.id = cu.course_id");
            INNER_JOIN("users as u on u.id = cu.user_id");
            WHERE("cu.user_id=#{u.id}");
        }}.toString();
    }
}
