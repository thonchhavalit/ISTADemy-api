package co.istad.istademy.api.courseUser;

import co.istad.istademy.api.courseUser.web.CourseUserDto;
import co.istad.istademy.api.courseUser.web.CreateCourseUserDto;
import com.github.pagehelper.PageInfo;

public interface CourseUserService {
    /**
     * use to retrieve all courseUsers from database and response with pagination
     *
     * @param page  : location page
     * @param limit : size of page
     * @return PageInfo of Tutorial is pagination
     */

    PageInfo<CourseUserDto> selectAllCourseUsers(int page, int limit);

    /**
     * insert courseUser
     *
     * @param createCourseUserDto : is data need to insert and require
     * @return CourseUserDto define response data
     */
    CourseUserDto insertCourseUser(CreateCourseUserDto createCourseUserDto);

    /**
     * select courseUser by courseId userId
     *
     * @param uuid  @param userId : needed id in order to do the searching
     * @return CourseUserDto define response data
     */
    CourseUserDto selectCourseUserByUuid(String uuid);


    /**
     * use to delete courseUser by uuid, and it just updates status from false to true
     *
     * @param uuid is required to search before delete
     * @return uuid they deleted
     */
    String deleteCourseUserByUuid(String uuid);

    /**
     * update courseUser
     *
     * @param updateCourseUserDto : data need to update
     * @param uuid
     * @return :CourseUserDto use to response that necessary to response
     */
    CourseUserDto updateCourseUserByUuid(CreateCourseUserDto updateCourseUserDto, String uuid);





}

