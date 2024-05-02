package co.istad.istademy.api.comment.web;

import co.istad.istademy.api.comment.Comment;
import co.istad.istademy.api.lesson.Lesson;
import co.istad.istademy.api.user.User;

import java.sql.Timestamp;
import java.util.List;

public record CommentDto(
        Lesson lessonId,
        User userId,
        Integer id,
        Comment parentId,
        String uuid,
        String contents,
        Boolean isDisabled,
        Timestamp createdAt,
        Timestamp updatedAt,
        List<Comment> subComments
) {
}
