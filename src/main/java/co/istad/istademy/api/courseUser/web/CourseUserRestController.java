package co.istad.istademy.api.courseUser.web;

import co.istad.istademy.api.courseUser.CourseUserService;
import co.istad.istademy.base.BaseRest;
import com.github.pagehelper.PageInfo;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/courseUser")
public class CourseUserRestController {
    private final CourseUserService courseUserService;
    @GetMapping
    public BaseRest<?> selectAllCourseUsers(
            @RequestParam(required = false, defaultValue = "1", name = "page") int page,
            @RequestParam(required = false, defaultValue = "20", name = "limit") int limit)
    {
        PageInfo<CourseUserDto> selectAllCourseUsers = courseUserService.selectAllCourseUsers(page, limit);
        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .timestamp(LocalDateTime.now())
                .message("Successfully retrieved all course users!!!")
                .data(selectAllCourseUsers)
                .build();
    }

    @GetMapping("/{uuid}")
    public BaseRest<?> selectCourseUserByUuid(@PathVariable String uuid){
        CourseUserDto selectCourseUserByUuid = courseUserService.selectCourseUserByUuid(uuid);
        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .timestamp(LocalDateTime.now())
                .data(selectCourseUserByUuid)
                .message("Find Course User by UUID is SuccessFul!!!")
                .build();
    }


    @DeleteMapping("/{uuid}")
    public BaseRest<?> deleteCourseUserByUuid(@PathVariable("uuid") String uuid) {
        String deletedCourseUserByUuid = courseUserService.deleteCourseUserByUuid(uuid);
        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .timestamp(LocalDateTime.now())
                .message("Successfully deleted a course user!")
                .data(deletedCourseUserByUuid)
                .build();
    }

    @PostMapping
    public BaseRest<?> insertCourseUser(@Valid @RequestBody CreateCourseUserDto createCourseUserDto) {
        CourseUserDto insertCourseUser = courseUserService.insertCourseUser(createCourseUserDto);
        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .timestamp(LocalDateTime.now())
                .message("Successfully inserted a course user!!!")
                .data(insertCourseUser)
                .build();
    }

    @PutMapping("/{uuid}")
    public BaseRest<?> updateCourseUserByUuid(@PathVariable("uuid") String uuid,@Valid @RequestBody CreateCourseUserDto updateCourseUserDto) {
        CourseUserDto updatedCourseUserByUuid = courseUserService.updateCourseUserByUuid(updateCourseUserDto, uuid);
        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .timestamp(LocalDateTime.now())
                .message("Successfully updated a course user !!!")
                .data(updatedCourseUserByUuid)
                .build();
    }
}

