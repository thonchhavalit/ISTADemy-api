package co.istad.istademy.api.content;

import co.istad.istademy.api.content.web.ContentDto;
import co.istad.istademy.api.content.web.SaveContentDto;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface ContentService {

    /**
     * use to retrieve all contents from database and response with pagination
     *
     * @param page  : location page
     * @param limit : size of page
     * @return PageInfo of Tutorial is pagination
     */

    PageInfo<ContentDto> selectAllContents(int page, int limit);

    /**
     * insert content
     *
     * @param saveContentDto : is data need to insert and require
     * @return ContentDto define response data
     */
    ContentDto insertContent(SaveContentDto saveContentDto);

    /**
     * select content by uuid
     *
     * @param uuid : needed id in order to do the searching
     * @return ContentDto define response data
     */
    ContentDto selectContentByUuid(String uuid);

    /**
     * select content by id
     *
     * @param id : needed id in order to do the searching
     * @return ContentDto define response data
     */
    ContentDto selectContentById(Integer id);

    /**
     * use to delete content by uuid, and it just updates status from false to true
     *
     * @param uuid is required to search before delete
     * @return uuid they deleted
     */
    String deleteContentByUuid(String uuid);

    /**
     * update content
     *
     * @param updateContentDto : data need to update
     * @return :ContentDto use to response that necessary to response
     */
    ContentDto updateContentByUuid(String uuid, SaveContentDto updateContentDto);

    List<Content> selectContentByLessonId(Integer id);
}
