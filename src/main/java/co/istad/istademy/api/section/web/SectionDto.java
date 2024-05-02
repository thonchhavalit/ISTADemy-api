package co.istad.istademy.api.section.web;

import co.istad.istademy.api.lesson.Lesson;

import java.sql.Timestamp;
import java.util.List;

public record SectionDto(
        Integer id,
        String uuid,
        Integer course,
        String title,
        String description,
        Boolean isCompleted,
        Boolean isDisabled,
        Timestamp createdAt,
        Timestamp updatedAt,
        List<Lesson> lessons
        ){
}
