package co.istad.istademy.api.contentProgress;


import org.apache.ibatis.builder.annotation.ProviderMethodResolver;
import org.apache.ibatis.jdbc.SQL;

public class ContentProgressProvider implements ProviderMethodResolver {

    private static final String table = "content_progresses";

    public String buildSelectContentProgressesSql(Integer userId, Integer contentId){
        return new SQL(){{
            SELECT("*");
            FROM(table);
            if(userId !=0 && contentId!=0) {
                WHERE("user_id=#{userId}", "content_id=#{contentId}");
            }
        }}.toString();
    }

  public String buildSetIsContentReadSql() {
        return new SQL(){{
            INSERT_INTO(table);
            VALUES("uuid","#{cp.uuid}");
            VALUES("user_id","#{cp.userId}");
            VALUES("content_id","#{cp.contentId}");
            VALUES("course_id","#{cp.courseId}");
            VALUES("is_read","#{cp.isRead}");
        }}.toString();
  }

}
