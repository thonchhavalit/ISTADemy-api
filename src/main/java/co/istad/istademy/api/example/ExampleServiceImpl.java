package co.istad.istademy.api.example;

import co.istad.istademy.api.example.web.ExampleDto;
import co.istad.istademy.api.example.web.SaveExampleDto;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ExampleServiceImpl implements ExampleService{
    private final ExampleRepo exampleRepo;
    private final ExampleMapStruct exampleMapStruct;
    @Override
    public PageInfo<ExampleDto> selectAllExamples(int page, int limit) {
        PageInfo<Example> examplePageInfo = PageHelper.startPage(page, limit).doSelectPageInfo(exampleRepo::buildSelectExampleSql);
        return exampleMapStruct.pageInfoExampleToPageInfoExampleDto(examplePageInfo);
    }

    @Override
    public ExampleDto insertExample(SaveExampleDto saveExampleDto) {
        Example example = exampleMapStruct.createExampleDtoToExample(saveExampleDto);
        example.setUuid(UUID.randomUUID().toString());
        example.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        if (exampleRepo.buildInsertExampleSql(example)){
            return this.selectExampleById(example.getId());
        }
        else {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Fail to create new example");
        }
    }

    @Override
    public ExampleDto selectExampleById(Integer id) {
        Example example = exampleRepo.buildSelectExampleByIdSql(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Undefined example with id %d :",id)));
        return exampleMapStruct.exampleToExampleDto(example);
    }

    @Override
    public ExampleDto selectExampleByUuid(String uuid) {
        Example example = exampleRepo.buildSelectExampleByUuidSql(uuid).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,String.format("Undefined example with uuid %s", uuid)));
        return exampleMapStruct.exampleToExampleDto(example);
    }

    @Override
    public String deleteExampleByUuid(String uuid) {
        if (exampleRepo.isExampleUuidExists(uuid)){
            if (exampleRepo.buildDeleteExampleByUuidSql(uuid)){
                return uuid;
            }
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Fail to delete example!!!");
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Undefined example with uuid %s", uuid));
    }

    @Override
    public ExampleDto updateExampleByUuid(String uuid, SaveExampleDto updateExampleDto) {
            if (exampleRepo.isExampleUuidExists(uuid)){
                Example example = exampleMapStruct.createExampleDtoToExample(updateExampleDto);
                example.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
                example.setUuid(uuid);
                if (exampleRepo.buildUpdatedExampleByUuidSql(example)) {
                    return this.selectExampleByUuid(uuid);
                }

                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Fail to update example!!!");
            }
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Undefined example with uuid %s", uuid));
    }

    @Override
    public List<Example> selectExampleByLessonId(Integer id) {
        return exampleRepo.buildSelectExampleByLessonIdSql(id);
    }
}
