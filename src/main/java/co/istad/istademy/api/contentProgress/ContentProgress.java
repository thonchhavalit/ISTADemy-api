package co.istad.istademy.api.contentProgress;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ContentProgress {
    private Integer id;
    private String uuid;
    private Integer userId;
    private Integer contentId;
    private Boolean isRead;
    private Integer courseId;
    private Timestamp completedAt;
}
