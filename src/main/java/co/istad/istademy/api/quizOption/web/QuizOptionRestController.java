package co.istad.istademy.api.quizOption.web;

import co.istad.istademy.api.quizOption.QuizOptionService;
import co.istad.istademy.base.BaseRest;
import com.github.pagehelper.PageInfo;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/quizOption")
public class QuizOptionRestController {
    private final QuizOptionService optionService;

    @GetMapping
    public BaseRest<?> selectAllQuizOption(
            @RequestParam(required = false,defaultValue = "1",name = "page")int page,
            @RequestParam(required = false, defaultValue = "20", name = "limit")int limit
    ){
        PageInfo<QuizOptionDto> selectAll = optionService.selectAllQuizOption(page, limit);
        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .timestamp(LocalDateTime.now())
                .message("Successfully retrieved all quiz option")
                .data(selectAll)
                .build();
    }

    @GetMapping("/by/{uuid}")
    public BaseRest<?> selectQuizOptionByUuid(@PathVariable String uuid){
        QuizOptionDto selectQuizOptionByUuid = optionService.selectQuizOptionByUuid(uuid);
        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .timestamp(LocalDateTime.now())
                .data(selectQuizOptionByUuid)
                .message("Find quiz option has defined by UUID!!!")
                .build();
    }

    @GetMapping("/{id}")
    public BaseRest<?> selectQuizOptionById(@PathVariable Integer id){
        QuizOptionDto selectQuizOptionById = optionService.selectQuizOptionById(id);
        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .timestamp(LocalDateTime.now())
                .data(selectQuizOptionById)
                .message("Find quiz option has defined by ID!!!")
                .build();
    }

    @PostMapping
    public BaseRest<?> insertQuizOption(@Valid @RequestBody SaveQuizOptionDto saveQuizOptionDto){
        QuizOptionDto insertQuizOption = optionService.insertQuizOption(saveQuizOptionDto);
        return BaseRest.builder()
                .code(HttpStatus.OK.value())
                .status(true)
                .timestamp(LocalDateTime.now())
                .data(insertQuizOption)
                .message("Insert quiz option is Successful!!!!")
                .build();
    }

    @PutMapping("/{uuid}")
    public BaseRest<?> updateQuizOptionByUuid(@PathVariable String uuid, @Valid @RequestBody SaveQuizOptionDto updateQuizOptionDto){
        QuizOptionDto updateQuizOptionByUuid = optionService.updateQuizOptionByUuid(uuid, updateQuizOptionDto);
        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .timestamp(LocalDateTime.now())
                .data(updateQuizOptionByUuid)
                .message("Update quiz option is Successful!!!!!")
                .build();
    }

    @DeleteMapping("/{uuid}")
    public BaseRest<?> deleteQuizOptionByUuid(@PathVariable("uuid") String uuid){
        String isDeleted = optionService.deleteQuizOptionByUuid(uuid);
        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .data(isDeleted)
                .timestamp(LocalDateTime.now())
                .message("Delete quiz option is Successful!!!")
                .build();
    }

}
