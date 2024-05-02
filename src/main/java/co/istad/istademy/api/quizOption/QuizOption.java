package co.istad.istademy.api.quizOption;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class QuizOption {
    private Integer id;
    private String uuid;
    private Integer quizId;
    private String choice;
    private Boolean isCorrected;
}
