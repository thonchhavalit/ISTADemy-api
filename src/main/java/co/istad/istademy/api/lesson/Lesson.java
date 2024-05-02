package co.istad.istademy.api.lesson;

import co.istad.istademy.api.content.Content;
import co.istad.istademy.api.exercise.Exercise;
import co.istad.istademy.api.quiz.Quiz;
import co.istad.istademy.api.section.Section;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Lesson {
    private Integer id;
    private String uuid;
    private Integer section;
    private String title;
    private String description;
    private Boolean isDisabled;
    private Timestamp createdAt;
    private Timestamp updatedAt;
    private List<Content> contents;
    private List<Quiz> quizzes;
    private List<Exercise> exercises;
}
