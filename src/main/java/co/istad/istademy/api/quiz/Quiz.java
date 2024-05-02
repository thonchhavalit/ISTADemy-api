package co.istad.istademy.api.quiz;

import co.istad.istademy.api.lesson.Lesson;
import co.istad.istademy.api.quizOption.QuizOption;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Quiz {
    private Integer id;
    private Lesson lesson;
    private String uuid;
    private String question;
    private Boolean isTaken;
    private Boolean isDisabled;
    private Timestamp createdAt;
    private Timestamp updatedAt;
    private List<QuizOption> quizOptions;
}
