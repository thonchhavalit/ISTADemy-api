package co.istad.istademy.api.commentLike;

import co.istad.istademy.api.commentLike.web.CommentLikeDto;
import co.istad.istademy.api.commentLike.web.SaveCommentLikeDto;
import co.istad.istademy.api.section.Section;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.sql.Timestamp;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CommentLikeServiceImpl implements CommentLikeService{
    private final CommentLikeMapStruct commentLikeMapStruct;
    private final CommentLikeRepo commentLikeRepo;

    @Override
    public PageInfo<CommentLikeDto> selectAllCommentLike(int page, int limit, Integer cmtId) {
        PageInfo<CommentLike> commentLikePageInfo = PageHelper.startPage(page,limit).doSelectPageInfo(
                () -> commentLikeRepo.buildSelectCommentLikeSql(cmtId)
        );
        if (cmtId==0 && commentLikeRepo.buildSelectCommentLikeSql(cmtId).size() < 1) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    String.format("Comment like with comment id %d is not found.", cmtId)
            );
        }
        return commentLikeMapStruct.pageInfoCommentLikeToPageInfoCommentLikeDto(commentLikePageInfo);
    }

    @Override
    public CommentLikeDto insertCommentLike(SaveCommentLikeDto saveCommentLikeDto) {
        CommentLike commentLike = commentLikeMapStruct.saveCommentLikeDtoToCommentLike(saveCommentLikeDto);
        commentLike.setUuid(UUID.randomUUID().toString());
        commentLike.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        if (commentLikeRepo.buildInsertCommentLikeSql(commentLike)){
            return selectCommentLikeById(commentLike.getId());
        }
        else
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Fail to create comment like!!!!");
    }

    @Override
    public CommentLikeDto selectCommentLikeByUuid(String uuid) {
        CommentLike commentLike = commentLikeRepo.buildSelectCommentLikeByUuidSql(uuid).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,String.format("Undefined comment like with uuid %s",uuid)));

        return commentLikeMapStruct.CommentLikeToCommentLikeDto(commentLike);
    }

    @Override
    public CommentLikeDto selectCommentLikeById(Integer id) {
        CommentLike commentLike = commentLikeRepo.buildSelectCommentLikeByIdSql(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,String.format("Undefined comment like with id %d", id)));
        return commentLikeMapStruct.CommentLikeToCommentLikeDto(commentLike);
    }

    @Override
    public Integer deleteCommentLike(Integer userId, Integer cmtId) {
        if (commentLikeRepo.buildDeleteCommentLikeSql(userId, cmtId)){
            return cmtId;
        }
        throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,"Fail to delete comment!!!");
    }

    @Override
    public String deleteCommentLikeByUuid(String uuid) {
            if (commentLikeRepo.buildDeleteCommentLikeByUuidSql(uuid)){
                return uuid;
            }
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,"Fail to delete comment!!!");
        }

    @Override
    public Boolean checkIfLiked(Integer userId, Integer cmtId) {
        return commentLikeRepo.buildCheckIfLikedByUserIdAndCmtId(userId, cmtId);
    }

    @Override
    public CommentLikeDto updateCommentLikeByUuid(String uuid, SaveCommentLikeDto updateCommentLikeDto) {
            CommentLike commentLike = commentLikeMapStruct.saveCommentLikeDtoToCommentLike(updateCommentLikeDto);
            commentLike.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
            commentLike.setUuid(uuid);
            if (commentLikeRepo.buildUpdateCommentLikeByUuidSql(commentLike)){
                return this.selectCommentLikeByUuid(uuid);
            }
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,"Fail to update comment like !!!");
        }

}
