package co.istad.istademy.api.level;

import org.apache.ibatis.builder.annotation.ProviderMethodResolver;
import org.apache.ibatis.jdbc.SQL;

public class LevelProvider implements ProviderMethodResolver {

    private static final String levelTb = "levels";

    public static String buildSelectLevelSql(){
        return new SQL(){{
            SELECT("*");
            FROM(levelTb);
        }}.toString();
    }

    public String buildSelectLevelByIdSql(){
        return new SQL(){{
            SELECT("*");
            FROM(levelTb);
            WHERE("id=#{id}");
        }}.toString();
    }
    public String buildSelectLevelByUuidSql(){
        return new SQL(){{
            SELECT("*");
            FROM(levelTb);
            WHERE("uuid=#{uuid}");
        }}.toString();
    }

    public String buildAddLevelSql(){
        return new SQL(){{
            INSERT_INTO(levelTb);
            VALUES("uuid", "#{l.uuid}");
            VALUES("name","#{l.name}");
        }}.toString();
    }

    public String buildEditLevelSql(){
        return new SQL(){{
            UPDATE(levelTb);
            SET("name = #{l.name}");
            WHERE("uuid=#{l.uuid}");
        }}.toString();
    }

    public String buildDeleteLevelSql(){
        return new SQL(){{
            DELETE_FROM(levelTb);
            WHERE("uuid=#{uuid}");
        }}.toString();
    }

}
