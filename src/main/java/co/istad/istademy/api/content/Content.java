package co.istad.istademy.api.content;

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
public class Content {
    private Integer id;
    private String uuid;
    private String content;
    private Boolean isRead;
    private Boolean isDisabled;
    private Timestamp createdAt;
    private Boolean isCode;
    private Timestamp updatedAt;
    private Lesson lesson;
}
