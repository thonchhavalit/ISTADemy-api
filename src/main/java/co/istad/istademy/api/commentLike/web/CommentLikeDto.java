package co.istad.istademy.api.commentLike.web;

import java.sql.Timestamp;

public record CommentLikeDto(
        String uuid,
        Integer commentId,
        Integer userId,
        Timestamp createdAt,
        Timestamp updatedAt
) {
}
