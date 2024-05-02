package co.istad.istademy.api.commentLike.web;

import jakarta.validation.constraints.NotNull;

public record SaveCommentLikeDto(
        @NotNull(message = "Comment is required!!!")
        Integer commentId,
        @NotNull(message = "User is required!!!!")
        Integer userId
) {
}
