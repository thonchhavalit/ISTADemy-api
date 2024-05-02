package co.istad.istademy.api.quizUserAnswer.web;

import co.istad.istademy.api.quizUserAnswer.QuizUserAnsService;
import co.istad.istademy.base.BaseRest;
import com.github.pagehelper.PageInfo;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/quizUserAnswer")
public class QuizUserAnsRestController {
    private final QuizUserAnsService service;

    @GetMapping
    public BaseRest<?> selectAllQuizUserAnswer(
            @RequestParam(required = false,defaultValue = "1",name = "page")int page,
            @RequestParam(required = false, defaultValue = "20", name = "limit")int limit
    ){
        PageInfo<QuizUserAnswerDto> selectAll = service.selectAllQuizUserAnswer(page, limit);
        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .timestamp(LocalDateTime.now())
                .message("Successfully retrieved all quiz user answer!!")
                .data(selectAll)
                .build();
    }

    @GetMapping("/by/{uuid}")
    public BaseRest<?> selectQuizUserAnswerByUuid(@PathVariable String uuid){
        QuizUserAnswerDto selectQuizUserAnswerByUuid = service.selectQuizUserAnswerByUuid(uuid);
        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .timestamp(LocalDateTime.now())
                .data(selectQuizUserAnswerByUuid)
                .message("Find quiz user answer has defined by UUID!!!")
                .build();
    }

    @GetMapping("/{id}")
    public BaseRest<?> selectQuizUserAnswerById(@PathVariable Integer id){
        QuizUserAnswerDto selectQuizUserAnswerById = service.selectQuizUserAnswerById(id);
        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .timestamp(LocalDateTime.now())
                .data(selectQuizUserAnswerById)
                .message("Find quiz user answer has defined by ID!!!")
                .build();
    }

    @PostMapping
    public BaseRest<?> insertQuizUserAnswer(@Valid @RequestBody SaveQuizUserAnswerDto saveQuizUserAnswerDto){
        QuizUserAnswerDto insertQuizUserAnswer = service.insertQuizUserAnswer(saveQuizUserAnswerDto);
        return BaseRest.builder()
                .code(HttpStatus.OK.value())
                .status(true)
                .timestamp(LocalDateTime.now())
                .data(insertQuizUserAnswer)
                .message("Insert quiz user answer is Successful!!!!")
                .build();
    }

    @PutMapping("/{uuid}")
    public BaseRest<?> updateQuizUserAnsByUuid(@PathVariable String uuid, @Valid @RequestBody SaveQuizUserAnswerDto updateQuizUserAnswerDto){
        QuizUserAnswerDto updateQuizUserAnsByUuid = service.updateQuizUserAnsByUuid(uuid, updateQuizUserAnswerDto);
        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .timestamp(LocalDateTime.now())
                .data(updateQuizUserAnsByUuid)
                .message("Update quiz user answer is Successful!!!!!")
                .build();
    }

    @DeleteMapping("/{uuid}")
    public BaseRest<?> deleteQuizUserAnsByUuid(@PathVariable("uuid") String uuid){
        String isDeleted = service.deleteQuizUserAnsByUuid(uuid);
        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .data(isDeleted)
                .timestamp(LocalDateTime.now())
                .message("Delete quiz user answer is Successful!!!")
                .build();
    }
    
}
