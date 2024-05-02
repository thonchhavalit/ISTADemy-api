package co.istad.istademy.api.user;

import co.istad.istademy.api.user.web.ModifyUserDto;
import co.istad.istademy.api.user.web.UserDto;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.sql.Timestamp;
import java.util.UUID;
@Service
@Slf4j

public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;
    private final UserMapStruct userMapStruct;

    public UserServiceImpl(UserMapper userMapper, UserMapStruct userMapStruct) {
        this.userMapper = userMapper;
        this.userMapStruct = userMapStruct;
    }

    @Override
    public PageInfo<UserDto> getAllUsers(int page, int size, String username, String email ){
        PageInfo<User> userPageInfo = PageHelper.startPage(page, size).doSelectPageInfo(
                () -> userMapper.getAllUsers(username,email)
        );
        if (!username.isEmpty() && !email.isEmpty() && userMapper.getAllUsers(username,email).isEmpty()) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    String.format("User with %s is not found","user :" +username+" email :"+ email)
            );
        }
        return userMapStruct.userPageToUserDtoPage(userPageInfo);
    }

    @Override
    public UserDto findUserById(int id) {
            User user = userMapper.findUserById(id).orElseThrow(
                    () -> new ResponseStatusException(
                            HttpStatus.NOT_FOUND,
                            String.format("Can't find user with id %s!", id)
                    )
            );
            System.out.println(user);
            return userMapStruct.userToUserDto(user);
        }

    @Override
    public UserDto findUserByUuid(String uuid) {
        User user = userMapper.findUserByUuid(uuid).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,String.format("Undefined user with uuid %S", uuid)));
        return userMapStruct.userToUserDto(user);
    }

    @Override
    public UserDto createNewUser(ModifyUserDto createUserDto) {
        User user = userMapStruct.modifyUserDtoToUser(createUserDto);
        user.setUuid(UUID.randomUUID().toString());
        user.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        if (userMapper.createNewUser(user)) {
            return this.findUserById(user.getId());
        }
        throw new ResponseStatusException(
                HttpStatus.INTERNAL_SERVER_ERROR,
                "Fail to create new user!"
        );
    }

    @Override
    public UserDto updateUserByUuid(ModifyUserDto updateUserDto, String uuid) {
        if (userMapper.existsByUuid(uuid)){
        User user = userMapStruct.modifyUserDtoToUser(updateUserDto);
        user.setUuid(uuid);
        user.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
        user.setProfile(userMapper.findImageByUuid(user.getProfile().getUuid()));
        if (userMapper.updateUserByUuid(user)){
            return this.findUserByUuid(uuid);
        }
        throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,"Fail to update User!!!");
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                String.format("User with uuid %s is not found", uuid));
    }


    @Override
    public String deleteUserByUuid(String uuid) {
        if (userMapper.existsByUuid(uuid)){
            if (userMapper.deleteUserByUuid(uuid)) {
                return uuid;
            }
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,"Fail to delete user!!!");
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                String.format("User with uuid %s is not found", uuid));
    }
}
