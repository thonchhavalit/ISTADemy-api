package co.istad.istademy.api.lesson;

import co.istad.istademy.api.lesson.web.LessonDto;
import co.istad.istademy.api.lesson.web.SaveLessonDto;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface LessonService {
    /**
     * use to retrieve all lessons from database and response with pagination
     *
     * @param page  : location page
     * @param limit : size of page
     * @return PageInfo of Tutorial is pagination
     */
    PageInfo<LessonDto> selectAllLessons(int page, int limit, Integer sectionId);

    /**
     * insert lesson
     *
     * @param saveLessonDto : is data need to insert and require
     * @return LessonDto define response data
     */
    LessonDto insertLesson(SaveLessonDto saveLessonDto);

    /**
     * select lesson by id
     *
     * @param id : needed id in order to do the searching
     * @return LessonDto define response data
     */
    LessonDto selectLessonById(Integer id);

    /**
     * select lesson by uuid
     *
     * @param uuid : needed id in order to do the searching
     * @return LessonDto define response data
     */
    LessonDto selectLessonByUuid(String uuid);

    /**
     * update course
     *
     * @param updateCourseDto : data need to update
     * @return :CourseDto use to response that necessary to response
     */
    LessonDto updateLessonByUuid(String uuid, SaveLessonDto updateCourseDto);

    /**
     * use to delete lesson by uuid, and it just updates status from false to true
     *
     * @param uuid is required to search before delete
     * @return uuid they deleted
     */
    String deleteLessonByUuid(String uuid);

    List<Lesson> selectLessonBySectionId(Integer id);
}
