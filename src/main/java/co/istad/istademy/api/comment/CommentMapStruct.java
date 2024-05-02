package co.istad.istademy.api.comment;

import co.istad.istademy.api.comment.web.CommentDto;
import co.istad.istademy.api.comment.web.SaveCommentDto;
import com.github.pagehelper.PageInfo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface CommentMapStruct {
    CommentDto CommentToCommentDto (Comment model);

    @Mappings({
            @Mapping(source = "userId", target = "userId.id"),
            @Mapping(source = "lessonId", target = "lessonId.id"),
            @Mapping(source = "parentId", target = "parentId.id")
    })
    Comment createCommentDtoToComment(SaveCommentDto model);
    PageInfo<CommentDto> pageInfoCommentToPageInfoCommentDto(PageInfo<Comment> model);
}
