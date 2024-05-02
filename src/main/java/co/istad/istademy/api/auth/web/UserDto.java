package co.istad.istademy.api.auth.web;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private int id;
    private String email;
    private String username;
    private String avatar;
    private List<RoleResponseDto> roles;
}
