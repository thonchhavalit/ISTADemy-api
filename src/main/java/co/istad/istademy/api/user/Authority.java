package co.istad.istademy.api.user;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
public class Authority {
    private Integer id;
    private String name;
    private Timestamp createdAt;
    private Boolean isDisabled;
}
