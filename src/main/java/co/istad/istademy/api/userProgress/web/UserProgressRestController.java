package co.istad.istademy.api.userProgress.web;

import co.istad.istademy.api.quizProgresses.QuizProgress;
import co.istad.istademy.api.userProgress.UserProgress;
import co.istad.istademy.api.userProgress.UserProgressService;
import co.istad.istademy.base.BaseRest;
import com.github.pagehelper.PageInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/userProgress")
public class UserProgressRestController {

    private final UserProgressService userProgressService;

    @GetMapping("/inProgress/{userId}")
    public BaseRest<?> getInProgressCourses(
            @PathVariable Integer userId
    ){
        List<UserProgress> selectAll = userProgressService.getUserProgress(userId);
        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .timestamp(LocalDateTime.now())
                .message("Successfully retrieved all quiz progresses")
                .data(selectAll)
                .build();
    }

    @GetMapping("/completed/{userId}")
    public BaseRest<?> getCompletedCourses(
            @PathVariable Integer userId
    ){
        List<UserProgress> selectAll = userProgressService.getCompletedCourses(userId);
        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .timestamp(LocalDateTime.now())
                .message("Successfully retrieved all quiz progresses")
                .data(selectAll)
                .build();
    }
}

