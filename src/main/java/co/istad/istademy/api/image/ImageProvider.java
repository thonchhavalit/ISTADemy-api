package co.istad.istademy.api.image;

import org.apache.ibatis.builder.annotation.ProviderMethodResolver;
import org.apache.ibatis.jdbc.SQL;

public class ImageProvider implements ProviderMethodResolver {

    private static final String imageTb = "images";

    public static String buildSelectImageSql(){
        return new SQL(){{
            SELECT("*");
            FROM(imageTb);
        }}.toString();
    }

    public String buildSelectImageByIdSql(){
        return new SQL(){{
            SELECT("*");
            FROM(imageTb);
            WHERE("id=#{id}", "is_disabled=false");
        }}.toString();
    }

    public String buildSelectImageByUuidSql(){
        return new SQL(){{
            SELECT("*");
            FROM(imageTb);
            WHERE("uuid=#{uuid}", "is_disabled=false");
        }}.toString();
    }

    public String buildInsertImageSql(){
        return new SQL(){{
            INSERT_INTO(imageTb);
            VALUES("uuid" ,"#{i.uuid}");
            VALUES("name" ,"#{i.name}");
            VALUES("url" ,"#{i.url}");
            VALUES("created_at" ,"#{i.createdAt}");
        }}.toString();
    }

    public String buildUpdateImageByUuidSql(){
        return new SQL(){{
            UPDATE(imageTb);
            SET("name=#{i.name}");
            SET("url=#{i.url}");
            WHERE("uuid=#{i.uuid}");
        }}.toString();
    }

    public String buildDeleteImageByUuidSql(){
        return new SQL(){{
            UPDATE(imageTb);
            SET("is_disabled=true");
            WHERE("uuid=#{uuid}", "is_disabled=false");
        }}.toString();
    }

}
