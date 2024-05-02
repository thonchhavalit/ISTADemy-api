package co.istad.istademy.api.user;

import co.istad.istademy.api.user.web.ModifyUserDto;
import co.istad.istademy.api.user.web.UserDto;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    PageInfo<UserDto> getAllUsers(int page, int size, String username,String email);

    UserDto findUserById(int id);
    UserDto findUserByUuid(String uuid);

    UserDto createNewUser(ModifyUserDto createUserDto);

    UserDto updateUserByUuid(ModifyUserDto updateUserDto, String uuid);

    String deleteUserByUuid(String uuid);


}
