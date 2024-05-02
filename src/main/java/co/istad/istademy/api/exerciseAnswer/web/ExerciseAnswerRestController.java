package co.istad.istademy.api.exerciseAnswer.web;

import co.istad.istademy.api.exerciseAnswer.ExerciseAnswerService;
import co.istad.istademy.base.BaseRest;
import com.github.pagehelper.PageInfo;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@RequestMapping("/api/v1/exerciseAnswer")
@RestController
public class ExerciseAnswerRestController {
    private final ExerciseAnswerService answerService;

    @GetMapping
    public BaseRest<?> selectAllExerciseAnswer(
            @RequestParam(required = false,defaultValue = "1",name = "page")int page,
            @RequestParam(required = false, defaultValue = "20", name = "limit")int limit
    ){
        PageInfo<ExerciseAnswerDto> selectAllExerciseAnswer = answerService.selectAllExerciseAnswer(page, limit);
        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .timestamp(LocalDateTime.now())
                .message("Successfully retrieved all exercise answer!!!")
                .data(selectAllExerciseAnswer)
                .build();
    }

    @GetMapping("/by/{uuid}")
    public BaseRest<?> selectByExerciseAnswerUuid(@PathVariable String uuid){
        ExerciseAnswerDto selectByExerciseAnswerUuid = answerService.selectByExerciseAnswerUuid(uuid);
        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .timestamp(LocalDateTime.now())
                .data(selectByExerciseAnswerUuid)
                .message("Find exercise answer has defined by UUID!!!")
                .build();
    }

    @GetMapping("/{id}")
    public BaseRest<?> selectByExerciseAnswerId(@PathVariable Integer id){
        ExerciseAnswerDto selectByExerciseAnswerId = answerService.selectByExerciseAnswerId(id);
        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .timestamp(LocalDateTime.now())
                .data(selectByExerciseAnswerId)
                .message("Find exercise answer has defined by ID!!!")
                .build();
    }

    @PostMapping
    public BaseRest<?> insertExerciseAnswer(@Valid @RequestBody SaveExerciseAnswerDto saveExerciseAnswerDto){
        ExerciseAnswerDto insertExerciseAnswer = answerService.insertExerciseAnswer(saveExerciseAnswerDto);
        return BaseRest.builder()
                .code(HttpStatus.OK.value())
                .status(true)
                .timestamp(LocalDateTime.now())
                .data(insertExerciseAnswer)
                .message("Insert exercise answer is Successful!!!!")
                .build();
    }

    @PutMapping("/{uuid}")
    public BaseRest<?> updateExerciseAnswerByUuid(@PathVariable String uuid, @Valid @RequestBody SaveExerciseAnswerDto updateExerciseAnswerDto){
        ExerciseAnswerDto updateExerciseAnswerByUuid = answerService.updateExerciseAnswerByUuid(uuid, updateExerciseAnswerDto);
        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .timestamp(LocalDateTime.now())
                .data(updateExerciseAnswerByUuid)
                .message("Update exercise answer is Successful!!!!!")
                .build();
    }

    @DeleteMapping("/{uuid}")
    public BaseRest<?> deleteExerciseAnswerByUuid(@PathVariable("uuid") String uuid){
        String isDeleted = answerService.deleteExerciseAnswerByUuid(uuid);
        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .data(isDeleted)
                .timestamp(LocalDateTime.now())
                .message("Delete Exercise Answer is Successful!!!")
                .build();
    }
}
