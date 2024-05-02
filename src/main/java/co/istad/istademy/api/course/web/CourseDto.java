package co.istad.istademy.api.course.web;

import co.istad.istademy.api.level.Level;
import co.istad.istademy.api.section.Section;

import java.sql.Timestamp;
import java.util.List;

public record CourseDto(
        Integer id,
        String uuid,
        String title,
        String description,
        Level level,
        String image,
        Float requiredTime,
        Timestamp createdAt,
        Timestamp updatedAt,
        Boolean isDisabled,
        List<Section> sections
        ) {

}

