package co.istad.istademy.api.courseUser;

import co.istad.istademy.api.courseUser.web.CourseUserDto;
import co.istad.istademy.api.courseUser.web.CreateCourseUserDto;
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
public class CourseUserServiceImpl implements CourseUserService {
    private final CourseUserMapStruct courseUserMapStruct;
    private final CourseUserRepo courseUserRepo;


    @Override
    public PageInfo<CourseUserDto> selectAllCourseUsers(int page, int limit) {
        PageInfo<CourseUser> courseUserPageInfo = PageHelper.startPage(page, limit).doSelectPageInfo(courseUserRepo::buildSelectCourseUserSql);
        return courseUserMapStruct.pageInfoCourseUserToPageInfoCourseUserDto(courseUserPageInfo);
    }

    @Override
    public CourseUserDto insertCourseUser(CreateCourseUserDto createCourseUserDto) {
        CourseUser courseUser = courseUserMapStruct.createCourseUserDtoToCourseUser(createCourseUserDto);
        courseUser.setUuid(UUID.randomUUID().toString());
        courseUser.setEnrolledAt(new Timestamp(System.currentTimeMillis()));
        if (courseUserRepo.buildCreateCourseUserSql(courseUser)){
            return this.selectCourseUserByUuid(courseUser.getUuid());
        }
        throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,"Fail to create new course user!!!!");
    }

    @Override
    public CourseUserDto selectCourseUserByUuid(String uuid) {
        CourseUser courseUser = courseUserRepo.buildSelectCourseUserByUuidSql(uuid).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,String.format("undefined course user with uuid %s", uuid)));
        return courseUserMapStruct.CourseUserToCourseUserDto(courseUser);
    }

    @Override
    public String deleteCourseUserByUuid(String uuid) {
                if (courseUserRepo.buildDeleteCourseUserByUuidSql(uuid)){
                    return uuid;
                }
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,"Fail to delete course user !!!!");
            }


    @Override
    public CourseUserDto updateCourseUserByUuid(CreateCourseUserDto updateCourseUserDto, String uuid) {

                CourseUser courseUser = courseUserMapStruct.createCourseUserDtoToCourseUser(updateCourseUserDto);
                courseUser.setFinishedAt(new Timestamp(System.currentTimeMillis()));
                courseUser.setUuid(uuid);
                if (courseUserRepo.buildEditCourseUserSql(courseUser)){
                    return this.selectCourseUserByUuid(uuid);
                }
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Fail to update course user!!!!");
            }

}

