package co.istad.istademy.api.contentProgress.web;

import co.istad.istademy.api.commentLike.web.CommentLikeDto;
import co.istad.istademy.api.commentLike.web.SaveCommentLikeDto;
import co.istad.istademy.api.contentProgress.ContentProgress;
import co.istad.istademy.api.contentProgress.ContentProgressMapper;
import co.istad.istademy.api.contentProgress.ContentProgressService;
import co.istad.istademy.base.BaseRest;
import com.github.pagehelper.PageInfo;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@RequestMapping("/api/v1/contentProgresses")
@RestController
public class ContentProgressRestController {
    private final ContentProgressService contentProgressService;
    @GetMapping
    public BaseRest<?> selectAllContentProgress(
            @RequestParam(required = false,defaultValue = "1",name = "page")int page,
            @RequestParam(required = false, defaultValue = "20", name = "limit")int limit,
             @RequestParam(required = false, defaultValue = "0") Integer userId,
            @RequestParam(required = false, defaultValue = "0") Integer contentId
    ){
        PageInfo<ContentProgress> selectAll = contentProgressService.selectContentProgresses(page, limit, userId, contentId);
        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .timestamp(LocalDateTime.now())
                .message("Successfully retrieved content progresses")
                .data(selectAll)
                .build();
    }

    @PostMapping
    public BaseRest<?> insertIsContentRead(@Valid @RequestBody SaveContentProgressDto saveContentProgressDto){
        ContentProgress contentProgress = contentProgressService.insertSetIsContentRead(saveContentProgressDto);
        return BaseRest.builder()
                .code(HttpStatus.OK.value())
                .status(true)
                .timestamp(LocalDateTime.now())
                .data(contentProgress)
                .message("Insert content progress is Successful!!!!")
                .build();
    }

    @GetMapping("/isExist")
    public Boolean isContentProgressExist(
            @RequestParam Integer userId,
            @RequestParam Integer contentId
    ){
        return contentProgressService.selectIsContentProgressExist(userId, contentId);
    }
}
