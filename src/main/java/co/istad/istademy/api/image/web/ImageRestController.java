package co.istad.istademy.api.image.web;

import co.istad.istademy.api.image.ImageService;
import co.istad.istademy.base.BaseRest;
import com.github.pagehelper.PageInfo;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/image")
public class ImageRestController {
    private final ImageService imageService;

    @GetMapping
    public BaseRest<?> selectAllImage(
            @RequestParam(required = false, defaultValue = "1", name = "page")int page,
            @RequestParam(required = false,defaultValue = "20" , name = "limit")int limit
    ){
        PageInfo<ImageDto> selectAll = imageService.selectAllImage(page, limit);
        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .timestamp(LocalDateTime.now())
                .data(selectAll)
                .message("Successfully Select All Images!!!!")
                .build();
    }
    @GetMapping("/{id}")
    public BaseRest<?> selectImageById(@PathVariable Integer id){
        ImageDto selectId = imageService.selectImageById(id);
        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .timestamp(LocalDateTime.now())
                .data(selectId)
                .message("Successfully Select Image by ID!!!")
                .build();
    }

    @GetMapping("/by/{uuid}")
    public BaseRest<?> selectImageByUuid(@PathVariable String uuid){
        ImageDto selectUuid = imageService.selectImageByUuid(uuid);
        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .timestamp(LocalDateTime.now())
                .data(selectUuid)
                .message("Successfully Select Image by UUID!!!")
                .build();
    }

    @PostMapping
    public BaseRest<?> insertImage(@RequestBody @Valid ModifyImageDto modifyImageDto){
        ImageDto inserted = imageService.insertImage(modifyImageDto);
        return BaseRest.builder()
                .code(HttpStatus.OK.value())
                .status(true)
                .timestamp(LocalDateTime.now())
                .data(inserted)
                .message("Create Image Successful!!!!")
                .build();
    }

    @PutMapping("/{uuid}")
    public BaseRest<?> updateImageByUuid(@PathVariable String uuid, @Valid @RequestBody ModifyImageDto updateImageDto){
        ImageDto updated = imageService.updateImageByUuid(uuid,updateImageDto);
        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .timestamp(LocalDateTime.now())
                .data(updated)
                .message("Update Image is Successful!!!!!")
                .build();
    }

    @DeleteMapping("/{uuid}")
    public BaseRest<?> deleteImageByUuid(@PathVariable String uuid){
        String deleted = imageService.deleteImageByUuid(uuid);
        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .data(deleted)
                .timestamp(LocalDateTime.now())
                .message("Delete Image is Successful!!!")
                .build();
    }
}
