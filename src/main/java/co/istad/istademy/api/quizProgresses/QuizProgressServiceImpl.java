package co.istad.istademy.api.quizProgresses;

import co.istad.istademy.api.quizProgresses.web.SaveQuizProgressDto;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.sql.Timestamp;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class QuizProgressServiceImpl implements QuizProgressService {
    private final QuizProgressMapper quizProgressMapper;
    private final QuizProgressMapStruct mapStruct;

    @Override
    public PageInfo<QuizProgress> selectQuizProgresses(int page, int limit, Integer userId, Integer quizId) {
        PageInfo<QuizProgress> contentProgressPageInfo = PageHelper.startPage(page, limit).doSelectPageInfo(
                () -> quizProgressMapper.buildSelectQuizProgressesSql(userId, quizId)
        );
        if (userId == 0 || quizId == 0 && quizProgressMapper.buildSelectQuizProgressesSql(userId, quizId).size() < 1) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    String.format("Quiz or User like with id %d , %d is not found.", quizId, userId)
            );
        }
        return contentProgressPageInfo;
    }

    @Override
    public QuizProgress insertSetIsQuizTaken(SaveQuizProgressDto saveQuizProgressDto) {
        QuizProgress quizProgress = mapStruct.createQuizProgressDtoToQuizProgress(saveQuizProgressDto);
        quizProgress.setUuid(UUID.randomUUID().toString());
        quizProgress.setIsTaken(true);
        quizProgress.setCompletedAt(new Timestamp(System.currentTimeMillis()));
        if (quizProgressMapper.buildSetIsQuizTakenSql(quizProgress)) {
            return quizProgress;
        } else {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Fail to Create Quiz Progress!!!");
        }
    }

    @Override
    public Boolean selectIsQuizProgressExist(Integer userId, Integer quizId) {
            return quizProgressMapper.isQuizProgressExists(userId, quizId);
    }
}
