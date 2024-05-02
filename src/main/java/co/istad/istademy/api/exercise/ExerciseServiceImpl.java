package co.istad.istademy.api.exercise;

import co.istad.istademy.api.exercise.web.ExerciseDto;
import co.istad.istademy.api.exercise.web.SaveExerciseDto;
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
public class ExerciseServiceImpl implements ExerciseService{
    private final ExerciseRepo exerciseRepo;
    private final ExerciseMapStruct mapStruct;
    @Override
    public PageInfo<ExerciseDto> selectAllExercise(int page, int limit) {
        PageInfo<Exercise> exercisePageInfo = PageHelper.startPage(page,limit).doSelectPageInfo(exerciseRepo::buildSelectExerciseSql);
        return mapStruct.pageInfoExerciseToPageInfoExerciseDto(exercisePageInfo);
    }

    @Override
    public ExerciseDto selectExerciseById(Integer id) {
        Exercise exercise = exerciseRepo.buildSelectExerciseByIdSql(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,String.format("Undefined exercise with id %d ",id)));
        return mapStruct.exerciseToExerciseDto(exercise);
    }

    @Override
    public ExerciseDto selectExerciseByUuid(String uuid) {
        Exercise exercise = exerciseRepo.buildSelectExerciseByUuidSql(uuid).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,String.format("Undefined exercise with uuid %s", uuid)));
        return mapStruct.exerciseToExerciseDto(exercise);
    }

    @Override
    public ExerciseDto insertExercise(SaveExerciseDto saveExerciseDto) {
        Exercise exercise = mapStruct.createExercieDtoToExercise(saveExerciseDto);
        exercise.setUuid(UUID.randomUUID().toString());
        exercise.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        if (exerciseRepo.buildInsertExerciseSql(exercise)){
            return this.selectExerciseById(exercise.getId());
        }
        else {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Fail to create new exercise!!!!");
        }
    }

    @Override
    public ExerciseDto updateExerciseByUuid(String uuid, SaveExerciseDto updateExerciseDto) {
        if (exerciseRepo.isExerciseUuidExists(uuid)){
            Exercise exercise = mapStruct.createExercieDtoToExercise(updateExerciseDto);
            exercise.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
            exercise.setUuid(uuid);
            if (exerciseRepo.buildUpdateExerciseByUuidSql(exercise)){
                return this.selectExerciseByUuid(uuid);
            }
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Fail to update exercise!!!");
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Undefined exercise with uuid %s",uuid));
    }

    @Override
    public String deleteExerciseByUuid(String uuid) {
        if (exerciseRepo.isExerciseUuidExists(uuid)){
            if (exerciseRepo.buildDeleteExerciseByUuidSql(uuid)){
                return uuid;
            }
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Fail to delete exercise!!!");
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND,String.format("Undefined exercise with uuid %s", uuid));
    }

    @Override
    public List<Exercise> selectExerciseByLessonId(Integer id) {
        return exerciseRepo.buildSelectExerciseByLessonIdSql(id);
    }
}
