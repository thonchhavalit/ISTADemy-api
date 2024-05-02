package co.istad.istademy.api.certificate.web;

import co.istad.istademy.api.certificate.CertificateService;
import co.istad.istademy.base.BaseRest;
import com.github.pagehelper.PageInfo;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/certificates")
public class CertificateRestController {
    private final CertificateService certService;

    @GetMapping
//    @PreAuthorize("SCOPE:")
    public BaseRest<?> selectAllCertificates(
            @RequestParam(required = false,defaultValue = "1",name = "page")int page,
            @RequestParam(required = false, defaultValue = "20", name = "limit")int limit
    ){
        PageInfo<CertificateDto> selectAll = certService.selectAllCertificates(page, limit);
        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .timestamp(LocalDateTime.now())
                .message("Successfully retrieved all certificates")
                .data(selectAll)
                .build();
    }

    @GetMapping("/{userId}")
    public BaseRest<?> getCertificateUser(@PathVariable Integer userId){
        CertificateDto certificateDto = certService.selectEachUserCertificate(userId);
        return BaseRest.builder()
                .code(HttpStatus.OK.value())
                .status(true)
                .timestamp(LocalDateTime.now())
                .data(certificateDto)
                .message("Get certificate for user is Successful!!!!")
                .build();
    }

    @PostMapping
    public BaseRest<?> insertCertificate(@Valid @RequestBody CreateCertificateDto createCertificateDto){
        CertificateDto insertCertificate = certService.insertCertificate(createCertificateDto);
        return BaseRest.builder()
                .code(HttpStatus.OK.value())
                .status(true)
                .timestamp(LocalDateTime.now())
                .data(insertCertificate)
                .message("Insert certificate is Successful!!!!")
                .build();
    }

    @PutMapping("/{uuid}")
    public BaseRest<?> updateCertificateByUuid(@PathVariable String uuid, @Valid @RequestBody CreateCertificateDto updateCertificateDto){
        CertificateDto updated = certService.updateCertificateByUuid(uuid, updateCertificateDto);
        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .timestamp(LocalDateTime.now())
                .data(updated)
                .message("Edit certificate is Successful!!!!!")
                .build();
    }

    @DeleteMapping("/{uuid}")
    public BaseRest<?> deleteCertificateByUuid(@PathVariable("uuid") String uuid){
        String isDeleted = certService.deleteCertificateByUuid(uuid);
        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .data(isDeleted)
                .timestamp(LocalDateTime.now())
                .message("Delete Certificate is Successful!!!")
                .build();
    }

    @GetMapping("/by/{uuid}")
    public BaseRest<?> selectCertificateByUuid(@PathVariable("uuid") String uuid){
        CertificateDto selectByUuid = certService.selectCertificateByUuid(uuid);
        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .data(selectByUuid)
                .timestamp(LocalDateTime.now())
                .message("Successfully get certificate by uuid!!!")
                .build();
    }

    @GetMapping("/{id}")
    public BaseRest<?> selectCertificateById(@PathVariable("id") Integer id){
        CertificateDto selectById = certService.selectCertificateById(id);
        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .data(selectById)
                .timestamp(LocalDateTime.now())
                .message("Successfully get certificate by id!!!")
                .build();
    }
}