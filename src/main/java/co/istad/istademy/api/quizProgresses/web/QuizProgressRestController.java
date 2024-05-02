package co.istad.istademy.api.quizProgresses.web;

import co.istad.istademy.api.quizProgresses.QuizProgress;
import co.istad.istademy.api.quizProgresses.QuizProgressService;
import co.istad.istademy.base.BaseRest;
import com.github.pagehelper.PageInfo;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@RequestMapping("/api/v1/quizProgresses")
@RestController
public class QuizProgressRestController {

        private final QuizProgressService quizProgressService;
        @GetMapping
        public BaseRest<?> selectAllQUizProgress(
                @RequestParam(required = false,defaultValue = "1",name = "page")int page,
                @RequestParam(required = false, defaultValue = "20", name = "limit")int limit,
                @RequestParam(required = false, defaultValue = "0") Integer userId,
                @RequestParam(required = false, defaultValue = "0") Integer quizId
        ){
            PageInfo<QuizProgress> selectAll = quizProgressService.selectQuizProgresses(page, limit, userId, quizId);
            return BaseRest.builder()
                    .status(true)
                    .code(HttpStatus.OK.value())
                    .timestamp(LocalDateTime.now())
                    .message("Successfully retrieved all quiz progresses")
                    .data(selectAll)
                    .build();
        }

        @PostMapping
        public BaseRest<?> insertIsContentRead(@Valid @RequestBody SaveQuizProgressDto saveQuizProgressDto){
            QuizProgress quizProgress = quizProgressService.insertSetIsQuizTaken(saveQuizProgressDto);
            return BaseRest.builder()
                    .code(HttpStatus.OK.value())
                    .status(true)
                    .timestamp(LocalDateTime.now())
                    .data(quizProgress)
                    .message("Insert quiz progress Successful!!!!")
                    .build();
        }

        @GetMapping("/isExist")
        public Boolean isQuizProgressExist(
                @RequestParam Integer userId,
                @RequestParam Integer quizId
        ){
            return quizProgressService.selectIsQuizProgressExist(userId, quizId);
        }

}
