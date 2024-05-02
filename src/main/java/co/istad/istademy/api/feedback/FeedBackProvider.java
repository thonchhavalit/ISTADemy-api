package co.istad.istademy.api.feedback;

import org.apache.ibatis.builder.annotation.ProviderMethodResolver;
import org.apache.ibatis.jdbc.SQL;

public class FeedBackProvider implements ProviderMethodResolver {
    private static final String table = "feedbacks";

    public String buildSelectFeedBackSql(){
        return new SQL(){{
            SELECT("*");
            FROM(table);
        }}.toString();
    }

    public String buildSelectFeedBackByIdSql(){
        return new SQL(){{
            SELECT("*");
            FROM(table);
            WHERE("id = #{id}");
        }}.toString();
    }

    public String buildSelectFeedBackByUuidSql(){
        return new SQL(){{
            SELECT("*");
            FROM(table);
            WHERE("uuid = #{uuid}");
        }}.toString();
    }

    public String buildInsertFeedBackSql(){
        return new SQL(){{
            INSERT_INTO(table);
            VALUES("uuid", "#{fd.uuid}");
            VALUES("user_id","#{fd.userId.id}");
            VALUES("contents","#{fd.contents}");
            VALUES("created_at","#{fd.createdAt}");
        }}.toString();
    }

    public String buildUpdateFeedBackByUuidSql(){
        return new SQL(){{
            UPDATE(table);
            SET(" user_id=#{fd.userId.id}");
            SET(" contents=#{fd.contents}");
            WHERE("uuid =#{fd.uuid}");
        }}.toString();
    }

    public String buildDeleteFeedBackByUuidSql(){
        return new SQL(){{
            DELETE_FROM(table);
            WHERE("uuid =#{uuid}");
        }}.toString();
    }
}
