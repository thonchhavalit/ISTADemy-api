package co.istad.istademy.api.file;

import co.istad.istademy.api.file.web.FileDto;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public interface FileService {
    /**
     * use to upload a single file
     * @param file request form data from client
     * @return FileDto
     */
    FileDto uploadSingle(MultipartFile file);

    /**
     * use to upload multiple files
     * @param files request form data from client
     * @return FileDto
     */
    List<FileDto> uploadMultiple(List<MultipartFile> files);

    /**
     * use to find all file
     * @return list of files
     */
    List<FileDto> findAll();

    /**
     * use to find file by filename
     * @param fileName search for filename
     * @return FileDto
     */
    FileDto findByName(String fileName);

    /**
     * use to delete file by filename
     * @param filename search for filename
     * @return FileDto
     */
    String deleteByName(String filename);

    /**
     * use to remove all file from a folder sever
     * @return if success return true
     */

    boolean removeAllFiles();


    FileDto uploadFileBase64(String image);

    /**
     * use to download file
     * @param name define the filename
     * @return Resource
     */
    Resource download(String name);

}
