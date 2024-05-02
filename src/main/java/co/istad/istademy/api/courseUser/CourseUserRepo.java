package co.istad.istademy.api.courseUser;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@Mapper
public interface CourseUserRepo {

    @SelectProvider(CourseUserProvider.class)
    @Results(
            id = "courseUserMapper",
            value = {
                    @Result(column = "course_id",property = "courseId"),
                    @Result(column = "user_id", property = "userId"),
                    @Result(column = "enrolled_at", property = "enrolledAt"),
                    @Result(column = "finished_at" ,property = "finishedAt")
            }
    )
    List<CourseUser> buildSelectCourseUserSql();


    @SelectProvider(CourseUserProvider.class)
    @ResultMap("courseUserMapper")
    Optional<CourseUser> buildSelectCourseUserByUuidSql(String uuid);

    @InsertProvider(CourseUserProvider.class)
    @Options(useGeneratedKeys = true, keyColumn = "uuid", keyProperty = "uuid")
    boolean buildCreateCourseUserSql(@Param("cu") CourseUser courseUser);

    @UpdateProvider(CourseUserProvider.class)
    boolean buildEditCourseUserSql(@Param("cu") CourseUser courseUser);


    @DeleteProvider(CourseUserProvider.class)
    boolean buildDeleteCourseUserByUuidSql(@Param("uuid") String uuid);


}

