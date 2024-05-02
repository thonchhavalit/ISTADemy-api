package co.istad.istademy.api.feedback;

import co.istad.istademy.api.user.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FeedBack {
    private Integer id;
    private User userId;
    private String uuid;
    private String contents;
    private Timestamp createdAt;
}
