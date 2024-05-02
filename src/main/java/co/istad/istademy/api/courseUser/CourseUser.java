package co.istad.istademy.api.courseUser;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;



@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CourseUser {
    private String uuid;
    private Integer courseId;
    private Integer userId;
    private Timestamp enrolledAt;
    private Timestamp finishedAt;
}
