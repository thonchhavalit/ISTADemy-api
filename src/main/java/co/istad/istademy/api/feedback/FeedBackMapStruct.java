package co.istad.istademy.api.feedback;

import co.istad.istademy.api.feedback.web.CreateFeedBackDto;
import co.istad.istademy.api.feedback.web.FeedBackDto;
import com.github.pagehelper.PageInfo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface FeedBackMapStruct{
    FeedBackDto FeedBackToFeedBackDto (FeedBack model);
    @Mapping(source = "userId", target = "userId.id")
    FeedBack createFeedBackDtoFeedback(CreateFeedBackDto model);
    PageInfo<FeedBackDto> pageInfoFeedBackToPageInfoFeedBackDto (PageInfo<FeedBack> model);
}
