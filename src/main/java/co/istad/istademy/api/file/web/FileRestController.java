package co.istad.istademy.api.file.web;

import co.istad.istademy.api.file.FileService;
import co.istad.istademy.base.BaseRest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/v1/files")
@Slf4j
@RequiredArgsConstructor
public class FileRestController {
    private final FileService fileService;
    
    String uploadSuccessMsg = "Successfully uploaded file!";

    @PostMapping()
    public BaseRest<?> uploadSingle(@RequestPart MultipartFile file) {
        FileDto fileDto = fileService.uploadSingle(file);
        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .message(uploadSuccessMsg)
                .timestamp(LocalDateTime.now())
                .data(fileDto)
                .build();
    }
    
    @PostMapping("/upload-file-base64")
    public BaseRest<?> uploadFileBase64(@RequestBody String image) {
        FileDto fileDto = fileService.uploadFileBase64(image);
        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .message(uploadSuccessMsg)
                .timestamp(LocalDateTime.now())
                .data(fileDto)
                .build();
    }

    @PostMapping("/multiple")
    public BaseRest<?> uploadMultiple(@RequestPart("files") List<MultipartFile> files) {
        var result = fileService.uploadMultiple(files);
        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .message(uploadSuccessMsg)
                .timestamp(LocalDateTime.now())
                .data(result)
                .build();
    }

    @GetMapping
    public BaseRest<?> findAll() {
        List<FileDto> resultFiles = fileService.findAll();
        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .message("The file has been located successfully!")
                .timestamp(LocalDateTime.now())
                .data(resultFiles)
                .build();
    }

    @GetMapping("/{name}")
    public BaseRest<?> findByName(@PathVariable("name") String name) {
        System.out.println("filename : " + name);
        FileDto resultFiles = fileService.findByName(name);
        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .message("The file has been located successfully!")
                .timestamp(LocalDateTime.now())
                .data(resultFiles)
                .build();
    }

    @DeleteMapping("/{name}")
    public BaseRest<?> deleteByName(@PathVariable("name") String name) {
        String resultFiles = fileService.deleteByName(name);
        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .message("The file has been deleted successfully!")
                .timestamp(LocalDateTime.now())
                .data(resultFiles)
                .build();
    }

    @DeleteMapping
    public BaseRest<?> removeAllFiles() {
        boolean resultFiles = fileService.removeAllFiles();
        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .message("The files has been deleted successfully!")
                .timestamp(LocalDateTime.now())
                .data(resultFiles)
                .build();
    }

    @GetMapping("/download/{name}")
    public ResponseEntity<?> download(@PathVariable String name) {
        Resource resource = fileService.download(name);
        return ResponseEntity.ok().
                contentType(MediaType.parseMediaType(MediaType.APPLICATION_OCTET_STREAM_VALUE))
                .header(HttpHeaders.CONTENT_DISPOSITION,"attachment; filename=\""+ resource.getFilename()+"\"")
                .body(resource);
    }
}
