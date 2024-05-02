package co.istad.istademy.api.exerciseAnswer;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ExerciseAnswer {
    private Integer id;
    private Integer userId;
    private Integer exerciseId;
    private String uuid;
    private String answer;
    private Boolean isCorrected;
    private Timestamp createdAt;
}
