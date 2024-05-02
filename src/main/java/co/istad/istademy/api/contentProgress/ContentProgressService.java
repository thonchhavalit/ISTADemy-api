package co.istad.istademy.api.contentProgress;

import co.istad.istademy.api.contentProgress.web.SaveContentProgressDto;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface ContentProgressService {

    /**
     * use to retrieve all contents from database and response with pagination
     *
     * @param page  : location page
     * @param limit : size of page
     * @return PageInfo of Tutorial is pagination
     */
    PageInfo<ContentProgress> selectContentProgresses(int page, int limit, Integer userId, Integer contentId);

    /**
     * insert content
     *
     * @param saveContentProgressDto : is data need to insert and require
     * @return ContentDto define response data
     */
    ContentProgress insertSetIsContentRead(SaveContentProgressDto saveContentProgressDto);

    Boolean selectIsContentProgressExist(Integer userId, Integer contentId);
}
