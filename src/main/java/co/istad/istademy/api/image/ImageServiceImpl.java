package co.istad.istademy.api.image;

import co.istad.istademy.api.image.web.ImageDto;
import co.istad.istademy.api.image.web.ModifyImageDto;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.sql.Timestamp;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ImageServiceImpl implements ImageService{

    private final ImageMapper imageMapper;
    private final ImageMapStruct imageMapStruct;
    @Override
    public PageInfo<ImageDto> selectAllImage(int page, int limit) {
        PageInfo<Image> imagePageInfo = PageHelper.startPage(page,limit).doSelectPageInfo(imageMapper::buildSelectImageSql);
        return imageMapStruct.pageInfoImageDtoToPageInfoImage(imagePageInfo);
    }

    @Override
    public ImageDto selectImageById(Integer id) {
        Image image = imageMapper.buildSelectImageByIdSql(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,String.format("Undefined image with id %d", id)));
        return imageMapStruct.ImageToImageDto(image);
    }

    @Override
    public ImageDto selectImageByUuid(String uuid) {
        Image image = imageMapper.buildSelectImageByUuidSql(uuid).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,String.format("Undefined image with uuid %s", uuid)));
        return imageMapStruct.ImageToImageDto(image);
    }

    @Override
    public ImageDto insertImage(ModifyImageDto modifyImageDto) {
        Image image = imageMapStruct.modifyImageDtoToImage(modifyImageDto);
        image.setUuid(UUID.randomUUID().toString());
        image.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        if (imageMapper.buildInsertImageSql(image)){
            return this.selectImageById(image.getId());
        }
        else
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,"Fail to create image!!!!");
    }

    @Override
    public ImageDto updateImageByUuid(String uuid, ModifyImageDto updateImageDto) {
        if (imageMapper.isImageUuidExits(uuid)){
            Image image = imageMapStruct.modifyImageDtoToImage(updateImageDto);
            image.setUuid(uuid);
            if (imageMapper.buildUpdateImageByUuidSql(image)){
                return this.selectImageByUuid(uuid);
            }
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Fail to update image!!!");
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND,String.format("Undefined image with uuid %s", uuid));
    }

    @Override
    public String deleteImageByUuid(String uuid) {
        if (imageMapper.isImageUuidExits(uuid)){
            if (imageMapper.buildDeleteImageByUuidSql(uuid)){
                return uuid;
            }
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Fail to delete image!!!!");
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND,String.format("Undefined image with uuid %s", uuid));
    }
}
