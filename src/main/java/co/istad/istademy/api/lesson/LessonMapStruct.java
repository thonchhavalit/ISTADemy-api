package co.istad.istademy.api.lesson;


import co.istad.istademy.api.lesson.web.LessonDto;
import co.istad.istademy.api.lesson.web.SaveLessonDto;
import com.github.pagehelper.PageInfo;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface LessonMapStruct {
    Lesson createLessonDtoToLesson(SaveLessonDto model);
    LessonDto toLessonDto (Lesson model);

    PageInfo<LessonDto> pageLessonDtoPageInfo(PageInfo<Lesson> model);
}
