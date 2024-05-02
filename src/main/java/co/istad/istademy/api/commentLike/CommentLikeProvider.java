package co.istad.istademy.api.commentLike;

import org.apache.ibatis.builder.annotation.ProviderMethodResolver;
import org.apache.ibatis.jdbc.SQL;

public class CommentLikeProvider implements ProviderMethodResolver {

    private static final String comLikeTable = "comment_likes";

    public static String buildSelectCommentLikeSql(int cmtId){
        return new SQL(){{
            SELECT("*");
            FROM(comLikeTable);
            if(cmtId != 0){
                WHERE("comment_id=#{cmtId}");
            }
        }}.toString();
    }

    public String buildSelectCommentLikeByUuidSql(){
        return new SQL(){{
            SELECT("*");
            FROM(comLikeTable);
            WHERE("uuid=#{uuid}");
        }}.toString();
    }

    public String buildSelectCommentLikeByIdSql(){
        return new SQL(){{
            SELECT("*");
            FROM(comLikeTable);
            WHERE("id=#{id}");
        }}.toString();
    }

    public String buildInsertCommentLikeSql(){
        return new SQL(){{
            INSERT_INTO(comLikeTable);
            VALUES("uuid","#{cml.uuid}");
            VALUES("comment_id","#{cml.commentId}");
            VALUES("user_id","#{cml.userId}");
            VALUES("created_at","#{cml.createdAt}");
        }}.toString();
    }

    public String buildUpdateCommentLikeByUuidSql(){
        return new SQL(){{
            UPDATE(comLikeTable);
            SET(" comment_id=#{cml.commentId}");
            SET(" user_id=#{cml.userId}");
            SET(" updated_at=#{cml.updatedAt}");
            WHERE("uuid=#{cml.uuid}");
        }}.toString();
    }

    public String buildDeleteCommentLikeByUuidSql(){
        return new SQL(){{
            DELETE_FROM(comLikeTable);
            WHERE("uuid=#{uuid}");
        }}.toString();
    }
}
