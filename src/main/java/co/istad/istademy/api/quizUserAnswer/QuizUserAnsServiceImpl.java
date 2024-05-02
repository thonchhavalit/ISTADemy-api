package co.istad.istademy.api.quizUserAnswer;

import co.istad.istademy.api.quizUserAnswer.web.QuizUserAnswerDto;
import co.istad.istademy.api.quizUserAnswer.web.SaveQuizUserAnswerDto;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class QuizUserAnsServiceImpl implements QuizUserAnsService {
    private final QuizUserAnsRepo quizUserAnsRepo;
    private final QuizUserAnswerMapStruct answerMapStruct;


    @Override
    public PageInfo<QuizUserAnswerDto> selectAllQuizUserAnswer(int page, int limit) {
        PageInfo<QuizUserAnswer> quizUserAnswerPageInfo = PageHelper.startPage(page, limit).doSelectPageInfo(quizUserAnsRepo::buildSelectQuizUserAnswerSql);
        return answerMapStruct.pageInfoQuizUserAnswerToPageInfoQuizUserAnswerDto(quizUserAnswerPageInfo);
    }

    @Override
    public QuizUserAnswerDto insertQuizUserAnswer(SaveQuizUserAnswerDto saveQuizUserAnswerDto) {
        QuizUserAnswer quizUserAnswer = answerMapStruct.saveQuizUserAnswerDtoToQuizUserAnswer(saveQuizUserAnswerDto);
        quizUserAnswer.setUuid(UUID.randomUUID().toString());
        if (quizUserAnsRepo.buildInsertQuizUserAnswerSql(quizUserAnswer)) {
            return selectQuizUserAnswerById(quizUserAnswer.getId());
        }
        else
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,"Fail to create new Quiz user answer!!!");
    }

    @Override
    public QuizUserAnswerDto selectQuizUserAnswerByUuid(String uuid) {
        QuizUserAnswer quizUserAnswer = quizUserAnsRepo.buildSelectQuizUserAnswerByUuidSql(uuid).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Undefined quiz user answer with uuid %s", uuid)));
        return answerMapStruct.QuizUserAnswerToQuizUserAnswerDto(quizUserAnswer);
    }

    @Override
    public QuizUserAnswerDto selectQuizUserAnswerById(Integer id) {
        QuizUserAnswer quizUserAnswer = quizUserAnsRepo.buildSelectQuizUserAnswerByIdSql(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,String.format("Undefined quiz user answer with id %d",id)));
        return answerMapStruct.QuizUserAnswerToQuizUserAnswerDto(quizUserAnswer);
    }

    @Override
    public String deleteQuizUserAnsByUuid(String uuid) {
        if (quizUserAnsRepo.buildDeleteQuizUserAnswerByUuidSql(uuid)){
            return uuid;
        }
        else
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,String.format("Undefined quiz user answer with uuid %s",uuid));
    }

    @Override
    public QuizUserAnswerDto updateQuizUserAnsByUuid(String uuid, SaveQuizUserAnswerDto updateQuizUserAnsDto) {
        QuizUserAnswer quizUserAnswer = answerMapStruct.saveQuizUserAnswerDtoToQuizUserAnswer(updateQuizUserAnsDto);
        quizUserAnswer.setUuid(uuid);
        if (quizUserAnsRepo.buildUpdateQuizUserAnswerByUuidSql(quizUserAnswer)){
            return this.selectQuizUserAnswerByUuid(uuid);
        }
        else
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Fail to update the quiz user answer!!!!");
    }
}
