package co.istad.istademy.api.level;

import co.istad.istademy.api.level.web.AddLevelDto;
import co.istad.istademy.api.level.web.LevelDto;
import com.github.pagehelper.PageInfo;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface LevelMapStruct {
    LevelDto LevelToLevelDto(Level model);
    Level addLevelDtoToLevel(AddLevelDto model);
    PageInfo<LevelDto> pageInfoLevelDtoToPageInfoLevel(PageInfo<Level> model);

}