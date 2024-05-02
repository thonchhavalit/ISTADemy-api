package co.istad.istademy.api.section;

import co.istad.istademy.api.section.web.SaveSectionDto;
import co.istad.istademy.api.section.web.SectionDto;
import com.github.pagehelper.PageInfo;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SectionMapStruct {
    PageInfo<SectionDto> pageSectionDtoPageInfo(PageInfo<Section> model);
    Section createSectionDtoSection(SaveSectionDto model);
    SectionDto toSectionDto(Section model);
}
