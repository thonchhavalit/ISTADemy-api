package co.istad.istademy.api.quizProgresses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class QuizProgress {
    private Integer id;
    private String uuid;
    private Integer userId;
    private Integer quizId;
    private Boolean isTaken;
    private Integer courseId;
    private Timestamp completedAt;
}
