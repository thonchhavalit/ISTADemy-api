package co.istad.istademy.api.lesson;


import co.istad.istademy.api.lesson.web.LessonDto;
import co.istad.istademy.api.lesson.web.SaveLessonDto;
import co.istad.istademy.api.section.Section;
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
public class LessonServiceImpl implements LessonService{
    private final LessonRepo lessonRepo;
    private final LessonMapStruct mapStruct;

    @Override
    public PageInfo<LessonDto> selectAllLessons(int page, int limit, Integer sectionId) {
        PageInfo<Lesson> lessonPageInfo = PageHelper.startPage(page,limit).doSelectPageInfo(
                () -> lessonRepo.buildSelectAllLessonSql(sectionId)
        );
        if (sectionId==0 && lessonRepo.buildSelectAllLessonSql(sectionId).size() < 1) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    String.format("Lesson with section id %d is not found.", sectionId)
            );
        }
        return mapStruct.pageLessonDtoPageInfo(lessonPageInfo);
    }

    @Override
    public LessonDto insertLesson(SaveLessonDto saveLessonDto) {
        Lesson lesson = mapStruct.createLessonDtoToLesson(saveLessonDto);
        lesson.setUuid(UUID.randomUUID().toString());
        lesson.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        if (lessonRepo.buildCreateLessonSql(lesson)){
            return this.selectLessonById(lesson.getId());
        }
        else {
            throw  new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Fail to create new lesson!!!");
        }
    }

    @Override
    public LessonDto selectLessonById(Integer id) {
        Lesson lesson = lessonRepo.buildSelectLessonByIdSql(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Undefined lesson with %d id",id)));
        return mapStruct.toLessonDto(lesson);
    }

    @Override
    public LessonDto selectLessonByUuid(String uuid) {
        Lesson lesson = lessonRepo.buildSelectLessonByUuidSql(uuid).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,String.format("Undefined lesson with %s uuid", uuid)));
        return mapStruct.toLessonDto(lesson);
    }

    @Override
    public LessonDto updateLessonByUuid(String uuid, SaveLessonDto updateLessonDto) {
        if (lessonRepo.isLessonIdExists(uuid)) {
            Lesson lesson = mapStruct.createLessonDtoToLesson(updateLessonDto);
            lesson.setUpdatedAt(new Timestamp((System.currentTimeMillis())));
            lesson.setUuid(uuid);
            if (lessonRepo.buildUpdateLessonByUuidSql(lesson)) {
                return this.selectLessonByUuid(uuid);
            }
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Fail to update lesson!!!");
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                String.format("Undefined lesson with uuid %s", uuid));
    }

    @Override
    public String deleteLessonByUuid(String uuid) {
        if (lessonRepo.isLessonIdExists(uuid)){
            if (lessonRepo.buildDeleteLessonByUuidSql(uuid)){
                return uuid;
            }
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Fail to delete lesson!!!");
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Undefined lesson with uuid %s", uuid));
    }

    @Override
    public List<Lesson> selectLessonBySectionId(Integer id) {
        return lessonRepo.buildSelectLessonBySectionIdSql(id);
    }
}
