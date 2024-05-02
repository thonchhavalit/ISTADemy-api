package co.istad.istademy.api.comment.web;

import co.istad.istademy.api.comment.Comment;
import co.istad.istademy.api.comment.CommentService;
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
@RequestMapping("/api/v1/comments")
public class CommentRestController {
    private final CommentService commentService;
    @GetMapping
    public BaseRest<?> selectAllComments(
            @RequestParam(required = false,defaultValue = "1",name = "page")int page,
            @RequestParam(required = false, defaultValue = "20", name = "limit")int limit,
            @RequestParam(required = false, defaultValue = "0", name = "lessonId")Integer lessonId
    ){
        PageInfo<CommentDto> selectAll = commentService.selectAllComments(page, limit, lessonId);
        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .timestamp(LocalDateTime.now())
                .message("Successfully retrieved all comments")
                .data(selectAll)
                .build();
    }

    @GetMapping("/{id}")
    public BaseRest<?> selectCommentById(@PathVariable Integer id){
        CommentDto selectCommentById = commentService.selectCommentById(id);
        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .timestamp(LocalDateTime.now())
                .data(selectCommentById)
                .message("Comment has defined by ID!!!")
                .build();
    }

    @GetMapping("/by/{uuid}")
    public BaseRest<?> selectCommentByUuid(@PathVariable String uuid){
        CommentDto selectCommentByUuid = commentService.selectCommentByUuid(uuid);
        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .timestamp(LocalDateTime.now())
                .data(selectCommentByUuid)
                .message("Comment has defined by UUID!!!")
                .build();
    }

    @PostMapping
    public BaseRest<?> insertComment(@Valid @RequestBody SaveCommentDto saveCommentDto){
        CommentDto commentDto = commentService.insertComment(saveCommentDto);
        return BaseRest.builder()
                .code(HttpStatus.OK.value())
                .status(true)
                .timestamp(LocalDateTime.now())
                .data(commentDto)
                .message("Insert Comment is Successful!!!!")
                .build();
    }

    @PutMapping("/{uuid}")
    public BaseRest<?> updateCommentById(@PathVariable String uuid, @Valid @RequestBody SaveCommentDto updateCommentDto){
        CommentDto commentDto = commentService.updateCommentByUuid(uuid, updateCommentDto);
        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .timestamp(LocalDateTime.now())
                .data(commentDto)
                .message("Update Example is Successful!!!!!")
                .build();
    }

    @DeleteMapping("/{uuid}")
    public BaseRest<?> deleteCommentById(@PathVariable("uuid") String uuid){
        String isDeleted = commentService.deleteCommentByUuid(uuid);
        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .data(isDeleted)
                .timestamp(LocalDateTime.now())
                .message("Delete Comment is Successful!!!")
                .build();
    }

    @GetMapping("/user/{id}")
    public BaseRest<?> selectCommentByUserId(@PathVariable Integer id){
        List<Comment> selectByUserId = commentService.selectCommentByUserId(id);
        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .timestamp(LocalDateTime.now())
                .data(selectByUserId)
                .message("Comment has defined by User ID!!!")
                .build();
    }
    @GetMapping("/lesson/{id}")
    public BaseRest<?> selectCommentByLessonId(@PathVariable Integer id){
        List<Comment> selectByLessonId = commentService.selectCommentByLessonId(id);
        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .timestamp(LocalDateTime.now())
                .data(selectByLessonId)
                .message("Comment has defined by Lesson ID!!!")
                .build();
    }

}
