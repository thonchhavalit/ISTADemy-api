package co.istad.istademy.api.contentProgress;

import co.istad.istademy.api.content.Content;
import co.istad.istademy.api.contentProgress.web.ContentProgressDto;
import co.istad.istademy.api.contentProgress.web.SaveContentProgressDto;
import com.github.pagehelper.PageInfo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ContentProgressMapStruct {
    ContentProgressDto contentProgressToContentProgressDto (Content model);

    ContentProgress createContentProgressDtoToContentProgress(SaveContentProgressDto model);
}
