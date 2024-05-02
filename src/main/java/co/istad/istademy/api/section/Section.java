package co.istad.istademy.api.section;

import co.istad.istademy.api.lesson.Lesson;
import lombok.*;

import java.sql.Timestamp;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Section {
    private Integer id;
    private String uuid;
    private Integer course;
    private String title;
    private String description;
    private Boolean isCompleted;
    private Boolean isDisabled;
    private Timestamp createdAt;
    private Timestamp updatedAt;
    private List<Lesson> lessons;
}
