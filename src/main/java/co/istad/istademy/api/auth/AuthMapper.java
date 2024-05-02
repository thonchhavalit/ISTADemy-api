package co.istad.istademy.api.auth;

import co.istad.istademy.api.auth.web.ResponseProfileDto;
import co.istad.istademy.api.auth.web.RoleResponseDto;
import co.istad.istademy.api.auth.web.UserDto;
import co.istad.istademy.api.image.Image;
import co.istad.istademy.api.user.Authority;
import co.istad.istademy.api.user.Role;
import co.istad.istademy.api.user.User;
import co.istad.istademy.api.user.web.UserDto;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Mapper
@Repository
public interface AuthMapper {

    @InsertProvider(type = AuthProvider.class, method = "buildRegisterSql")
    @Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "id")
    boolean register(@Param("u") User user);

    @InsertProvider(type = AuthProvider.class, method = "buildCreateUserRoleSql")
    void createUserRole(@Param("userId") Integer userId, @Param("roleId") Integer roleId);


    @Select("SELECT * FROM users WHERE email = #{email} AND is_disabled = FALSE")
    @Results(id = "authResultMap", value = {
            @Result(column = "id", property = "id"),
            @Result(column = "github_url" ,property = "githubUrl"),
            @Result(column = "last_login",property = "lastLogin"),
            @Result(column = "updated_at",property = "updatedAt"),
            @Result(column = "created_at" ,property = "createdAt"),
            @Result(column = "is_disabled", property = "isDisabled"),
            @Result(column = "is_verified", property = "isVerified"),
            @Result(column = "github_url" ,property = "githubUrl"),
            @Result(column = "dob",property = "dob"),
            @Result(column = "profile", property = "profile", one = @One(select = "findImageByUuid")),
            @Result(column = "verified_code", property = "verifiedCode"),
            @Result(column = "id", property = "roles", many = @Many(select = "loadUserRoles"))
    })
    Optional<User> selectByEmail(@Param("email") String email);

    @SelectProvider(type = AuthProvider.class, method = "buildSelectByEmailAndVerifiedCodeSql")
    @ResultMap(value = "authResultMap")
    Optional<User> selectByEmailAndVerifiedCode(@Param("email") String email, @Param("verifiedCode") String verifiedCode);

    @UpdateProvider(type = AuthProvider.class, method = "buildVerifySql")
    void verify(@Param("email") String email, @Param("verifiedCode") String verifiedCode);

    @UpdateProvider(type = AuthProvider.class, method = "buildUpdateVerifiedCodeSql")
    boolean updateVerifiedCode(@Param("email") String email, @Param("verifiedCode") String verifiedCode);

    @Select("SELECT * FROM users WHERE email = #{email} AND is_verified = TRUE")
    @ResultMap("authResultMap")
    Optional<User> loadUserByUsername(@Param("email") String email);

    @SelectProvider(type = AuthProvider.class, method = "buildLoadUserRolesSql")
    @Result(column = "id", property = "authorities",
        many = @Many(select = "loadUserAuthorities"))
    List<Role> loadUserRoles(Integer userId);

    @SelectProvider(type = AuthProvider.class, method = "buildLoadUserAuthoritiesSql")
    List<Authority> loadUserAuthorities(Integer roleId);

    @Select("SELECT role FROM user_roles INNER JOIN roles ON user_roles.role_id=roles.id WHERE user_id=#{id}")
    List<ResponseProfileDto> findUserRole();

    @Select("SELECT * FROM images WHERE uuid=#{uuid}")
    @Results({
            @Result(column = "created_at", property = "createdAt"),
            @Result(column = "is_disabled", property = "isDisabled")
    })
    Image findImageByUuid(@Param("uuid") String uuid);

    @Select("SELECT * FROM users WHERE uuid = #{uuid}")
    User findByUuid(@Param("uuid") String uuid);


    @Insert("INSERT INTO users (uuid,username, email, created_at) VALUES (#{user.uuid},#{user.username},#{user.email},#{user.createdAt})")
    @Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "id")
    boolean createUserOauth2(@Param("user") User user);

    @Select("SELECT * FROM users WHERE email = #{email} AND username = #{username}")
    UserDto findUserGoogle(String email, String username);

    @Select("SELECT * FROM roles WHERE name = #{name}")
    Role findRoleByName(String name);

    @Select("SELECT * FROM users WHERE email = #{email} AND username = #{username}")
    UserDto findUserGithub(String email, String username);


    @InsertProvider(type = AuthProvider.class, method = "buildCreateUserRoleSql")
    void createUserRole(@Param("userId") Integer userId, @Param("roleId") Integer roleId);

}
