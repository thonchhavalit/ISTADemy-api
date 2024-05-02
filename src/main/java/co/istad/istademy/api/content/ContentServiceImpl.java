package co.istad.istademy.api.content;

import co.istad.istademy.api.content.web.ContentDto;
import co.istad.istademy.api.content.web.SaveContentDto;
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
public class ContentServiceImpl implements ContentService{
    private final ContentRepo contentRepo;
    private final ContentMapStruct mapStruct;

    @Override
    public PageInfo<ContentDto> selectAllContents(int page, int limit) {
        PageInfo<Content> contentPageInfo = PageHelper.startPage(page,limit).doSelectPageInfo(contentRepo::buildSelectContentSql);
        return mapStruct.pageInfoContentToPageInfoContentDto(contentPageInfo);
    }

    @Override
    public ContentDto insertContent(SaveContentDto saveContentDto) {
        Content content = mapStruct.createContentDtoToContent(saveContentDto);
        content.setUuid("content"+UUID.randomUUID().toString());
        content.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        if (contentRepo.buildInsertContentSql(content)){
            return this.selectContentById(content.getId());
        }
        else {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Fail to Create Content!!!");
        }
    }

    @Override
    public ContentDto selectContentByUuid(String uuid) {
        Content content = contentRepo.buildSelectContentByUuidSql(uuid).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Undefined content with uuid %s", uuid)));
        return mapStruct.ContentToContentDto(content);
    }

    @Override
    public ContentDto selectContentById(Integer id) {
        Content content = contentRepo.buildSelectContentByIdSql(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NO_CONTENT,String.format("Undefined content with id %d", id)));
        return mapStruct.ContentToContentDto(content);
    }

    @Override
    public String deleteContentByUuid(String uuid) {
        if (contentRepo.isContentUuidExists(uuid)){
            if (contentRepo.buildDeleteContentByUuidSql(uuid)){
                return uuid;
            }
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,"Fail t delete content!!!!");
        }
         throw new ResponseStatusException(HttpStatus.NOT_FOUND,String.format("Undefined content with uuid %s", uuid));
        }

    @Override
    public ContentDto updateContentByUuid(String uuid, SaveContentDto updateContentDto) {
        if (contentRepo.isContentUuidExists(uuid)){
            Content content =mapStruct.createContentDtoToContent(updateContentDto);
            content.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
            content.setUuid(uuid);
            if (contentRepo.buildUpdateContentByUuidSql(content)){
                return this.selectContentByUuid(uuid);
            }
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Fail to update contents!!!");
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND,String.format("Undefined content with uuid %s", uuid));
        }

    @Override
    public List<Content> selectContentByLessonId(Integer id) {
        return contentRepo.buildSelectContentByLessonIdSql(id);
    }

}