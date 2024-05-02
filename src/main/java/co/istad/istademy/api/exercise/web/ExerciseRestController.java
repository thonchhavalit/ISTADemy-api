package co.istad.istademy.api.exercise.web;

import co.istad.istademy.api.exercise.Exercise;
import co.istad.istademy.api.exercise.ExerciseService;
import co.istad.istademy.base.BaseRest;
import com.github.pagehelper.PageInfo;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/exercises")
public class ExerciseRestController {

    private final ExerciseService exerciseService;

    @GetMapping
    public BaseRest<?> selectAllExercise(
            @RequestParam(required = false, defaultValue = "1", name = "page")int page,
            @RequestParam(required = false,defaultValue = "20" , name = "limit")int limit
    ){
        PageInfo<ExerciseDto> selectAll = exerciseService.selectAllExercise(page, limit);
        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .timestamp(LocalDateTime.now())
                .data(selectAll)
                .message("Successfully Select All Exercises!!!!")
                .build();
    }
    @GetMapping("/{id}")
    public BaseRest<?> selectExerciseById(@PathVariable Integer id){
        ExerciseDto selectId = exerciseService.selectExerciseById(id);
        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .timestamp(LocalDateTime.now())
                .data(selectId)
                .message("Successfully Select All by ID!!!")
                .build();
    }

    @GetMapping("/by/{uuid}")
    public BaseRest<?> selectExerciseByUuid(@PathVariable String uuid){
        ExerciseDto selectUuid = exerciseService.selectExerciseByUuid(uuid);
        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .timestamp(LocalDateTime.now())
                .data(selectUuid)
                .message("Successfully Select All by UUID!!!")
                .build();
    }

    @PostMapping
    public BaseRest<?> insertExercise(@RequestBody @Valid SaveExerciseDto saveExerciseDto){
        ExerciseDto inserted = exerciseService.insertExercise(saveExerciseDto);
        return BaseRest.builder()
                .code(HttpStatus.OK.value())
                .status(true)
                .timestamp(LocalDateTime.now())
                .data(inserted)
                .message("Create Exercises Success!!!!")
                .build();
    }

    @PutMapping("/{uuid}")
    public BaseRest<?> updateExerciseByUuid(@PathVariable String uuid, @Valid @RequestBody SaveExerciseDto updateExerciseDto){
        ExerciseDto updated = exerciseService.updateExerciseByUuid(uuid,updateExerciseDto);
        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .timestamp(LocalDateTime.now())
                .data(updated)
                .message("Update Exercise is Successful!!!!!")
                .build();
    }

    @DeleteMapping("/{uuid}")
    public BaseRest<?> deleteExerciseByUuid(@PathVariable String uuid){
        String deleted = exerciseService.deleteExerciseByUuid(uuid);
        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .data(deleted)
                .timestamp(LocalDateTime.now())
                .message("Delete Exercise is Successful!!!")
                .build();
    }

    @GetMapping("/lesson/{id}")
    public BaseRest<?> selectExerciseByLessonId(@PathVariable Integer id){
        List<Exercise> selectByLessonId = exerciseService.selectExerciseByLessonId(id);
        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .timestamp(LocalDateTime.now())
                .data(selectByLessonId)
                .message("Successfully Select Exercise by Lesson ID!!!")
                .build();
    }

}
