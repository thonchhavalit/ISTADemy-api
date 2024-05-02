package co.istad.istademy.api.feedback;

import co.istad.istademy.api.feedback.web.CreateFeedBackDto;
import co.istad.istademy.api.feedback.web.FeedBackDto;
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
public class FeedBackServiceImpl implements FeedBackService{
    private final FeedBackRepo feedBackRepo;
    private final FeedBackMapStruct mapStruct;
    @Override
    public PageInfo<FeedBackDto> selectAllFeedBacks(int page, int limit) {
        PageInfo<FeedBack> feedBackPageInfo = PageHelper.startPage(page,limit).doSelectPageInfo(feedBackRepo::buildSelectFeedBackSql);
        return mapStruct.pageInfoFeedBackToPageInfoFeedBackDto(feedBackPageInfo);
    }

    @Override
    public FeedBackDto insertFeedbacks(CreateFeedBackDto createFeedBackDto) {
        FeedBack feedBack = mapStruct.createFeedBackDtoFeedback(createFeedBackDto);
        feedBack.setUuid(UUID.randomUUID().toString());
        feedBack.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        if (feedBackRepo.buildInsertFeedBackSql(feedBack)){
            return this.selectFeedBackById(feedBack.getId());
        }
        throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Fail to create new feedback!!!");
    }

    @Override
    public FeedBackDto selectFeedBackById(Integer id) {
        FeedBack feedBack = feedBackRepo.buildSelectFeedBackByIdSql(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Undefined feedback with id %d",id)));
        return mapStruct.FeedBackToFeedBackDto(feedBack);
    }

    @Override
    public FeedBackDto selectFeedBackByUuid(String uuid) {
        FeedBack feedBack =  feedBackRepo.buildSelectFeedBackByUuidSql(uuid).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,String.format("Undefined feedback with uuid %s",uuid)));
        return mapStruct.FeedBackToFeedBackDto(feedBack);
    }

    @Override
    public String deleteFeedBackByUuid(String uuid) {
            if (feedBackRepo.buildDeleteFeedBackByUuidSql(uuid)){
                return uuid;
            }
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Fail to delete FeedBack!!!");
        }


    @Override
    public FeedBackDto updateFeedBackByUuid(String uuid, CreateFeedBackDto updateFeedBackDto) {
            FeedBack feedBack = mapStruct.createFeedBackDtoFeedback(updateFeedBackDto);
            feedBack.setUuid(uuid);
            if (feedBackRepo.buildUpdateFeedBackByUuidSql(feedBack)){
                return this.selectFeedBackByUuid(uuid);
            }
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Fail to update FeedBack!!!");
    }
}
