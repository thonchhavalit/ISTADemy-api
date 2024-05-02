package co.istad.istademy.api.course.web;

import co.istad.istademy.api.course.Course;
import co.istad.istademy.api.course.CourseService;
import co.istad.istademy.api.level.Level;
import co.istad.istademy.base.BaseRest;
import com.github.pagehelper.PageInfo;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/courses")
public class CourseRestController {

    private final CourseService courseService;
    @GetMapping
    public BaseRest<?> selectAllCourse(
            @RequestParam(required = false, defaultValue = "1", name = "page") int page,
            @RequestParam(required = false, defaultValue = "20", name = "limit") int limit)
    {
        PageInfo<CourseDto> findAll = courseService.selectAllCourses(page, limit);
        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .timestamp(LocalDateTime.now())
                .message("Successfully retrieved all courses.")
                .data(findAll)
                .build();
    }

    @GetMapping("/{id}")
    public BaseRest<?> getCourseById(@PathVariable("id")Integer id){
        CourseDto selectById = courseService.selectCourseById(id);
        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .timestamp(LocalDateTime.now())
                .data(selectById)
                .message("Successfully get course by Id!!")
                .build();
    }

    @GetMapping("/by/{uuid}")
    public BaseRest<?> getCourseByUuid(@PathVariable("uuid")String uuid){
        CourseDto selectByUuid = courseService.selectCourseByUuid(uuid);
        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .timestamp(LocalDateTime.now())
                .data(selectByUuid)
                .message("Successfully get course by UUID!!")
                .build();
    }
    @DeleteMapping("/{uuid}")
    public BaseRest<?> deleteCourseByUuid(@PathVariable("uuid") String uuid) {
        String deletedId = courseService.deleteCourseByUuid(uuid);
        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .timestamp(LocalDateTime.now())
                .message("Successfully deleted a course!")
                .data(deletedId)
                .build();
    }

//    @GetMapping("/{userID}")
//    public BaseRest<?> getCoursesForUser(@PathVariable("userID") Integer userId){
//        List<Course> getCourseForUser = courseService.getCoursesForUser(userId);
//        return BaseRest.builder()
//                .status(true)
//                .code(HttpStatus.OK.value())
//                .timestamp(LocalDateTime.now())
//                .data(getCourseForUser)
//                .message("Successfully get course for user !!!!")
//                .build();
//    }

    @PostMapping
    public BaseRest<?> insertCourse(@Valid @RequestBody SaveCourseDto saveCourseDto) {
        CourseDto courseDto = courseService.insertCourse(saveCourseDto);
        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .timestamp(LocalDateTime.now())
                .message("Successfully inserted a course.")
                .data(courseDto)
                .build();
    }

    @PutMapping("/{uuid}")
    public BaseRest<?> updateByUuid(@PathVariable("uuid") String uuid,@Valid @RequestBody SaveCourseDto saveCourseDto) {
        CourseDto courseDto = courseService.updateCourseByUuid(uuid, saveCourseDto);
        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .timestamp(LocalDateTime.now())
                .message("Successfully updated a course.")
                .data(courseDto)
                .build();
    }

    @GetMapping("/level/{id}")
    public BaseRest<?> getCourseByLevelId(@PathVariable("id")Integer id){
        List<Course> courseLevel = courseService.getCourseByLevelId(id);
        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .timestamp(LocalDateTime.now())
                .message("Successfully get course by levelId !!!")
                .data(courseLevel)
                .build();
    }
    @GetMapping("/countLesson/{courseId}")
    public BaseRest<?>  countCourseLesson(@PathVariable("courseId")Integer courseId){
        Integer totalContent = courseService.countCourseLessonByCourseId(courseId);
        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .data(totalContent)
                .timestamp(LocalDateTime.now())
                .message("Successfully get total course lesson by course Id!")
                .build();
    }
}