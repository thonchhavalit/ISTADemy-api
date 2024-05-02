package co.istad.istademy.api.user;

import co.istad.istademy.api.image.Image;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Accessors(chain = true)
public class User {
    private int id ;
    private String uuid;
    private String username;
    private String email;
    private String password;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date dob;
    private Image profile;
    private String githubUrl;
    private String bio;
    private String location;
    private Timestamp lastLogin;
    private Boolean isDisabled;
    private Timestamp updatedAt;
    private Timestamp createdAt;
    private List<Role> roles;

    private String verifiedCode;
    private Boolean isVerified;
}
