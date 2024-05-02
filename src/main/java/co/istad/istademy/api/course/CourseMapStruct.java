package co.istad.istademy.api.course;

import co.istad.istademy.api.course.web.CourseDto;
import co.istad.istademy.api.course.web.SaveCourseDto;
import com.github.pagehelper.PageInfo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CourseMapStruct {

    CourseDto courseToCourseDto(Course model);
    @Mapping(source = "level", target = "level.id")
    Course createCourseDtoToCourse(SaveCourseDto model);
    PageInfo<CourseDto> pageInfoCourseToPageInfoCourseDto(PageInfo<Course> model);


}