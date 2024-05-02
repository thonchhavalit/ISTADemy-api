package co.istad.istademy.api.image;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Image {
    private Integer id;
    private String uuid;
    private String name;
    private String url;
    private Timestamp createdAt;
    private Boolean isDisabled;
}
