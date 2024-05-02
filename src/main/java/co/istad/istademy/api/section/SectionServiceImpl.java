package co.istad.istademy.api.section;

import co.istad.istademy.api.section.web.SaveSectionDto;
import co.istad.istademy.api.section.web.SectionDto;
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
public class SectionServiceImpl implements SectionService{
    private final SectionRepo sectionRepo;
    private final SectionMapStruct mapStruct;


    @Override
    public PageInfo<SectionDto> selectAllSection(int page, int limit, Integer courseId) {
        PageInfo<Section> sectionPageInfo = PageHelper.startPage(page,limit).doSelectPageInfo(
                () -> sectionRepo.buildSelectSectionSql(courseId)
        );
        if (courseId==0 && sectionRepo.buildSelectSectionSql(courseId).size() < 1) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    String.format("Section with course id %d is not found.", courseId)
            );
        }
        return mapStruct.pageSectionDtoPageInfo(sectionPageInfo);
    }

    @Override
    public List<Section> selectSectionLessonByCourseId(Integer id) {
        return sectionRepo.buildSelectSectionLessonByCourseIdSql(id);
    }

    @Override
    public SectionDto selectSectionById(Integer id) {
        Section section = sectionRepo.buildSelectSectionByIdSql(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Undefined section with %d id",id)));
        return mapStruct.toSectionDto(section);
    }

    @Override
    public SectionDto selectSectionByUuid(String uuid) {
        Section section = sectionRepo.buildSelectSectionByUuidSql(uuid).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,String.format("Undefined section with %s uuid", uuid)));
        return mapStruct.toSectionDto(section);
    }

    @Override
    public SectionDto insertSection(SaveSectionDto saveSectionDto) {
        Section section = mapStruct.createSectionDtoSection(saveSectionDto);
        section.setUuid(UUID.randomUUID().toString());
        section.setCreatedAt(new Timestamp(System.currentTimeMillis()));

        if (sectionRepo.buildCreateSectionSql(section)){
            return this.selectSectionById(section.getId());
        }
        else {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,"Fail to create new section!!!");
        }

    }

    @Override
    public SectionDto updateSectionByUuid(String uuid, SaveSectionDto updateSectionDto) {
        if (sectionRepo.isSectionIdExists(uuid)){
            Section section = mapStruct.createSectionDtoSection(updateSectionDto);
            section.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
            section.setUuid(uuid);
            if (sectionRepo.buildUpdateSectionByUuidSql(section)){
                return this.selectSectionByUuid(uuid);
            }
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Fail to update section!!!!");
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Undefined section with uuid %s",uuid));
    }

    @Override
    public String deleteSectionByUuid(String uuid) {
        if (sectionRepo.isSectionIdExists(uuid)){
            if (sectionRepo.buildDeleteSectionByUuidSql(uuid)){
                return uuid;
            }
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,"Fail to delete section!!!!");
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND,String.format("Undefined section with uuid %s", uuid));
    }
}
