package co.istad.istademy.api.level.web;

import co.istad.istademy.api.level.LevelService;
import co.istad.istademy.base.BaseRest;
import com.github.pagehelper.PageInfo;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/level")
public class LevelRestController {
    private final LevelService service;
    @GetMapping
    public BaseRest<?> selectAllLevel(
            @RequestParam(required = false, defaultValue = "1", name = "page")int page,
            @RequestParam(required = false,defaultValue = "20" , name = "limit")int limit
    ){
        PageInfo<LevelDto> selectAll = service.selectAllLevel(page, limit);
        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .timestamp(LocalDateTime.now())
                .data(selectAll)
                .message("Successfully Select All Levels!!!!")
                .build();
    }

    @GetMapping("/{id}")
    public BaseRest<?> selectLevelById(@PathVariable Integer id){
        LevelDto selectId = service.selectLevelById(id);
        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .timestamp(LocalDateTime.now())
                .data(selectId)
                .message("Successfully Select Level by ID!!!")
                .build();
    }

    @GetMapping("/by/{uuid}")
    public BaseRest<?> selectLevelByUuid(@PathVariable String uuid){
        LevelDto selectUuid = service.selectLevelByUuid(uuid);
        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .timestamp(LocalDateTime.now())
                .data(selectUuid)
                .message("Successfully Select Level by UUID!!!")
                .build();
    }

    @PostMapping
    public BaseRest<?> insertLevel(@RequestBody @Valid AddLevelDto addLevelDto){
        LevelDto inserted = service.insertLevel(addLevelDto);
        return BaseRest.builder()
                .code(HttpStatus.OK.value())
                .status(true)
                .timestamp(LocalDateTime.now())
                .data(inserted)
                .message("Create Level is Successful!!!!")
                .build();
    }

    @PutMapping("/{uuid}")
    public BaseRest<?> updateLevelByUuid(@PathVariable String uuid, @Valid @RequestBody AddLevelDto updateLevelDto){
        LevelDto updated = service.updateLevelByUuid(uuid,updateLevelDto);
        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .timestamp(LocalDateTime.now())
                .data(updated)
                .message("Update level is Successful!!!!!")
                .build();
    }

    @DeleteMapping("/{uuid}")
    public BaseRest<?> deleteLevelByUuid(@PathVariable String uuid){
        String deleted = service.deleteLevelByUuid(uuid);
        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .data(deleted)
                .timestamp(LocalDateTime.now())
                .message("Delete level is Successful!!!")
                .build();
    }
}
