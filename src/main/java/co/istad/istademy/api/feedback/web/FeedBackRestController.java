package co.istad.istademy.api.feedback.web;

import co.istad.istademy.api.feedback.FeedBackService;
import co.istad.istademy.base.BaseRest;
import com.github.pagehelper.PageInfo;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/feedbacks")
public class FeedBackRestController {
    private final FeedBackService feedBackService;

    @GetMapping
    public BaseRest<?> selectAllFeedBacks(
            @RequestParam(required = false, defaultValue = "1", name = "page") int page,
            @RequestParam(required = false, defaultValue = "20", name = "limit") int limit)
    {
        PageInfo<FeedBackDto> selectAll = feedBackService.selectAllFeedBacks(page, limit);
        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .timestamp(LocalDateTime.now())
                .message("Successfully retrieved all feedbacks.")
                .data(selectAll)
                .build();
    }

    @DeleteMapping("/{uuid}")
    public BaseRest<?> deleteFeedBackByUuid(@PathVariable("uuid") String uuid) {
        String deletedUuid = feedBackService.deleteFeedBackByUuid(uuid);
        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .timestamp(LocalDateTime.now())
                .message("Successfully deleted a feedback!")
                .data(deletedUuid)
                .build();
    }

    @PostMapping
    public BaseRest<?> insertFeedbacks(@Valid @RequestBody CreateFeedBackDto createFeedBackDto) {
        FeedBackDto feedBackDto = feedBackService.insertFeedbacks(createFeedBackDto);
        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .timestamp(LocalDateTime.now())
                .message("Successfully inserted a feedback.")
                .data(feedBackDto)
                .build();
    }

    @PutMapping("/{uuid}")
    public BaseRest<?> updateFeedBackByUuid(@PathVariable("uuid") String uuid,@Valid @RequestBody CreateFeedBackDto updateFeedBackDto) {
        FeedBackDto feedBackDto = feedBackService.updateFeedBackByUuid(uuid, updateFeedBackDto);
        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .timestamp(LocalDateTime.now())
                .message("Successfully updated a feedback.")
                .data(feedBackDto)
                .build();
    }

    @GetMapping("/by/{uuid}")
    public BaseRest<?> selectByFeedBackUuid(@PathVariable String uuid){
        FeedBackDto selectFeedBackByUuid = feedBackService.selectFeedBackByUuid(uuid);
        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .timestamp(LocalDateTime.now())
                .data(selectFeedBackByUuid)
                .message("Find exercise answer has defined by UUID!!!")
                .build();
    }

    @GetMapping("/{id}")
    public BaseRest<?> selectByFeedBackId(@PathVariable Integer id){
        FeedBackDto selectFeedBackById = feedBackService.selectFeedBackById(id);
        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .timestamp(LocalDateTime.now())
                .data(selectFeedBackById)
                .message("Find exercise answer has defined by ID!!!")
                .build();
    }

}
