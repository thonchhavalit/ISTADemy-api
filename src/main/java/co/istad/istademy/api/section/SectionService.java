package co.istad.istademy.api.section;

import co.istad.istademy.api.section.web.SaveSectionDto;
import co.istad.istademy.api.section.web.SectionDto;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface SectionService {

    /**
     * use to retrieve all sections from database and response with pagination
     *
     * @param page  : location page
     * @param limit : size of page
     * @return PageInfo of Tutorial is pagination
     */
    PageInfo<SectionDto> selectAllSection(int page, int limit, Integer courseId);

    /**
     * select section by id
     *
     * @param id : needed id in order to do the searching
     * @return SectionDto define response data
     */
    SectionDto selectSectionById(Integer id);

    /**
     * select section by uuid
     *
     * @param uuid : needed uuid in order to do the searching
     * @return SectionDto define response data
     */
    SectionDto selectSectionByUuid(String uuid);


    /**
     * select section by id
     *
     * @param id : needed id in order to do the searching
     * @return SectionDto define response data
     */
    List<Section> selectSectionLessonByCourseId(Integer id);


    /**
     * insert section
     *
     * @param saveSectionDto : is data need to insert and require
     * @return SectionDto define response data
     */
    SectionDto insertSection(SaveSectionDto saveSectionDto);

    /**
     * update section
     *
     * @param updateSectionDto : data need to update
     * @return :SectionDto use to response that necessary to response
     */
    SectionDto updateSectionByUuid(String uuid, SaveSectionDto updateSectionDto);

    /**
     * use to delete section by uuid, and it just updates status from false to true
     *
     * @param uuid is required to search before delete
     * @return uuid they deleted
     */
    String deleteSectionByUuid(String uuid);
}
