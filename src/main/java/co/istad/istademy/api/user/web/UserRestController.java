package co.istad.istademy.api.user.web;

import co.istad.istademy.api.user.UserService;
import co.istad.istademy.base.BaseRest;
import com.github.pagehelper.PageInfo;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;


@RestController
@Slf4j
@RequestMapping("api/v1/user")
public class UserRestController {
    private final UserService userService;

    public UserRestController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public BaseRest<?> select(@RequestParam(name = "page", required = false, defaultValue = "1") int page,
                              @RequestParam(name = "size", required = false, defaultValue = "20") int size,
                              @RequestParam(name = "username", required = false, defaultValue = "") String username,
                              @RequestParam(name = "email",required = false,defaultValue = "" )String email){
        PageInfo<UserDto> userPageInfo = userService.getAllUsers(page, size, username,email);
        return BaseRest.builder()
                .status(true).code(HttpStatus.OK.value())
                .message("User has been founded")
                .timestamp(LocalDateTime.now())
                .data(userPageInfo)
                .build();
    }
    @GetMapping("/{id}")
    public BaseRest<?> findUserById(@PathVariable("id") int id) {
        UserDto userDto = userService.findUserById(id);
        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .message("User has been found successfully.")
                .timestamp(LocalDateTime.now())
                .data(userDto)
                .build();
    }
    @GetMapping("/by/{uuid}")
    public BaseRest<?> findUserByUuid(@PathVariable("uuid") String uuid) {
        UserDto userDto = userService.findUserByUuid(uuid);
        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .message("User has been found successfully.")
                .timestamp(LocalDateTime.now())
                .data(userDto)
                .build();
    }


    @PostMapping
    public BaseRest<?> createNewUser(@RequestBody @Valid ModifyUserDto createUserDto) {
        UserDto userDto = userService.createNewUser(createUserDto);
        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .message("User has been created")
                .timestamp(LocalDateTime.now())
                .data(userDto)
                .build();
    }

    @DeleteMapping("/{uuid}")
    public BaseRest<?> deleteUserByUuid(@PathVariable String uuid) {
        String deletedUuid = userService.deleteUserByUuid(uuid);
        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .message("User has been deleted successfully.")
                .timestamp(LocalDateTime.now())
                .data(deletedUuid)
                .build();
    }

    @PutMapping("/{uuid}")
    public BaseRest<?> updateUserByUuid(@PathVariable("uuid") String uuid, @RequestBody ModifyUserDto updateUserDto) {
        UserDto userDto = userService.updateUserByUuid(updateUserDto,uuid);
        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .message("User has been updated successfully.")
                .timestamp(LocalDateTime.now())
                .data(userDto)
                .build();
    }
}
