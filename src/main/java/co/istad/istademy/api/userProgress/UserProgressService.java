package co.istad.istademy.api.userProgress;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserProgressService {
    List<UserProgress> getUserProgress(Integer userId);

    List<UserProgress> getCompletedCourses(Integer userId);
}
