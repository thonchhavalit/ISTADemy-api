package co.istad.istademy.api.certificate;

import co.istad.istademy.api.certificate.web.CertificateDto;
import co.istad.istademy.api.certificate.web.CreateCertificateDto;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.sql.Timestamp;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class CertificateServiceImpl implements CertificateService{
    private final CertificateRepository certificateRepo;
    private final CertificateMapStruct mapStruct;
    @Override
    public PageInfo<CertificateDto> selectAllCertificates(int page, int limit) {
        PageInfo<Certificate> certificatePageInfo = PageHelper.startPage(page, limit).doSelectPageInfo(certificateRepo::buildSelectAllCertificate);
        return mapStruct.pageInfoCertificateToPageInfoCertificateDto(certificatePageInfo);
    }

    @Override
    public CertificateDto insertCertificate(CreateCertificateDto createCertificateDto) {
        Certificate certificate = mapStruct.createCertificateDtoToCertificate(createCertificateDto);
        certificate.setUuid(UUID.randomUUID().toString());
        certificate.setDateEarned(new Timestamp(System.currentTimeMillis()));
        if (certificateRepo.buildCreateCertificateSql(certificate)){
            return this.selectCertificateById(certificate.getId());
        }
        else {
         throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,"Fail to create new certificate!!!!");
        }
    }

    @Override
    public CertificateDto selectCertificateById(Integer id) {
        Certificate certificate = certificateRepo.buildSelectCertificateByIdSql(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,String.format("Undefined certificate with id %d",id)));
        return mapStruct.CertificateToCertificateDto(certificate);
    }

    @Override
    public CertificateDto selectCertificateByUuid(String uuid) {
        Certificate certificate = certificateRepo.buildSelectCertificateByUuidSql(uuid).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,String.format("Undefined certificate with uuid %s", uuid)));
        return mapStruct.CertificateToCertificateDto(certificate);
    }

    @Override
    public String deleteCertificateByUuid(String uuid) {
        if (certificateRepo.buildDeleteCertificateSql(uuid)){
            return uuid;
        }
        throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Fail to delete certificate!!!");
    }

    @Override
    public CertificateDto updateCertificateByUuid(String uuid, CreateCertificateDto updateCertificateDto) {
        Certificate certificate = mapStruct.createCertificateDtoToCertificate(updateCertificateDto);
        certificate.setDateEarned(new Timestamp(System.currentTimeMillis()));
        certificate.setUuid(uuid);
        if (certificateRepo.buildEditCertificateSql(certificate)){
            return this.selectCertificateByUuid(uuid);
        }
        throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Fail to edit certificate!!!");
    }

    @Override
    public CertificateDto selectEachUserCertificate(Integer userId) {
        Certificate certificate = certificateRepo.buildSelectEachUserByIdSql(userId).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,String.format("Undefined certificate each user by userId %d", userId)));
        return mapStruct.CertificateToCertificateDto(certificate);
    }

}
