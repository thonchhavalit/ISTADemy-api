package co.istad.istademy.api.contentProgress;

import co.istad.istademy.api.content.Content;
import co.istad.istademy.api.content.ContentMapStruct;
import co.istad.istademy.api.contentProgress.web.SaveContentProgressDto;
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
public class ContentProgressServiceImpl implements  ContentProgressService {
    private final ContentProgressMapper contentProgressMapper;
    private final ContentProgressMapStruct mapStruct;

    @Override
    public PageInfo<ContentProgress> selectContentProgresses(int page, int limit, Integer userId, Integer contentId) {
        PageInfo<ContentProgress> contentProgressPageInfo = PageHelper.startPage(page, limit).doSelectPageInfo(
                () -> contentProgressMapper.buildSelectContentProgressesSql(userId, contentId)
        );
        if (userId == 0 || contentId == 0 && contentProgressMapper.buildSelectContentProgressesSql(userId, contentId).size() < 1) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    String.format("Content or User like with id %d , %d is not found.", contentId, userId)
            );
        }
        return contentProgressPageInfo;
    }

    @Override
    public ContentProgress insertSetIsContentRead(SaveContentProgressDto saveContentProgressDto) {
        ContentProgress contentProgress = mapStruct.createContentProgressDtoToContentProgress(saveContentProgressDto);
        contentProgress.setUuid(UUID.randomUUID().toString());
        contentProgress.setIsRead(true);
        contentProgress.setCompletedAt(new Timestamp(System.currentTimeMillis()));
        if (contentProgressMapper.buildSetIsContentReadSql(contentProgress)) {
            return contentProgress;
        } else {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Fail to Create Content Progress!!!");
        }
    }

    @Override
    public Boolean selectIsContentProgressExist(Integer userId, Integer contentId) {

            return contentProgressMapper.isContentProgressExists(userId, contentId);

    }
}
