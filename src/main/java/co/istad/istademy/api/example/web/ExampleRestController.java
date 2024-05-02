package co.istad.istademy.api.example.web;

import co.istad.istademy.api.example.Example;
import co.istad.istademy.api.example.ExampleService;
import co.istad.istademy.base.BaseRest;
import com.github.pagehelper.PageInfo;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/examples")
public class ExampleRestController {
    private final ExampleService exampleService;

    @GetMapping
    public BaseRest<?> selectAllExamples(
            @RequestParam(required = false,defaultValue = "1",name = "page")int page,
            @RequestParam(required = false, defaultValue = "20", name = "limit")int limit
    ){
        PageInfo<ExampleDto> selectAll = exampleService.selectAllExamples(page, limit);
        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .timestamp(LocalDateTime.now())
                .message("Successfully retrieved all examples")
                .data(selectAll)
                .build();
    }

    @GetMapping("/{id}")
    public BaseRest<?> selectExampleById(@PathVariable Integer id){
        ExampleDto example = exampleService.selectExampleById(id);
        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .timestamp(LocalDateTime.now())
                .data(example)
                .message("Example has defined by ID!!!")
                .build();
    }

    @GetMapping("/by/{uuid}")
    public BaseRest<?> selectExampleByUuid(@PathVariable String uuid){
        ExampleDto example = exampleService.selectExampleByUuid(uuid);
        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .timestamp(LocalDateTime.now())
                .data(example)
                .message("Example has defined by UUID!!!")
                .build();
    }

    @PostMapping
    public BaseRest<?> insertExample(@Valid @RequestBody SaveExampleDto saveExampleDto){
        ExampleDto exampleDto = exampleService.insertExample(saveExampleDto);
        return BaseRest.builder()
                .code(HttpStatus.OK.value())
                .status(true)
                .timestamp(LocalDateTime.now())
                .data(exampleDto)
                .message("Create Example is Successful!!!!")
                .build();
    }

    @PutMapping("/{uuid}")
    public BaseRest<?> updateExampleByUuid(@PathVariable String uuid, @Valid @RequestBody SaveExampleDto updateExampleDto){
        ExampleDto exampleDto = exampleService.updateExampleByUuid(uuid, updateExampleDto);
        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .timestamp(LocalDateTime.now())
                .data(exampleDto)
                .message("Update Example is Successful!!!!!")
                .build();
    }

    @DeleteMapping("/{uuid}")
    public BaseRest<?> deleteExampleByUuid(@PathVariable("uuid") String uuid){
        String isDeleted = exampleService.deleteExampleByUuid(uuid);
        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .data(isDeleted)
                .timestamp(LocalDateTime.now())
                .message("Delete Example is Successful!!!")
                .build();
    }

    @GetMapping("/lesson/{id}")
    public BaseRest<?> selectExampleByLessonId(@PathVariable Integer id){
        List<Example> selectByLessonId = exampleService.selectExampleByLessonId(id);
        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .data(selectByLessonId)
                .timestamp(LocalDateTime.now())
                .message("Successfully select example by lesson id!!!!")
                .build();
    }
}
