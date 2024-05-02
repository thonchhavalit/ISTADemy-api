package co.istad.istademy.api.example;

import co.istad.istademy.api.example.web.ExampleDto;
import co.istad.istademy.api.example.web.SaveExampleDto;
import com.github.pagehelper.PageInfo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ExampleMapStruct {
    ExampleDto exampleToExampleDto(Example model);
    @Mapping(source = "lessonId", target = "lessonId.id")
    Example createExampleDtoToExample(SaveExampleDto model);
    PageInfo<ExampleDto> pageInfoExampleToPageInfoExampleDto(PageInfo<Example> model);
}
