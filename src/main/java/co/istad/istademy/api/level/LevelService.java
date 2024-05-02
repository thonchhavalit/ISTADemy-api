package co.istad.istademy.api.level;

import co.istad.istademy.api.level.web.AddLevelDto;
import co.istad.istademy.api.level.web.LevelDto;
import com.github.pagehelper.PageInfo;

public interface LevelService {

    /**
     * use to retrieve all levels from database and response with pagination
     *
     * @param page  : location page
     * @param limit : size of page
     * @return PageInfo of Tutorial is pagination
     */
    PageInfo<LevelDto> selectAllLevel(int page, int limit);

    /**
     * select level by id
     *
     * @param id : needed id in order to do the searching
     * @return LevelDto define response data
     */
    LevelDto selectLevelById(Integer id);

    /**
     * insert level
     *
     * @param addLevelDto : is data need to insert and require
     * @return LevelDto define response data
     */
    LevelDto insertLevel(AddLevelDto addLevelDto);

    /**
     * update level
     *
     * @param updateLevelDto : data need to update
     * @return :LevelDto use to response that necessary to response
     */
    LevelDto updateLevelByUuid(String uuid, AddLevelDto updateLevelDto);

    /**
     * use to delete level by uuid, and it just updates status from false to true
     *
     * @param uuid is required to search before delete
     * @return uuid they deleted
     */
    String deleteLevelByUuid(String uuid);


    /**
     * select level by uuid
     *
     * @param uuid : needed id in order to do the searching
     * @return LevelDto define response data
     */
    LevelDto selectLevelByUuid(String uuid);
}
