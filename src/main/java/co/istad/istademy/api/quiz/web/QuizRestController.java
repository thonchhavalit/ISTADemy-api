package co.istad.istademy.api.quiz.web;

import co.istad.istademy.api.quiz.Quiz;
import co.istad.istademy.api.quiz.QuizService;
import co.istad.istademy.base.BaseRest;
import com.github.pagehelper.PageInfo;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/v1/quizzes")
@RequiredArgsConstructor
public class QuizRestController {
    private final QuizService quizService;

    @GetMapping
    public BaseRest<?> selectAllQuiz(
            @RequestParam(required = false, defaultValue = "1", name = "page")int page,
            @RequestParam(required = false,defaultValue = "20" , name = "limit")int limit
    ){
        PageInfo<QuizDto> selectAll = quizService.selectAllQuiz(page, limit);
        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .timestamp(LocalDateTime.now())
                .data(selectAll)
                .message("Successfully Select All Quizzes!!!!")
                .build();
    }
    @GetMapping("/{id}")
    public BaseRest<?> selectQuizById(@PathVariable Integer id){
        QuizDto selectId = quizService.selectQuizById(id);
        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .timestamp(LocalDateTime.now())
                .data(selectId)
                .message("Successfully Select All by ID!!!")
                .build();
    }

    @GetMapping("/by/{uuid}")
    public BaseRest<?> selectQuizByUuid(@PathVariable String uuid){
        QuizDto selectUuid = quizService.selectQuizByUuid(uuid);
        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .timestamp(LocalDateTime.now())
                .data(selectUuid)
                .message("Successfully Select All by UUID!!!")
                .build();
    }

    @PostMapping
    public BaseRest<?> insertQuiz(@RequestBody @Valid SaveQuizDto saveQuizDto){
        QuizDto inserted = quizService.insertQuiz(saveQuizDto);
        return BaseRest.builder()
                .code(HttpStatus.OK.value())
                .status(true)
                .timestamp(LocalDateTime.now())
                .data(inserted)
                .message("Create Quiz Success!!!!")
                .build();
    }

    @PutMapping("/{uuid}")
    public BaseRest<?> updateQuizByUuid(@PathVariable String uuid, @Valid @RequestBody SaveQuizDto updateQuizDto){
        QuizDto updated = quizService.updateQuizByUuid(uuid,updateQuizDto);
        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .timestamp(LocalDateTime.now())
                .data(updated)
                .message("Update Quiz is Successful!!!!!")
                .build();
    }

    @DeleteMapping("/{uuid}")
    public BaseRest<?> deleteQuizByUuid(@PathVariable String uuid){
        String deleted = quizService.deleteQuizByUuid(uuid);
        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .data(deleted)
                .timestamp(LocalDateTime.now())
                .message("Delete Quiz is Successful!!!")
                .build();
    }

    @GetMapping("/lesson/{id}")
    public BaseRest<?> selectQuizByLessonId(@PathVariable Integer id){
        List<Quiz> selectByLessonId = quizService.selectQuizByLessonId(id);
        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .data(selectByLessonId)
                .timestamp(LocalDateTime.now())
                .message("Successfully select quiz by lesson Id !!!!")
                .build();
    }
}