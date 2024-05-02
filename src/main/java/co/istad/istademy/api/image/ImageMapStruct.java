package co.istad.istademy.api.image;

import co.istad.istademy.api.image.web.ImageDto;
import co.istad.istademy.api.image.web.ModifyImageDto;
import com.github.pagehelper.PageInfo;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ImageMapStruct {
    ImageDto ImageToImageDto(Image model);
    Image modifyImageDtoToImage(ModifyImageDto model);
    PageInfo<ImageDto> pageInfoImageDtoToPageInfoImage(PageInfo<Image> model);
}
