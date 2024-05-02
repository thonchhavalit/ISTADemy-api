package co.istad.istademy.api.quiz;

import co.istad.istademy.api.quiz.web.QuizDto;
import co.istad.istademy.api.quiz.web.SaveQuizDto;
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
public class QuizServiceImpl implements QuizService{
    private final QuizRepo quizRepo;
    private final QuizMapStruct mapStruct;
    @Override
    public PageInfo<QuizDto> selectAllQuiz(int page, int limit) {
        PageInfo<Quiz> quizPageInfo = PageHelper.startPage(page,limit).doSelectPageInfo(quizRepo::buildSelectQuizSql);
        return mapStruct.pageInfoQuizToPageInfoQuizDto(quizPageInfo);
    }

    @Override
    public QuizDto selectQuizById(Integer id) {
        Quiz quiz = quizRepo.buildSelectQuizByIdSql(id).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,String.format("Undefined quiz with id %d",id)));
        return mapStruct.quizToQuizDto(quiz);
    }

    @Override
    public QuizDto selectQuizByUuid(String uuid) {
        Quiz quiz = quizRepo.buildSelectQuizByUuidSql(uuid).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,String.format("Undefined quiz with uuid %s", uuid)));
        return mapStruct.quizToQuizDto(quiz);
    }

    @Override
    public QuizDto insertQuiz(SaveQuizDto saveQuizDto) {
        Quiz quiz = mapStruct.createQuizDtoToQuiz(saveQuizDto);
        quiz.setUuid("quiz"+UUID.randomUUID().toString());
        quiz.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        if (quizRepo.buildInsertQuizSql(quiz)){
            return this.selectQuizById(quiz.getId());
        }
        else {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Fail to create new quiz!!!");
        }
    }

    @Override
    public QuizDto updateQuizByUuid(String uuid, SaveQuizDto updateQuizDto) {
        if (quizRepo.isQuizUuidExists(uuid)){
            Quiz quiz = mapStruct.createQuizDtoToQuiz(updateQuizDto);
            quiz.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
            quiz.setUuid(uuid);
            if (quizRepo.buildUpdateQuizByUuidSql(quiz)){
                return this.selectQuizByUuid(uuid);
            }
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Fail to update quiz!!!");
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Undefined quiz with uuid %s",uuid));
    }

    @Override
    public String deleteQuizByUuid(String uuid) {
        if (quizRepo.isQuizUuidExists(uuid)){
            if (quizRepo.buildDeleteQuizByUuidSql(uuid)){
                return uuid;
            }
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,"Fail to delete quiz!!!");
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND,String.format("Undefined quiz with uuid %s ",uuid));
    }

    @Override
    public List<Quiz> selectQuizByLessonId(Integer id) {
        return quizRepo.buildSelectQuizByLessonIdSql(id);
    }
}
