package co.istad.istademy.api.content;

import co.istad.istademy.api.content.web.ContentDto;
import co.istad.istademy.api.content.web.SaveContentDto;
import com.github.pagehelper.PageInfo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ContentMapStruct {
    ContentDto ContentToContentDto (Content model);
    @Mapping(source = "lesson", target = "lesson.id")
    Content createContentDtoToContent(SaveContentDto model);
    PageInfo<ContentDto> pageInfoContentToPageInfoContentDto(PageInfo<Content> model);
}
