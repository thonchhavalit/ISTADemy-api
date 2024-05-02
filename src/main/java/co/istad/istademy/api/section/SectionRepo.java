package co.istad.istademy.api.section;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Mapper
@Repository
public interface SectionRepo {

    @SelectProvider(SectionProvider.class)
    @Results(
            id = "sectionMapper",
            value = {
//                    @Result(column = "course_id", property = "course"),
                    @Result(column = "id", property = "id"),
                    @Result(column = "is_completed", property = "isCompleted"),
                    @Result(column = "is_disabled", property = "isDisabled"),
                    @Result(column = "created_at", property = "createdAt"),
                    @Result(column = "updated_at", property = "updatedAt"),
                    @Result(property = "lessons", javaType = List.class, column = "id",
                            many = @Many(select = "co.istad.istademy.api.lesson.LessonRepo.buildSelectLessonBySectionIdSql"))
            }
    )
    List<Section> buildSelectSectionSql(Integer courseId);


    @Select("SELECT * FROM sections WHERE course_id = #{id}")
    @Results({
            @Result(column = "is_completed", property = "isCompleted"),
            @Result(column = "id", property = "id"),
            @Result(column = "is_disabled", property = "isDisabled"),
            @Result(column = "created_at", property = "createdAt"),
            @Result(column = "updated_at", property = "updatedAt"),
            @Result(property = "lessons", javaType = List.class, column = "id",
                        many = @Many(select = "co.istad.istademy.api.lesson.LessonRepo.buildSelectLessonBySectionIdSql"))
    })
    List<Section> buildSelectSectionLessonByCourseIdSql(Integer id);


    @SelectProvider(SectionProvider.class)
    @ResultMap("sectionMapper")
    List<Section> buildSelectSectionByCourseIdSql(@Param("id") Integer id);

    @SelectProvider(SectionProvider.class)
    @ResultMap("sectionMapper")
    Optional<Section> buildSelectSectionByIdSql(@Param("id") Integer id);

    @SelectProvider(SectionProvider.class)
    @ResultMap("sectionMapper")
    Optional<Section> buildSelectSectionByUuidSql(@Param("uuid") String uuid);

    @InsertProvider(SectionProvider.class)
    @Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "id")
    boolean buildCreateSectionSql(@Param("s") Section section);

    @UpdateProvider(SectionProvider.class)
    boolean buildUpdateSectionByUuidSql(@Param("s") Section section);

    @DeleteProvider(SectionProvider.class)
    boolean buildDeleteSectionByUuidSql(@Param("uuid") String uuid);

    @Select("SELECT EXISTS (SELECT * FROM sections WHERE is_disabled=false)")
    boolean isSectionIdExists(@Param("uuid") String uuid);
}
