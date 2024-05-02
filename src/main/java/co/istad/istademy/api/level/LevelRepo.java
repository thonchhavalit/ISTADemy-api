package co.istad.istademy.api.level;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@Mapper
public interface LevelRepo {

    @SelectProvider(LevelProvider.class)
    List<Level> buildSelectLevelSql();

    @SelectProvider(LevelProvider.class)
    Optional<Level> buildSelectLevelByIdSql(@Param("id")Integer id);
    @SelectProvider(LevelProvider.class)
    Optional<Level> buildSelectLevelByUuidSql(@Param("uuid")String uuid);

    @InsertProvider(LevelProvider.class)
    @Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "id")
    boolean buildAddLevelSql(@Param("l") Level level);

    @UpdateProvider(LevelProvider.class)
    boolean buildEditLevelSql(@Param("l") Level level);

    @DeleteProvider(LevelProvider.class)
    boolean buildDeleteLevelSql(@Param("uuid") String uuid);
}
