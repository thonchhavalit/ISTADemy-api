package co.istad.istademy.api.content.web;

import co.istad.istademy.api.content.Content;
import co.istad.istademy.api.content.ContentService;
import co.istad.istademy.base.BaseRest;
import com.github.pagehelper.PageInfo;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/contents")
public class ContentRestController {
    private final ContentService contentService;

    @GetMapping
    public BaseRest<?> selectAllContents(
            @RequestParam(required = false,defaultValue = "1",name = "page")int page,
            @RequestParam(required = false, defaultValue = "20", name = "limit")int limit
    ){
        PageInfo<ContentDto> selectAll = contentService.selectAllContents(page, limit);
        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .timestamp(LocalDateTime.now())
                .message("Successfully retrieved all contents")
                .data(selectAll)
                .build();
    }

    @GetMapping("/by/{uuid}")
    public BaseRest<?> selectContentByUuid(@PathVariable String uuid){
        ContentDto selectContentByUuid = contentService.selectContentByUuid(uuid);
        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .timestamp(LocalDateTime.now())
                .data(selectContentByUuid)
                .message("Content has defined by UUID!!!")
                .build();
    }

    @GetMapping("/{id}")
    public BaseRest<?> selectContentById(@PathVariable Integer id){
        ContentDto selectContentById = contentService.selectContentById(id);
        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .timestamp(LocalDateTime.now())
                .data(selectContentById)
                .message("Content has defined by ID!!!")
                .build();
    }

    @PostMapping
    public BaseRest<?> insertContent(@Valid @RequestBody SaveContentDto saveContentDto){
        ContentDto insertContent = contentService.insertContent(saveContentDto);
        return BaseRest.builder()
                .code(HttpStatus.OK.value())
                .status(true)
                .timestamp(LocalDateTime.now())
                .data(insertContent)
                .message("Insert content is Successful!!!!")
                .build();
    }

    @PutMapping("/{uuid}")
    public BaseRest<?> updateContentByUuid(@PathVariable String uuid, @Valid @RequestBody SaveContentDto updateContentDto){
        ContentDto updateByUuid = contentService.updateContentByUuid(uuid, updateContentDto);
        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .timestamp(LocalDateTime.now())
                .data(updateByUuid)
                .message("Update Content is Successful!!!!!")
                .build();
    }

    @DeleteMapping("/{uuid}")
    public BaseRest<?> deleteContentByUuid(@PathVariable("uuid") String uuid){
        String isDeleted = contentService.deleteContentByUuid(uuid);
        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .data(isDeleted)
                .timestamp(LocalDateTime.now())
                .message("Delete Content is Successful!!!")
                .build();
    }

    @GetMapping("/lesson/{id}")
    public BaseRest<?> selectContentByLessonId(@PathVariable Integer id){
        List<Content> selectByLessonId = contentService.selectContentByLessonId(id);
        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .timestamp(LocalDateTime.now())
                .data(selectByLessonId)
                .message("Successfully select Content by Lesson Id!!!")
                .build();
    }
}
