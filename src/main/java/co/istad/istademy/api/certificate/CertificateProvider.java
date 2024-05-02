package co.istad.istademy.api.certificate;

import org.apache.ibatis.builder.annotation.ProviderMethodResolver;
import org.apache.ibatis.jdbc.SQL;

public class CertificateProvider implements ProviderMethodResolver {

    public String buildSelectAllCertificate(){
        return new SQL(){{
            SELECT("*");
            FROM("certificates");
        }}.toString();
    }

    public String buildSelectEachUserByIdSql(){
        return new SQL(){{
            SELECT("*");
            FROM("certificates as ct");
            INNER_JOIN("courses as c ON c.id = ct.course_id");
            INNER_JOIN("users as u ON u.id = ct.user_id");
            WHERE("u.id=#{id}");
            LIMIT(1);
        }}.toString();
    }

    public String buildSelectCertificateByUuidSql(){
        return new SQL(){{
            SELECT("*");
            FROM("certificates");
            WHERE("uuid=#{uuid}");
        }}.toString();
    }

    public String buildSelectCertificateByIdSql(){
        return new SQL(){{
            SELECT("*");
            FROM("certificates");
            WHERE("id=#{id}");
        }}.toString();
    }

    public String buildCreateCertificateSql(){
        return new SQL(){{
            INSERT_INTO("certificates as ct");
            VALUES("uuid","#{ct.uuid}");
            VALUES("user_id","#{ct.user.id}");
            VALUES("course_id","#{ct.course.id}");
            VALUES("date_earned","#{ct.dateEarned}");
        }}.toString();
    }
    public String buildEditCertificateSql(){
        return new SQL(){{
            UPDATE("certificates as ct");
            SET("user_id =#{ct.user.id}");
            SET("course_id=#{ct.course.id}");
            SET("date_earned=#{ct.dateEarned}");
            WHERE("uuid=#{ct.uuid}");
        }}.toString();
    }
    public String buildDeleteCertificateSql(){
        return new SQL(){{
            DELETE_FROM("certificates");
            WHERE("uuid=#{uuid}");
        }}.toString();
    }
}
