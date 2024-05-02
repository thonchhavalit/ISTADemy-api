package co.istad.istademy.api.quizOption;

import co.istad.istademy.api.quizOption.web.QuizOptionDto;
import co.istad.istademy.api.quizOption.web.SaveQuizOptionDto;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class QuizOptionServiceImpl implements QuizOptionService{
    private final QuizOptionRepo optionRepo;
    private final QuizOptionMapStruct optionMapStruct;
    @Override
    public PageInfo<QuizOptionDto> selectAllQuizOption(int page, int limit) {
        PageInfo<QuizOption> quizOptionPageInfo = PageHelper.startPage(page, limit).doSelectPageInfo(optionRepo::buildSelectQuizOptionSql);
        return optionMapStruct.pageInfoQuizOptionToQuizOptionDto(quizOptionPageInfo);
    }

    @Override
    public QuizOptionDto insertQuizOption(SaveQuizOptionDto saveQuizOptionDto) {
        QuizOption quizOption = optionMapStruct.saveQuizOptionDtoToQuizOption(saveQuizOptionDto);
        quizOption.setUuid(UUID.randomUUID().toString());
        if (optionRepo.buildInsertQuizOptionSql(quizOption)){
            return selectQuizOptionById(quizOption.getId());
        }
        else
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Fail to create quiz option!!!!");
    }

    @Override
    public QuizOptionDto selectQuizOptionByUuid(String uuid) {
        QuizOption quizOption = optionRepo.buildSelectQuizOptionByUuidSql(uuid).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,String.format("Undefined quiz option with uuid %s", uuid)));
        return optionMapStruct.QUizOptionToQuizOptionDto(quizOption);
    }

    @Override
    public QuizOptionDto selectQuizOptionById(Integer id) {
        QuizOption quizOption = optionRepo.buildSelectQuizOptionByIdSql(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,String.format("Undefined quiz option with id %d", id)));
        return optionMapStruct.QUizOptionToQuizOptionDto(quizOption);
    }

    @Override
    public String deleteQuizOptionByUuid(String uuid) {
        if (optionRepo.buildDeleteQuizOptionByUuidSql(uuid)){
            return uuid;
        }
        else
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Undefined quiz option with uuid %s",uuid));
    }

    @Override
    public QuizOptionDto updateQuizOptionByUuid(String uuid, SaveQuizOptionDto updateQuizOptionDto) {
        QuizOption quizOption = optionMapStruct.saveQuizOptionDtoToQuizOption(updateQuizOptionDto);
        quizOption.setUuid(uuid);
        if (optionRepo.buildUpdateQuizOptionByUuidSql(quizOption)){
            return this.selectQuizOptionByUuid(uuid);
        }
        else
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,"Fail to update quiz option!!!");
    }
}
