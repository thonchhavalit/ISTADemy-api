package co.istad.istademy.api.exerciseAnswer;

import co.istad.istademy.api.exerciseAnswer.web.ExerciseAnswerDto;
import co.istad.istademy.api.exerciseAnswer.web.SaveExerciseAnswerDto;
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
public class ExerciseAnswerServiceImpl implements ExerciseAnswerService{
    private final ExerciseAnswerRepo exerciseAnswerRepo;
    private final ExerciseAnswerMapStruct answerMapStruct;
    @Override
    public PageInfo<ExerciseAnswerDto> selectAllExerciseAnswer(int page, int limit) {
        PageInfo<ExerciseAnswer> exerciseAnswerPageInfo = PageHelper.startPage(page,limit).doSelectPageInfo(exerciseAnswerRepo::buildSelectExerciseAnswerSql);
        return answerMapStruct.pageInfoExerciseAnswerToPageInfoExerciseAnswerDto(exerciseAnswerPageInfo);
    }

    @Override
    public ExerciseAnswerDto insertExerciseAnswer(SaveExerciseAnswerDto saveExerciseAnswerDto) {
        ExerciseAnswer exerciseAnswer = answerMapStruct.saveExerciseAnswerDtoToExerciseAnswer(saveExerciseAnswerDto);
        exerciseAnswer.setUuid(UUID.randomUUID().toString());
        exerciseAnswer.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        if (exerciseAnswerRepo.buildInsertExerciseAnswerSql(exerciseAnswer)){
            return selectByExerciseAnswerId(exerciseAnswer.getId());
        }
        else
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Fail to create exercise answer!!!!");
    }

    @Override
    public ExerciseAnswerDto selectByExerciseAnswerUuid(String uuid) {
        ExerciseAnswer exerciseAnswer = exerciseAnswerRepo.buildSelectExerciseAnswerByUuidSql(uuid).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,String.format("Undefined exercise answer with uuid %s",uuid)));
        return answerMapStruct.ExerciseAnswerToExerciseAnswerDto(exerciseAnswer);
    }

    @Override
    public ExerciseAnswerDto selectByExerciseAnswerId(Integer id) {
        ExerciseAnswer exerciseAnswer =  exerciseAnswerRepo.buildSelectExerciseAnswerByIdSql(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,String.format("Undefined exercise correct answer with id %d", id)));
        return answerMapStruct.ExerciseAnswerToExerciseAnswerDto(exerciseAnswer);
    }

    @Override
    public String deleteExerciseAnswerByUuid(String uuid) {
            if (exerciseAnswerRepo.buildDeleteExerciseAnswerByUuidSql(uuid)){
                return uuid;
            }
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Fail to delete exercise!!!");
        }


    @Override
    public ExerciseAnswerDto updateExerciseAnswerByUuid(String uuid, SaveExerciseAnswerDto updateExerciseAnswerDto) {
            ExerciseAnswer exerciseAnswer = answerMapStruct.saveExerciseAnswerDtoToExerciseAnswer(updateExerciseAnswerDto);
            exerciseAnswer.setIsCorrected(true);
            exerciseAnswer.setUuid(uuid);
            if (exerciseAnswerRepo.buildUpdateExerciseAnswerByUuidSql(exerciseAnswer)){
                return this.selectByExerciseAnswerUuid(uuid);
            }
            else
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,"Fail to update exercise answer!!!!!");
        }

}
