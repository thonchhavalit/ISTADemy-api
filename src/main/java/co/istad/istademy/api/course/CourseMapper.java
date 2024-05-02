package co.istad.istademy.api.course;


import co.istad.istademy.api.courseUser.CourseUserProvider;
import co.istad.istademy.api.level.Level;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Mapper
@Repository
public interface CourseMapper {

   @SelectProvider(CourseProvider.class)
   @Results(
           id = "courseResultMap",
           value = {
                   @Result(column = "created_at", property = "createdAt"),
                   @Result(column = "updated_at", property = "updatedAt"),
                   @Result(column = "level_id", property = "level", many = @Many(select = "buildSelectLevelId")),
                   @Result(column = "required_time", property = "requiredTime"),
                   @Result(column = "is_disabled", property = "isDisabled")
           }
   )
   List<Course> buildSelectCoursesSql();

   @Select("SELECT * FROM levels where id = #{id}")
   List<Level> buildSelectLevelId (Integer id);

   @SelectProvider(CourseProvider.class)
   @ResultMap("courseResultMap")
   List<Course> buildSelectCourseByLevelId(@Param("id")Integer id);

   @InsertProvider(CourseProvider.class)
   @Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "id")
   boolean buildInsertCourseSql(@Param("c") Course course);

   @SelectProvider(CourseProvider.class)
   @ResultMap("courseResultMap")
   Optional<Course> buildFindCourseByIdSql(Integer id);

   @SelectProvider(CourseProvider.class)
   @ResultMap("courseResultMap")
   Optional<Course> buildFindCourseByUuidSql(String uuid);

   @UpdateProvider(CourseProvider.class)
   boolean buildDeleteCourseByUuidSql(@Param("uuid") String uuid);


   @Select("SELECT EXISTS (SELECT * FROM courses WHERE is_disabled=false)")
   boolean isCourseUuidExits(@Param("uuid") String uuid);

   @Select("SELECT COUNT(l.id) FROM courses co " +
           "JOIN sections s ON co.id = s.course_id " +
           "JOIN lessons l ON s.id = l.section_id " +
           "WHERE co.id = #{courseId}")
   int getLessonCountByCourseId(@Param("courseId") Integer courseId);

   @UpdateProvider(CourseProvider.class)
   boolean buildUpdateCourseByUuidSql(@Param("c") Course course);

   @SelectProvider(value = CourseUserProvider.class, method = "buildFindCourseByUserIdSql")
   List<Course> buildFindCourseByUserIdSql(@Param("userID") Integer userID);

}