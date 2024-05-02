package co.istad.istademy.api.image;

import co.istad.istademy.api.image.web.ImageDto;
import co.istad.istademy.api.image.web.ModifyImageDto;
import com.github.pagehelper.PageInfo;

public interface ImageService {
    /**
     * use to retrieve all images from database and response with pagination
     *
     * @param page  : location page
     * @param limit : size of page
     * @return PageInfo of Tutorial is pagination
     */
    PageInfo<ImageDto> selectAllImage(int page, int limit);

    /**
     * select image by id
     *
     * @param id : needed id in order to do the searching
     * @return ImageDto define response data
     */
    ImageDto selectImageById(Integer id);

    /**
     * select image by uuid
     *
     * @param uuid : needed id in order to do the searching
     * @return ImageDto define response data
     */
    ImageDto selectImageByUuid(String uuid);

    /**
     * insert image
     *
     * @param modifyImageDto : is data need to insert and require
     * @return ImageDto define response data
     */
    ImageDto insertImage(ModifyImageDto modifyImageDto);

    /**
     * update image
     *
     * @param updateImageDto : data need to update
     * @return :ImageDto use to response that necessary to response
     */
    ImageDto updateImageByUuid(String uuid, ModifyImageDto updateImageDto);

    /**
     * use to delete image by uuid, and it just updates status from false to true
     *
     * @param uuid is required to search before delete
     * @return uuid they deleted
     */
    String deleteImageByUuid(String uuid);
}
