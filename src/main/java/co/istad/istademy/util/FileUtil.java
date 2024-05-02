package co.istad.istademy.util;

import co.istad.istademy.api.file.web.FileBase64Dto;
import co.istad.istademy.api.file.web.FileDto;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import jakarta.xml.bind.DatatypeConverter;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.UUID;

@Component
@Getter
@RequiredArgsConstructor
public class FileUtil {
    @Value("${file.server-path}")
    private String fileServerPath;
    @Value("${file.base-url}")
    private String fileBaseUrl;
    @Value("${file.download-url}")
    private String fileDownloadUrl;
    @Autowired
    private final HttpServletResponse response;

    public FileDto upload(MultipartFile file) {
        int lastDotIndex = Objects.requireNonNull(file.getOriginalFilename()).lastIndexOf(".");
        String extension = file.getOriginalFilename().substring(lastDotIndex + 1);
        long size = file.getSize();
        String fileName = String.format("%s.%s", UUID.randomUUID(), extension);
        String url = String.format("%s%s", fileBaseUrl, fileName);
        Path path = Paths.get(fileServerPath + fileName);





        try {
            Files.copy(file.getInputStream(), path);
            return FileDto.builder()
                    .name(fileName)
                    .url(url)
                    .downloadUrl(fileDownloadUrl + path.getFileName())
                    .extension(extension)
                    .size(size)
                    .build();
        } catch (IOException e) {
            System.out.println(e.getMessage());
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "Fail to upload file."
            );
        }
    }

    public String getExtensionBase64(String urlScheme) {
        String[] temp = urlScheme.split("/");
        String[] extension = temp[1].split(";");
        return extension[0];
    }

    public String getExtensionFile(File file) {
        int lastDotIndex = file.getName().lastIndexOf(".");
        return file.getName().substring(lastDotIndex + 1);
    }


    public Resource findByName(String filename) {
        Path path = Paths.get(fileServerPath + filename);
        System.out.println(path.getFileSystem());
        try {
            System.out.println(path.toUri());
            Resource resource = new UrlResource(path.toUri());
            if (resource.exists()) {
                return resource;
            } else {
                throw new ResponseStatusException(
                        HttpStatus.INTERNAL_SERVER_ERROR,
                        String.format("File with name %s can't be found.", filename)
                );
            }
        } catch (MalformedURLException e) {
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "URL is invalid!"
            );
        }
    }

    public FileBase64Dto uploadFileStringBase64(String filename) {
        String[] record = filename.split(",");
        String extension = this.getExtensionBase64(record[0]);
        String fileImage = record[1];
        byte[] imageBytes = DatatypeConverter.parseBase64Binary(fileImage);
        String fileName = String.format("%s.%s", UUID.randomUUID(), extension);
        try {
            Path path = Paths.get(fileServerPath + fileName);
            Files.write(path, imageBytes);
            return new FileBase64Dto(true, fileName);
        } catch (IOException e) {
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "Fail to upload file."
            );
        }
    }
}
