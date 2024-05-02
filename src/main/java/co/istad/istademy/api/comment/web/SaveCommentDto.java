package co.istad.istademy.api.comment.web;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record SaveCommentDto(
        @NotNull(message = "Lesson is required!!!!")
        Integer lessonId,
        @NotNull(message = "User is required!!!")
        Integer userId,
        Integer parentId,
        @NotBlank(message = "Content is required!!!!")
        String contents
) {
}
