package co.istad.istademy.api.comment;

import co.istad.istademy.api.course.Course;
import co.istad.istademy.api.lesson.Lesson;
import co.istad.istademy.api.level.Level;
import co.istad.istademy.api.section.Section;
import co.istad.istademy.api.user.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Mapper
@Repository
public interface CommentRepo {

    @SelectProvider(CommentProvider.class)
    @Results(
            id = "commentMapper",
            value = {
                    @Result(column = "id", property = "id"),
//                  @Result(column = "lesson_id",property = "lessonId", one = @One(select = "buildSelectLessonIdSql")),
                    @Result(column = "user_id", property = "userId", one = @One(select = "co.istad.istademy.api.user.UserMapper.findUserById")),
                    @Result(column = "parent_id",property = "parentId", one=@One(select = "buildSelectCommentById")),
                    @Result(column = "is_disabled", property = "isDisabled"),
                    @Result(column = "created_at",property = "createdAt"),
                    @Result(column = "updated_at",property = "updatedAt"),
                    @Result(property = "subComments", javaType = List.class, column = "id",
                            many = @Many(select = "buildSelectCommentByParentIdSql"))
            }
    )
    List<Comment> buildSelectCommentSql(Integer lessonId);

    @Select("SELECT * FROM comments where parent_id = #{id}")
    @Results({
            @Result(column = "user_id", property = "userId", one = @One(select = "co.istad.istademy.api.user.UserMapper.findUserById")),
//            @Result(column = "parent_id",property = "parentId", one=@One(select = "buildSelectCommentById")),
            @Result(column = "is_disabled", property = "isDisabled"),
            @Result(column = "created_at",property = "createdAt"),
            @Result(column = "updated_at",property = "updatedAt"),
    })
    Comment buildSelectCommentByParentIdSql (Integer id);

    @Select("SELECT * FROM lessons where id = #{id}")
    Lesson buildSelectLessonIdSql(Integer lessonId);

    @Select("SELECT * FROM courses where id = #{id}")
    @Results({
            @Result(column = "created_at", property = "createdAt"),
            @Result(column = "updated_at", property = "updatedAt"),
            @Result(column = "level_id", property = "level", many = @Many(select = "buildSelectCourseLevelByIdSql")),
            @Result(column = "required_time", property = "requiredTime"),
            @Result(column = "is_disabled", property = "isDisabled")
    })
    Course buildSelectSectionByCourseId (Integer id);

    @Select("SELECT * FROM users where id = #{id}")
    @Results({
            @Result(property = "githubUrl",column = "github_url"),
            @Result(property = "lastLogin",column = "last_login"),
            @Result(property = "isDisabled",column = "is_disabled"),
            @Result(property = "updatedAt",column = "updated_at"),
            @Result(property = "createdAt",column = "created_at"),
            @Result(property = "verifiedCode", column = "verified_code"),
            @Result(property = "isVerified", column = "is_verified")
    })
    User buildSelectUserByIdSql (User id);

    @Select("SELECT * FROM levels where id = #{id}")
    List<Level> buildSelectCourseLevelByIdSql (Integer id);

    @SelectProvider(CommentProvider.class)
    @ResultMap("commentMapper")
    List<Comment> buildSelectCommentByUserIdSql(Integer id);

    @SelectProvider(CommentProvider.class)
    @ResultMap("commentMapper")
    List<Comment> buildSelectCommentByLessonIdSql(Integer id);

    @Select("SELECT * FROM comments where parent_id = #{id}")
    @Results({
            @Result(column = "user_id", property = "userId", one = @One(select = "co.istad.istademy.api.user.UserMapper.findUserById")),
//            @Result(column = "parent_id",property = "parentId", one=@One(select = "buildSelectCommentById")),
            @Result(column = "is_disabled", property = "isDisabled"),
            @Result(column = "created_at",property = "createdAt"),
            @Result(column = "updated_at",property = "updatedAt"),
    })
    Comment buildSelectCommentByParentIdSql (Integer id);

    @Select("SELECT * FROM lessons where id = #{id}")
    Lesson buildSelectLessonIdSql(Integer lessonId);

    @Select("SELECT * FROM courses where id = #{id}")
    @Results({
            @Result(column = "created_at", property = "createdAt"),
            @Result(column = "updated_at", property = "updatedAt"),
            @Result(column = "level_id", property = "level", many = @Many(select = "buildSelectCourseLevelByIdSql")),
            @Result(column = "required_time", property = "requiredTime"),
            @Result(column = "is_disabled", property = "isDisabled")
    })
    Course buildSelectSectionByCourseId (Integer id);

    @Select("SELECT * FROM users where id = #{id}")
    @Results({
            @Result(property = "githubUrl",column = "github_url"),
            @Result(property = "lastLogin",column = "last_login"),
            @Result(property = "isDisabled",column = "is_disabled"),
            @Result(property = "updatedAt",column = "updated_at"),
            @Result(property = "createdAt",column = "created_at"),
            @Result(property = "verifiedCode", column = "verified_code"),
            @Result(property = "isVerified", column = "is_verified")
    })
    User buildSelectUserByIdSql (User id);

    @Select("SELECT * FROM levels where id = #{id}")
    List<Level> buildSelectCourseLevelByIdSql (Integer id);

    @SelectProvider(CommentProvider.class)
    @ResultMap("commentMapper")
    List<Comment> buildSelectCommentByUserIdSql(Integer id);

    @SelectProvider(CommentProvider.class)
    @ResultMap("commentMapper")
    List<Comment> buildSelectCommentByLessonIdSql(Integer id);

    @SelectProvider(CommentProvider.class)
    @ResultMap("commentMapper")
    Optional<Comment> buildSelectCommentById(Integer id);

    @SelectProvider(CommentProvider.class)
    @ResultMap("commentMapper")
    Optional<Comment> buildSelectCommentByUuid(String uuid);

    @InsertProvider(CommentProvider.class)
    @Options(useGeneratedKeys = true,keyColumn = "id",keyProperty = "id")
    boolean buildInsertCommentSql(@Param("cm") Comment comment);

    @UpdateProvider(CommentProvider.class)
    boolean buildUpdateCommentByUuidSql(@Param("cm") Comment comment);

    @DeleteProvider(CommentProvider.class)
    boolean buildDeleteCommentByUuidSql(@Param("uuid") String uuid);

    @Select("SELECT EXISTS (SELECT * FROM comments WHERE is_disabled=false)")
    boolean isCommentUuidExists(@Param("uuid") String uuid);

}
