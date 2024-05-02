package co.istad.istademy.api.contentProgress.web;

import co.istad.istademy.api.lesson.Lesson;
import java.sql.Timestamp;

public record ContentProgressDto(
        Integer id,
        String uuid,
        String content,
        Boolean isRead,
        Boolean isDisabled,
        Timestamp createdAt,
        Boolean isCode,
        Timestamp updatedAt,
        Lesson lesson
) {

}
