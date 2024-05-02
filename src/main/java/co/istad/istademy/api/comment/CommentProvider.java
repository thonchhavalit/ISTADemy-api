package co.istad.istademy.api.comment;

import org.apache.ibatis.builder.annotation.ProviderMethodResolver;
import org.apache.ibatis.jdbc.SQL;

public class CommentProvider implements ProviderMethodResolver {
    private static final String table = "comments";

    public String buildSelectCommentSql(Integer lessonId){
        return new SQL(){{
            SELECT("*");
            FROM(table);
            WHERE("parent_id IS NULL");
            if(lessonId !=0){
                WHERE("lesson_id=#{lessonId}");
            }
            ORDER_BY("created_at");
        }}.toString();
    }

    public String buildSelectCommentByUuid(){
        return new SQL(){{
            SELECT("*");
            FROM(table);
            WHERE("uuid=#{uuid}", "is_disabled=false");
        }}.toString();
    }

    public String buildSelectCommentById(){
        return new SQL(){{
            SELECT("*");
            FROM(table);
            WHERE("id=#{id}", "is_disabled=false");
        }}.toString();
    }

    public String buildInsertCommentSql(){
        return new SQL(){{
            INSERT_INTO(table);
            VALUES("uuid","#{cm.uuid}");
            VALUES("lesson_id","#{cm.lessonId.id}");
            VALUES("user_id","#{cm.userId.id}");
            VALUES("parent_id","#{cm.parentId.id}");
            VALUES("contents","#{cm.contents}");
            VALUES("created_at","#{cm.createdAt}");
        }}.toString();
    }

    public String buildUpdateCommentByUuidSql(){
        return new SQL(){{
            UPDATE(table);
            SET(" lesson_id=#{cm.lessonId.id}");
            SET(" user_id=#{cm.userId.id}");
            SET(" parent_id=#{cm.parentId}");
            SET(" contents=#{cm.contents}");
            SET(" updated_at=#{cm.updatedAt}");
            WHERE("uuid=#{cm.uuid}");
        }}.toString();
    }

    public String buildDeleteCommentByUuidSql(){
        return new SQL(){{
            UPDATE(table);
            SET("is_disabled=true");
            WHERE("uuid=#{uuid}", "is_disabled=false");
        }}.toString();
    }

    public String buildSelectCommentByUserIdSql(){
        return new SQL(){{
            SELECT("*");
            FROM(table);
            WHERE("user_id=#{id}", "is_disabled=false");
        }}.toString();
    }

    public String buildSelectCommentByLessonIdSql(){
        return new SQL(){{
            SELECT("*");
            FROM(table);
            WHERE("lesson_id=#{id}", "is_disabled=false");
        }}.toString();
    }
}
