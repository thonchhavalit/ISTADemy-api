package co.istad.istademy.api.lesson.web;

import co.istad.istademy.api.lesson.Lesson;
import co.istad.istademy.api.lesson.LessonService;
import co.istad.istademy.base.BaseRest;
import com.github.pagehelper.PageInfo;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/v1/lessons")
@RequiredArgsConstructor
public class LessonRestController {

    private final LessonService lessonService;

    @GetMapping
//    @PreAuthorize("hasAuthority('SCOPE_lesson:read')")
    public BaseRest<?> getAllSection(
            @RequestParam(required = false,defaultValue = "1", name = "page") int page,
            @RequestParam(required = false,defaultValue = "20", name = "limit")int limit,
            @RequestParam(defaultValue = "0", required = false, name="sectionId") Integer sectionId
    ){
        PageInfo<LessonDto> selectAll = lessonService.selectAllLessons(page, limit, sectionId);
        return BaseRest.builder()
                .code(HttpStatus.OK.value())
                .timestamp(LocalDateTime.now())
                .status(true)
                .data(selectAll)
                .message("Successfully Select ALl Lessons!!!")
                .build();
    }

    @GetMapping("/{id}")
//    @PreAuthorize("hasAuthority('SCOPE_section:read')")
    public BaseRest<?> findLessonById(@PathVariable Integer id){
        var lesson = lessonService.selectLessonById(id);
        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .timestamp(LocalDateTime.now())
                .data(lesson)
                .message("Lesson has defined by ID!!!")
                .build();
    }

    @GetMapping("/by/{uuid}")
//    @PreAuthorize("hasAuthority('SCOPE_section:read')")
    public BaseRest<?> findLessonByUuid(@PathVariable String uuid){
        var lesson = lessonService.selectLessonByUuid(uuid);
        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .timestamp(LocalDateTime.now())
                .data(lesson)
                .message("Lesson has defined by UUID!!!")
                .build();
    }

    @PostMapping
//    @PreAuthorize("hasAuthority('SCOPE_section:write')")
    public BaseRest<?> createLesson(@Valid @RequestBody SaveLessonDto lessonDto){
        LessonDto lesson = lessonService.insertLesson(lessonDto);
        return BaseRest.builder()
                .code(HttpStatus.OK.value())
                .status(true)
                .timestamp(LocalDateTime.now())
                .data(lesson)
                .message("Create Lesson Success!!!!")
                .build();
    }

    @PutMapping("/{uuid}")
//    @PreAuthorize("hasAuthority('SCOPE_section:update')")
    public BaseRest<?> updateLessonByUuid(@PathVariable String uuid, @Valid @RequestBody SaveLessonDto lessonDto){
        LessonDto lesson = lessonService.updateLessonByUuid(uuid, lessonDto);
        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .timestamp(LocalDateTime.now())
                .data(lesson)
                .message("Update Lesson is Successful!!!!!")
                .build();
    }

    @DeleteMapping("/{uuid}")
//    @PreAuthorize("hasAuthority('SCOPE_section:delete')")
    public BaseRest<?> deleteLessonByUuid(@PathVariable("uuid") String uuid){
        String isDeleted = lessonService.deleteLessonByUuid(uuid);
        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .data(isDeleted)
                .timestamp(LocalDateTime.now())
                .message("Delete Lesson is Successful!!!")
                .build();
    }

    @GetMapping("/section/{id}")
    public BaseRest<?> getLessonBySectionId(@PathVariable Integer id){
        List<Lesson> lessonSection = lessonService.selectLessonBySectionId(id);
        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .timestamp(LocalDateTime.now())
                .message("Successfully get lesson by section id!!!")
                .data(lessonSection)
                .build();
    }

}
