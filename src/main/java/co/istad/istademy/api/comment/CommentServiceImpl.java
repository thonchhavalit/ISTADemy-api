package co.istad.istademy.api.comment;

import co.istad.istademy.api.comment.web.CommentDto;
import co.istad.istademy.api.comment.web.SaveCommentDto;
import co.istad.istademy.api.contentProgress.ContentProgress;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService{
    private final CommentRepo commentRepo;
    private final CommentMapStruct mapStruct;

    @Override
    public PageInfo<CommentDto> selectAllComments(int page, int limit, Integer lessonId) {
        PageInfo<CommentDto> commentDtoPageInfo = PageHelper.startPage(page, limit).doSelectPageInfo(
                () -> commentRepo.buildSelectCommentSql(lessonId)
        );
        if (lessonId ==  0 && commentRepo.buildSelectCommentSql(lessonId).size() < 1) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    String.format("Lesson or User like with id %d is not found.", lessonId)
            );
        }
        return commentDtoPageInfo;
    }

    @Override
    public CommentDto insertComment(SaveCommentDto saveCommentDto) {
        Comment comment = mapStruct.createCommentDtoToComment(saveCommentDto);
        comment.setUuid(UUID.randomUUID().toString());
        comment.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        if (commentRepo.buildInsertCommentSql(comment)){
            return this.selectCommentById(comment.getId());
        }
        else {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,"Fail to create new comment!!!");
        }
    }

    @Override
    public CommentDto selectCommentByUuid(String uuid) {
        Comment comment = commentRepo.buildSelectCommentByUuid(uuid).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Undefined comment with uuid %s",uuid)));
        return mapStruct.CommentToCommentDto(comment);
    }

    @Override
    public CommentDto selectCommentById(Integer id) {
        Comment comment = commentRepo.buildSelectCommentById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,String.format("Undefined comment with id %d", id)));
        return mapStruct.CommentToCommentDto(comment);
    }

    @Override
    public String deleteCommentByUuid(String uuid) {
            if (commentRepo.isCommentUuidExists(uuid)){
                if (commentRepo.buildDeleteCommentByUuidSql(uuid)){
                    return uuid;
                }
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,"Fail to delete comment!!!");
            }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Undefined comment with uuid %s", uuid));
        }

    @Override
    public CommentDto updateCommentByUuid(String uuid, SaveCommentDto updateCommentDto) {
        if (commentRepo.isCommentUuidExists(uuid)){
            Comment comment = mapStruct.createCommentDtoToComment(updateCommentDto);
            comment.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
            comment.setUuid(uuid);
            if (commentRepo.buildUpdateCommentByUuidSql(comment)){
                return this.selectCommentByUuid(uuid);
            }
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Fail to update comment!!!!");
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                String.format("Undefined comment with uuid %s", uuid));
    }

    @Override
    public List<Comment> selectCommentByUserId(Integer id) {
        return commentRepo.buildSelectCommentByUserIdSql(id);
    }

    @Override
    public List<Comment> selectCommentByLessonId(Integer id) {
        return commentRepo.buildSelectCommentByLessonIdSql(id);
    }
}
