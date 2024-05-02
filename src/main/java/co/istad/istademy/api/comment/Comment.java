package co.istad.istademy.api.comment;

import co.istad.istademy.api.lesson.Lesson;
import co.istad.istademy.api.user.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Comment {
    private Integer id;
    private Lesson lessonId;
    private User userId;
    private Comment parentId;
    private String uuid;
    private String contents;
    private Boolean isDisabled;
    private Timestamp createdAt;
    private Timestamp updatedAt;
    private List<Comment> subComments;
}
