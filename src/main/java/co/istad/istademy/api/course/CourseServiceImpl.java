package co.istad.istademy.api.course;

import co.istad.istademy.api.course.web.CourseDto;
import co.istad.istademy.api.course.web.SaveCourseDto;
import co.istad.istademy.api.level.LevelRepo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class CourseServiceImpl implements CourseService{
    private final CourseMapper courseMapper;
    private final CourseMapStruct courseMapStruct;
    private final LevelRepo levelRepo;

    @Override
    public PageInfo<CourseDto> selectAllCourses(int page, int limit) {
        PageInfo<Course> coursePageInfo = PageHelper.startPage(page, limit).doSelectPageInfo(
                courseMapper::buildSelectCoursesSql
        );
        return courseMapStruct.pageInfoCourseToPageInfoCourseDto(coursePageInfo);
    }

    @Override
    public CourseDto selectCourseById(Integer id) {
        Course course = courseMapper.buildFindCourseByIdSql(id).orElseThrow(
                () -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        String.format("Can't find course with id %s!", id)
                )
        );
        System.out.println(course);
        return courseMapStruct.courseToCourseDto(course);
    }

    @Override
    public CourseDto selectCourseByUuid(String uuid) {
        Course course = courseMapper.buildFindCourseByUuidSql(uuid).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,String.format("Undefined course with uuid %s", uuid)));
        return courseMapStruct.courseToCourseDto(course);
    }

    @Override
    public String deleteCourseByUuid(String uuid) {
        if (courseMapper.isCourseUuidExits(uuid)) {
            if (courseMapper.buildDeleteCourseByUuidSql(uuid)) {
                return uuid;
            }
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "Fail to delete course"
            );
        }
        throw new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                String.format("Can't find course with uuid %s", uuid)
        );
    }

    @Override
    public CourseDto updateCourseByUuid(String uuid, SaveCourseDto updateCourseDto) {
        if (courseMapper.isCourseUuidExits(uuid)) {
            Course course = courseMapStruct.createCourseDtoToCourse(updateCourseDto);
            course.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
            course.setUuid(uuid);
            if (courseMapper.buildUpdateCourseByUuidSql(course)) {
                return this.selectCourseByUuid(uuid);
            }
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "Fail to update course!"
            );
        }
        throw new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                String.format("Can't fund course with uuid %s", uuid)
        );
    }

//    @Override
//    public List<Course> getCoursesForUser(Integer userID) {
//       return courseMapper.buildFindCourseByUserIdSql(userID);
//    }

    @Override
    public List<Course> getCourseByLevelId(Integer id) {
        return courseMapper.buildSelectCourseByLevelId(id);
    }

    @Override
    public Integer countCourseLessonByCourseId(Integer courseId) {
        return courseMapper.getLessonCountByCourseId(courseId);
    }

    @Override
    public CourseDto insertCourse(SaveCourseDto saveCourseDto) {
        Course course = courseMapStruct.createCourseDtoToCourse(saveCourseDto);
        course.setUuid(UUID.randomUUID().toString());
        course.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        if (courseMapper.buildInsertCourseSql(course)) {
            return this.selectCourseById(course.getId());
        }
        throw new ResponseStatusException(
                HttpStatus.INTERNAL_SERVER_ERROR,
                "Fail to create new course!"
        );
    }
}