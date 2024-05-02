package co.istad.istademy.api.certificate;


import co.istad.istademy.api.certificate.web.CertificateDto;
import co.istad.istademy.api.certificate.web.CreateCertificateDto;
import com.github.pagehelper.PageInfo;

public interface CertificateService {
    /**
     * use to retrieve all certificates from database and response with pagination
     *
     * @param page  : location page
     * @param limit : size of page
     * @return PageInfo of Tutorial is pagination
     */

    PageInfo<CertificateDto> selectAllCertificates(int page, int limit);

    /**
     * insert certificate
     *
     * @param createCertificateDto : is data need to insert and require
     * @return CertificateDto define response data
     */
    CertificateDto insertCertificate(CreateCertificateDto createCertificateDto);

    /**
     * select username , title, date_earned by userId
     *
     * @param id : needed id in order to do the searching
     * @return CertificateDto define response data
     */
    CertificateDto selectCertificateById(Integer id);

    /**
     * select certificate by uuid
     *
     * @param uuid : needed id in order to do the searching
     * @return CertificateDto define response data
     */
    CertificateDto selectCertificateByUuid(String uuid);

    /**
     * use to delete certificate by uuid, and it just updates status from false to true
     *
     * @param uuid is required to search before delete
     * @return uuid they deleted
     */
    String deleteCertificateByUuid(String uuid);

    /**
     * update certificate
     *
     * @param updateCertificateDto : data need to update
     * @return :CertificateDto use to response that necessary to response
     */
    CertificateDto updateCertificateByUuid(String uuid, CreateCertificateDto updateCertificateDto);

    CertificateDto selectEachUserCertificate(Integer userId);
}
