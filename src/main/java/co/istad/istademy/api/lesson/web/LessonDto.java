package co.istad.istademy.api.lesson.web;

import co.istad.istademy.api.content.Content;
import co.istad.istademy.api.exercise.Exercise;
import co.istad.istademy.api.quiz.Quiz;

import java.sql.Timestamp;
import java.util.List;

public record LessonDto(
        Integer id,
        String uuid,
        Integer section,
        String title,
        String description,
        Boolean isDisabled,
        Timestamp createdAt,
        Timestamp updatedAt,
         List<Content> contents,
        List<Quiz> quizzes,
        List<Exercise> exercises

        ) {
}
