package co.istad.istademy.api.exercise;

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
public class Exercise {
    private Integer id;
    private Lesson lesson;
    private String uuid;
    private String question;
    private String correctAnswer;
    private Boolean isTaken;
    private String prompt;
    private Boolean isDisabled;
    private Timestamp createdAt;
    private Timestamp updatedAt;
}
