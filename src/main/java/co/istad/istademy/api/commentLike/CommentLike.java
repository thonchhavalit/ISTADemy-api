package co.istad.istademy.api.commentLike;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CommentLike {
    private Integer id;
    private String uuid;
    private Integer commentId;
    private Integer userId;
    private Timestamp createdAt;
    private Timestamp updatedAt;
}
