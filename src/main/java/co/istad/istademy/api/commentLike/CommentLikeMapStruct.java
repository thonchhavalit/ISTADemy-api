package co.istad.istademy.api.commentLike;

import co.istad.istademy.api.commentLike.web.CommentLikeDto;
import co.istad.istademy.api.commentLike.web.SaveCommentLikeDto;
import com.github.pagehelper.PageInfo;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CommentLikeMapStruct {
    CommentLikeDto CommentLikeToCommentLikeDto (CommentLike model);
    CommentLike saveCommentLikeDtoToCommentLike(SaveCommentLikeDto model);
    PageInfo<CommentLikeDto> pageInfoCommentLikeToPageInfoCommentLikeDto(PageInfo<CommentLike> model);
}
