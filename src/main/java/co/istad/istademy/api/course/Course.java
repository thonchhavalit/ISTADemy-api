package co.istad.istademy.api.course;

import co.istad.istademy.api.level.Level;
import co.istad.istademy.api.section.Section;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Course {
    private Integer id;
    private String uuid;
    private String title;
    private String description;
    private Level level;
    private String image;
    private Float requiredTime;
    private Timestamp createdAt;
    private Timestamp updatedAt;
    private Boolean isDisabled;

    private List<Section> sections;
}
