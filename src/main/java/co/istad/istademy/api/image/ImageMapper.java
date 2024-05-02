package co.istad.istademy.api.image;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@Mapper
public interface ImageMapper {

    @SelectProvider(ImageProvider.class)
    @Results(
            id = "imageMapper",
            value = {
                    @Result(column = "created_at", property = "createdAt"),
                    @Result(column = "is_disabled", property = "isDisabled")
            }
    )
    List<Image> buildSelectImageSql();

    @SelectProvider(ImageProvider.class)
    @ResultMap("imageMapper")
    Optional<Image> buildSelectImageByIdSql(@Param("id") Integer id);

    @SelectProvider(ImageProvider.class)
    @ResultMap("imageMapper")
    Optional<Image> buildSelectImageByUuidSql(@Param("uuid") String uuid);

    @InsertProvider(ImageProvider.class)
    @Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "id")
    boolean buildInsertImageSql(@Param("i") Image image);

    @UpdateProvider(ImageProvider.class)
    boolean buildUpdateImageByUuidSql(@Param("i") Image image);

    @DeleteProvider(ImageProvider.class)
    boolean buildDeleteImageByUuidSql(@Param("uuid") String uuid);

    @Select("SELECT EXISTS (SELECT * FROM images WHERE is_disabled=false)")
    boolean isImageUuidExits(@Param("uuid") String uuid);


}