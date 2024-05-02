package co.istad.istademy.api.example;

import co.istad.istademy.api.lesson.Lesson;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Example {
    private Integer id;
    private Lesson LessonId;
    private String uuid;
    private String exampleCode;
    private String description;
    private String output;
    private Boolean isDisabled;
    private Timestamp createdAt;
    private Timestamp updatedAt;
}
