package co.istad.istademy.api.section.web;

import co.istad.istademy.api.section.SectionService;
import co.istad.istademy.base.BaseRest;
import com.github.pagehelper.PageInfo;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/v1/sections")
@RequiredArgsConstructor
public class SectionRestController {
    private final SectionService service;

    @GetMapping
//    @PreAuthorize("hasAnyAuthority('SCOPE_section:read')")
    public BaseRest<?> getAllSection(
            @RequestParam(required = false, defaultValue = "1", name = "page")int page,
            @RequestParam(required = false,defaultValue = "20" , name = "limit")int limit,
            @RequestParam(defaultValue = "0", required = false) Integer courseId
    ){
        PageInfo<SectionDto> selectAll = service.selectAllSection(page, limit, courseId);
        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .timestamp(LocalDateTime.now())
                .data(selectAll)
                .message("Successfully Select All Sections!!!!")
                .build();
    }
    @GetMapping("/{id}")
//    @PreAuthorize("hasAuthority('SCOPE_section:read')")
    public BaseRest<?> getSectionById(@PathVariable Integer id){
        var section = service.selectSectionById(id);
        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .timestamp(LocalDateTime.now())
                .data(section)
                .message("Successfully Select All section lesson by ID!!!")
                .build();
    }

    @GetMapping("/by/{uuid}")
//    @PreAuthorize("hasAuthority('SCOPE_section:read')")
    public BaseRest<?> getSectionByUuid(@PathVariable String uuid){
        var section = service.selectSectionByUuid(uuid);
        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .timestamp(LocalDateTime.now())
                .data(section)
                .message("Successfully Select All by UUID!!!")
                .build();
    }


    @PostMapping
//    @PreAuthorize("hasAuthority('SCOPE_section:write')")
    public BaseRest<?> createSection(@RequestBody @Valid SaveSectionDto sectionDto){
        SectionDto section = service.insertSection(sectionDto);
        return BaseRest.builder()
                .code(HttpStatus.OK.value())
                .status(true)
                .timestamp(LocalDateTime.now())
                .data(section)
                .message("Create Section Success!!!!")
                .build();
    }

    @PutMapping("/{uuid}")
//    @PreAuthorize("hasAuthority('SCOPE_section:update')")
    public BaseRest<?> updateSectionByUuid(@PathVariable String uuid, @Valid @RequestBody SaveSectionDto sectionDto){
        SectionDto section = service.updateSectionByUuid(uuid,sectionDto);
        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .timestamp(LocalDateTime.now())
                .data(section)
                .message("Update Section is Successful!!!!!")
                .build();
    }

    @DeleteMapping("/{uuid}")
//    @PreAuthorize("hasAuthority('SCOPE_section:delete')")
    public BaseRest<?> deleteSectionByUuid(@PathVariable String uuid){
        String section = service.deleteSectionByUuid(uuid);
        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .data(section)
                .timestamp(LocalDateTime.now())
                .message("Delete Section is Successful!!!")
                .build();
    }
}
