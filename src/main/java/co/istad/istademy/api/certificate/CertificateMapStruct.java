package co.istad.istademy.api.certificate;


import co.istad.istademy.api.certificate.web.CertificateDto;
import co.istad.istademy.api.certificate.web.CreateCertificateDto;
import com.github.pagehelper.PageInfo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface CertificateMapStruct {
    CertificateDto CertificateToCertificateDto(Certificate model);
    @Mappings({
            @Mapping(source = "user", target = "user.id"),
            @Mapping(source = "course", target = "course.id")
    })
    Certificate createCertificateDtoToCertificate(CreateCertificateDto model);
    PageInfo<CertificateDto> pageInfoCertificateToPageInfoCertificateDto(PageInfo<Certificate> model);
}
