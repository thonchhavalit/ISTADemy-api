package co.istad.istademy.api.user;

import co.istad.istademy.api.image.Image;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Mapper
@Repository
public interface UserMapper {


    @SelectProvider(type = UserProvider.class, method = "buildGetAllUsersSql")
    @Results(id = "mappingUser",value = {
//            @Result(property = "userId",column = "id"),
            @Result(property = "githubUrl",column = "github_url"),
            @Result(property = "lastLogin",column = "last_login"),
            @Result(property = "isDisabled",column = "is_disabled"),
            @Result(property = "updatedAt",column = "updated_at"),
            @Result(property = "createdAt",column = "created_at"),
            @Result(property = "verifiedCode", column = "verified_code"),
            @Result(property = "isVerified", column = "is_verified"),
            @Result(property = "profile", column = "profile", one = @One(select = "findImageByUuid"))
    })
    List<User> getAllUsers(@Param("username") String username,@Param("email") String email);

    @Select("SELECT * FROM images WHERE uuid=#{uuid}")
    @Results({
            @Result(column = "created_at", property = "createdAt"),
            @Result(column = "is_disabled", property = "isDisabled")
    })
    Image findImageByUuid(@Param("uuid") String uuid);

    @InsertProvider(type = UserProvider.class ,method = "buildCreateUser")
    @Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "id")
    boolean createNewUser(@Param("u") User user);

    @SelectProvider(type = UserProvider.class, method = "buildFindUserByIdSql")
    @ResultMap("mappingUser")
    Optional<User> findUserById(@Param("id")int id);
    @SelectProvider(type = UserProvider.class, method = "buildFindUserByUuidSql")
    @ResultMap("mappingUser")
    Optional<User> findUserByUuid(@Param("uuid")String uuid);

    @UpdateProvider(type = UserProvider.class, method = "buildUpdateUserByUuidSql")
    boolean updateUserByUuid(@Param("u") User user);

    @Select("SELECT EXISTS (SELECT * FROM users WHERE is_disabled=false)")
    boolean existsByUuid(@Param("uuid") String uuid);
    @DeleteProvider(type = UserProvider.class, method = "buildDeleteUserByUuidSql")
    boolean deleteUserByUuid(String uuid);

    @Select("SELECT EXISTS(SELECT * FROM users WHERE email = #{email})")
    boolean existsByEmail(String email);

    @Select("SELECT EXISTS(SELECT * FROM roles WHERE id = #{roleId})")
    boolean checkRoleId(Integer roleId);

}
