package co.istad.istademy.api.certificate;

import co.istad.istademy.api.course.Course;
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
public class Certificate {
    private Integer id;
    private User user;
    private Course course;
    private String uuid;
    private Timestamp dateEarned;
}