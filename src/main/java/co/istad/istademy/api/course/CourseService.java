package co.istad.istademy.api.course;

import co.istad.istademy.api.course.web.CourseDto;
import co.istad.istademy.api.course.web.SaveCourseDto;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CourseService {
    /**
     * use to retrieve all courses from database and response with pagination
     *
     * @param page  : location page
     * @param limit : size of page
     * @return PageInfo of Tutorial is pagination
     */

    PageInfo<CourseDto> selectAllCourses(int page, int limit);

    /**
     * insert course
     *
     * @param saveCourseDto : is data need to insert and require
     * @return CourseDto define response data
     */
    CourseDto insertCourse(SaveCourseDto saveCourseDto);

    /**
     * select course by id
     *
     * @param id : needed id in order to do the searching
     * @return CourseDto define response data
     */
    CourseDto selectCourseById(Integer id);

    /**
     * select course by uuid
     *
     * @param uuid : needed uuid in order to do the searching
     * @return CourseDto define response data
     */
    CourseDto selectCourseByUuid(String uuid);

    /**
     * use to delete course by uuid, and it just updates status from false to true
     *
     * @param uuid is required to search before delete
     * @return uuid they deleted
     */
    String deleteCourseByUuid(String uuid);

    /**
     * update course
     *
     * @param updateCourseDto : data need to update
     * @return :CourseDto use to response that necessary to response
     */
    CourseDto updateCourseByUuid(String uuid, SaveCourseDto updateCourseDto);

//    List<Course> getCoursesForUser(Integer userID);

    List<Course> getCourseByLevelId(Integer id);

    Integer countCourseLessonByCourseId(Integer courseId);
}