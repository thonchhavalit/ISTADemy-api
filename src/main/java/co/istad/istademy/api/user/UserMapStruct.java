package co.istad.istademy.api.user;

import co.istad.istademy.api.auth.web.RegisterDto;
import co.istad.istademy.api.auth.web.ResponseProfileDto;
import co.istad.istademy.api.user.web.ModifyUserDto;
import co.istad.istademy.api.user.web.UserDto;
import com.github.pagehelper.PageInfo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapStruct {

    PageInfo<UserDto> userPageToUserDtoPage(PageInfo<User> userPageInfo);
    UserDto userToUserDto(User model);
    @Mapping(source = "profile",target = "profile.uuid")
    User modifyUserDtoToUser (ModifyUserDto modifyUserDto);


    User registerDtoToUser(RegisterDto registerDto);
    ResponseProfileDto toResponseProfileDto (User user);


}
