package co.istad.istademy.api.userProgress;

import co.istad.istademy.api.contentProgress.ContentProgress;
import co.istad.istademy.api.course.Course;
import co.istad.istademy.api.quizProgresses.QuizProgress;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserProgress {
    private Integer courseId;
    private String courseName;
    private String courseImage;
    private Integer contentCompleted;
    private Integer totalQuiz;
    private Integer totalLesson;
    private Integer lessonCompleted;
    private Integer totalContent;
    private Integer quizzesCompleted;
    private Double courseCompletionPercentage;
    private Double lessonReadPercentage;
    private Double quizTakenPercentage;
//    private Integer exercisesCompleted;
}