package co.istad.istademy.api.quizUserAnswer;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class QuizUserAnswer {
    private Integer id;
    private Integer userId;
    private Integer quizId;
    private Integer optionId;
    private String uuid;
}
