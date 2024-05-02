package co.istad.istademy.api.commentLike.web;

import co.istad.istademy.api.commentLike.CommentLikeService;
import co.istad.istademy.base.BaseRest;
import com.github.pagehelper.PageInfo;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@RequestMapping("/api/v1/commentLike")
@RestController
public class CommentLikeRestController {
    private final CommentLikeService commentLikeService;
    @GetMapping
    public BaseRest<?> selectAllCommentLike(
            @RequestParam(required = false,defaultValue = "1",name = "page")int page,
            @RequestParam(required = false, defaultValue = "20", name = "limit")int limit,
            @RequestParam(required = false, defaultValue = "0") Integer cmtId
    ){
        PageInfo<CommentLikeDto> selectAll = commentLikeService.selectAllCommentLike(page, limit, cmtId);
        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .timestamp(LocalDateTime.now())
                .message("Successfully retrieved all comment likes")
                .data(selectAll)
                .build();
    }

    @GetMapping("/by/{uuid}")
    public BaseRest<?> selectCommentLikeByUuid(@PathVariable String uuid){
        CommentLikeDto selectCommentLikeByUuid = commentLikeService.selectCommentLikeByUuid(uuid);
        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .timestamp(LocalDateTime.now())
                .data(selectCommentLikeByUuid)
                .message("Find Comment like has defined by UUID!!!")
                .build();
    }

    @GetMapping("/{id}")
    public BaseRest<?> selectCommentLikeById(@PathVariable Integer id){
        CommentLikeDto selectCommentLikeById = commentLikeService.selectCommentLikeById(id);
        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .timestamp(LocalDateTime.now())
                .data(selectCommentLikeById)
                .message("Find Comment like has defined by ID!!!")
                .build();
    }

    @GetMapping("/checkIfLiked")
    public BaseRest<?> checkIfLiked(
            @RequestParam(required = true, defaultValue = "0") Integer userId,
            @RequestParam(required = true, defaultValue = "0") Integer cmtId
    ){
        Boolean checkIfLiked = commentLikeService.checkIfLiked(userId, cmtId);
        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .timestamp(LocalDateTime.now())
                .data(checkIfLiked)
                .message("Successfully check if comment is liked!")
                .build();
    }

    @PostMapping
    public BaseRest<?> insertCommentLike(@Valid @RequestBody SaveCommentLikeDto saveCommentLikeDto){
        CommentLikeDto insertCommentLike = commentLikeService.insertCommentLike(saveCommentLikeDto);
        return BaseRest.builder()
                .code(HttpStatus.OK.value())
                .status(true)
                .timestamp(LocalDateTime.now())
                .data(insertCommentLike)
                .message("Insert comment like is Successful!!!!")
                .build();
    }

    @PutMapping("/{uuid}")
    public BaseRest<?> updateCommentLikeByUuid(@PathVariable String uuid, @Valid @RequestBody SaveCommentLikeDto updateCommentLikeDto){
        CommentLikeDto updateCommentLikeByUuid = commentLikeService.updateCommentLikeByUuid(uuid, updateCommentLikeDto);
        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .timestamp(LocalDateTime.now())
                .data(updateCommentLikeByUuid)
                .message("Update Comment like is Successful!!!!!")
                .build();
    }

    @DeleteMapping
    public BaseRest<?> deleteCommentLikeByUuid(
            @RequestParam(required = true, defaultValue = "0") Integer userId,
            @RequestParam(required = true, defaultValue = "0") Integer cmtId
    ){
        Integer isDeleted = commentLikeService.deleteCommentLike(userId,cmtId);
        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .data(isDeleted)
                .timestamp(LocalDateTime.now())
                .message("Delete Comment like is Successful!!!")
                .build();
    }

    @DeleteMapping("/{uuid}")
    public BaseRest<?> deleteCommentLikeByUuid(@PathVariable("uuid") String uuid){
        String isDeleted = commentLikeService.deleteCommentLikeByUuid(uuid);
        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .data(isDeleted)
                .timestamp(LocalDateTime.now())
                .message("Delete Comment like is Successful!!!")
                .build();
    }
}
