package co.istad.istademy.api.example;

import co.istad.istademy.api.example.web.ExampleDto;
import co.istad.istademy.api.example.web.SaveExampleDto;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface ExampleService {
    /**
     * use to retrieve all examples from database and response with pagination
     *
     * @param page  : location page
     * @param limit : size of page
     * @return PageInfo of Tutorial is pagination
     */

    PageInfo<ExampleDto> selectAllExamples(int page, int limit);

    /**
     * insert example
     *
     * @param saveExampleDto : is data need to insert and require
     * @return ExampleDto define response data
     */
    ExampleDto insertExample(SaveExampleDto saveExampleDto);

    /**
     * select example by id
     *
     * @param id : needed id in order to do the searching
     * @return ExampleDto define response data
     */
    ExampleDto selectExampleById(Integer id);

    /**
     * select example by uuid
     *
     * @param uuid : needed id in order to do the searching
     * @return ExampleDto define response data
     */
    ExampleDto selectExampleByUuid(String uuid);

    /**
     * use to delete example by uuid, and it just updates status from false to true
     *
     * @param uuid is required to search before delete
     * @return uuid they deleted
     */
    String deleteExampleByUuid(String uuid);

    /**
     * update example
     *
     * @param updateExampleDto : data need to update
     * @return :ExampleDto use to response that necessary to response
     */
    ExampleDto updateExampleByUuid(String uuid, SaveExampleDto updateExampleDto);

    List<Example> selectExampleByLessonId(Integer id);
}
