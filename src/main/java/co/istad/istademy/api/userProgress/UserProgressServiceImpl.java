package co.istad.istademy.api.userProgress;

import co.istad.istademy.api.contentProgress.ContentProgress;
import co.istad.istademy.api.contentProgress.ContentProgressMapper;
import co.istad.istademy.api.quizProgresses.QuizProgress;
import co.istad.istademy.api.quizProgresses.QuizProgressMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserProgressServiceImpl implements UserProgressService {

    private final UserProgressMapper userProgressMapper;
    @Override
    public List<UserProgress> getUserProgress(Integer userId) {
        return userProgressMapper.buildSelectUserProgressSql(userId);
    }

    @Override
    public List<UserProgress> getCompletedCourses(Integer userId) {
        return userProgressMapper.buildSelectCompletedCoursesSql(userId);
    }
}
