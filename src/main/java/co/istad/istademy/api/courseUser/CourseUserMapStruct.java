package co.istad.istademy.api.courseUser;

import co.istad.istademy.api.courseUser.web.CourseUserDto;
import co.istad.istademy.api.courseUser.web.CreateCourseUserDto;
import com.github.pagehelper.PageInfo;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CourseUserMapStruct {
    CourseUserDto CourseUserToCourseUserDto (CourseUser model);
    CourseUser createCourseUserDtoToCourseUser (CreateCourseUserDto model);
    PageInfo<CourseUserDto> pageInfoCourseUserToPageInfoCourseUserDto (PageInfo<CourseUser> model);

//    List<CourseUserDto> mapCourseUserToCourseUserDto(List<CourseUser> model);
}
