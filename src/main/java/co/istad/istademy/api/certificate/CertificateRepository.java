package co.istad.istademy.api.certificate;

import co.istad.istademy.api.course.Course;
import co.istad.istademy.api.user.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Mapper
@Repository
public interface CertificateRepository {

    @SelectProvider(CertificateProvider.class)
    @Results(
            id = "certificateMapper",
            value = {
                    @Result(column = "user_id", property = "user", one = @One(select = "buildSelectUserByCertificateIdSql")),
                    @Result(column = "course_id",property = "course", one = @One(select = "buildSelectCourseByCertificateIdSQL")),
                    @Result(column = "date_earned", property = "dateEarned")
            }
    )
    List<Certificate> buildSelectAllCertificate();

    @Select("select * from courses where id=#{id}")
    Course buildSelectCourseByCertificateIdSQL(Integer id);
    @Select("select * from users where id=#{id}")
    User buildSelectUserByCertificateIdSql(Integer id);


    @SelectProvider(CertificateProvider.class)
    @ResultMap("certificateMapper")
    Optional<Certificate> buildSelectEachUserByIdSql(Integer userId);

    @SelectProvider(CertificateProvider.class)
    @ResultMap("certificateMapper")
    Optional<Certificate> buildSelectCertificateByUuidSql(String uuid);

    @SelectProvider(CertificateProvider.class)
    @ResultMap("certificateMapper")
    Optional<Certificate> buildSelectCertificateByIdSql(Integer id);


    @InsertProvider(CertificateProvider.class)
    @Options(useGeneratedKeys = true,keyColumn = "id", keyProperty = "id")
    boolean buildCreateCertificateSql(@Param("ct") Certificate certificate);

    @UpdateProvider(CertificateProvider.class)
    boolean buildEditCertificateSql(@Param("ct") Certificate certificate);

    @DeleteProvider(CertificateProvider.class)
    boolean buildDeleteCertificateSql(@Param("uuid") String uuid);
}
