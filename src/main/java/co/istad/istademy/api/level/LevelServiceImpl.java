package co.istad.istademy.api.level;

import co.istad.istademy.api.level.web.AddLevelDto;
import co.istad.istademy.api.level.web.LevelDto;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class LevelServiceImpl implements LevelService{
    private final LevelMapStruct levelMapStruct;
    private final LevelRepo levelRepo;
    @Override
    public PageInfo<LevelDto> selectAllLevel(int page, int limit) {
        PageInfo<Level> levelPageInfo = PageHelper.startPage(page, limit).doSelectPageInfo(levelRepo::buildSelectLevelSql);
        return levelMapStruct.pageInfoLevelDtoToPageInfoLevel(levelPageInfo);
    }

    @Override
    public LevelDto selectLevelById(Integer id) {
        Level level = levelRepo.buildSelectLevelByIdSql(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,String.format("Undefined level with id %d", id)));
        return levelMapStruct.LevelToLevelDto(level);
    }

    @Override
    public LevelDto insertLevel(AddLevelDto addLevelDto) {
        Level level = levelMapStruct.addLevelDtoToLevel(addLevelDto);
        level.setUuid(UUID.randomUUID().toString());
        if (levelRepo.buildAddLevelSql(level)){
            return this.selectLevelById(level.getId());
        }
        else
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,"Fail to create new Level!!!!");
    }

    @Override
    public LevelDto updateLevelByUuid(String uuid, AddLevelDto updateLevelDto) {
        Level level = levelMapStruct.addLevelDtoToLevel(updateLevelDto);
        level.setUuid(uuid);
        if (levelRepo.buildEditLevelSql(level)){
            return this.selectLevelByUuid(uuid);
        }
        else
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Fail to edit level !!!");
    }

    @Override
    public String deleteLevelByUuid(String uuid) {
        if (levelRepo.buildDeleteLevelSql(uuid)){
            return uuid;
        }
        else
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Fail to delete level!!!");
    }

    @Override
    public LevelDto selectLevelByUuid(String uuid) {
        Level level = levelRepo.buildSelectLevelByUuidSql(uuid).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,String.format("Undefined level with uuid %s", uuid)));
        return levelMapStruct.LevelToLevelDto(level);
    }
}
