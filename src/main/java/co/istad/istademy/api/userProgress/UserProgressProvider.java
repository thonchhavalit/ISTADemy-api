package co.istad.istademy.api.userProgress;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.builder.annotation.ProviderMethodResolver;
import org.apache.ibatis.jdbc.SQL;

public class UserProgressProvider implements ProviderMethodResolver {
    private static final String courseTable = "courses";
    private static final String contentProgress = "content_progresses";

    public String buildSelectUserProgressSql(@Param("userId") Integer userId) {
        return new SQL() {{
            SELECT("courses.id AS course_id, courses.title AS course_name, courses.image as course_image");
            SELECT("COUNT(DISTINCT cp.id) AS content_completed");
            SELECT("COUNT(DISTINCT qp.id) AS quizzes_completed");
            SELECT("SUM(CASE WHEN EXISTS (SELECT 1 FROM content_progresses cp WHERE cp.content_id = contents.id AND cp.is_read = true AND cp.user_id = #{userId}) THEN 1 ELSE 0 END) AS lesson_completed");
            SELECT("COUNT(DISTINCT contents.id) AS total_content");
            SELECT("COUNT(DISTINCT l.id) AS total_lesson");
            SELECT("COUNT(DISTINCT quizzes.id) AS total_quiz");
            SELECT("courses.image AS courseImage");
            SELECT("100.0 * COUNT(DISTINCT cp.id) / NULLIF(COUNT(DISTINCT contents.id), 0) AS lessonReadPercentage");
            SELECT("100.0 * COUNT(DISTINCT qp.id) / NULLIF(COUNT(DISTINCT quizzes.id), 0) AS quizTakenPercentage");
            SELECT("ROUND(100.0 * (COUNT(DISTINCT cp.id) + COUNT(DISTINCT qp.id)) / NULLIF((COUNT(DISTINCT l.id) + COUNT(DISTINCT quizzes.id)), 0)) AS course_completion_percentage");
            FROM(courseTable);
            LEFT_OUTER_JOIN("content_progresses cp ON courses.id = cp.course_id AND cp.is_read = true AND cp.user_id = #{userId}");
            LEFT_OUTER_JOIN("quiz_progresses qp ON courses.id = qp.course_id AND qp.is_taken = true AND qp.user_id = #{userId}");
            LEFT_OUTER_JOIN("sections s ON courses.id = s.course_id");
            LEFT_OUTER_JOIN("lessons l ON s.id = l.section_id");
            LEFT_OUTER_JOIN("contents ON l.id = contents.lesson_id");
            LEFT_OUTER_JOIN("quizzes ON l.id = quizzes.lesson_id");
            GROUP_BY("courses.id, courses.title, courses.image");
            HAVING("COUNT(DISTINCT cp.id) > 0");
        }}.toString();
    }

    public String buildSelectCompletedCoursesSql(@Param("userId") Integer userId) {
        return new SQL() {{
            SELECT("courses.id AS course_id, courses.title AS course_name, courses.image as course_image");
            SELECT("COUNT(DISTINCT cp.id) AS content_completed");
            SELECT("COUNT(DISTINCT qp.id) AS quizzes_completed");
            SELECT("COUNT(DISTINCT contents.id) AS total_content");
            SELECT("COUNT(DISTINCT quizzes.id) AS total_quiz");
            SELECT("100.0 * (COUNT(DISTINCT cp.id) + COALESCE(COUNT(DISTINCT qp.id), 0)) / NULLIF((COUNT(DISTINCT contents.id) + COALESCE(COUNT(DISTINCT quizzes.id), 0)), 0) AS courseCompletionPercentage");
            FROM(courseTable);
            LEFT_OUTER_JOIN("content_progresses cp ON courses.id = cp.course_id AND cp.is_read = true AND cp.user_id = #{userId}");
            LEFT_OUTER_JOIN("quiz_progresses qp ON courses.id = qp.course_id AND qp.is_taken = true AND qp.user_id = #{userId}");
            LEFT_OUTER_JOIN("sections s ON courses.id = s.course_id");
            LEFT_OUTER_JOIN("lessons l ON s.id = l.section_id");
            LEFT_OUTER_JOIN("contents ON l.id = contents.lesson_id");
            LEFT_OUTER_JOIN("quizzes ON l.id = quizzes.lesson_id");
            GROUP_BY("courses.id, courses.title, courses.image");
            HAVING("100.0 * (COUNT(DISTINCT cp.id) + COALESCE(COUNT(DISTINCT qp.id), 0)) / NULLIF((COUNT(DISTINCT contents.id) + COALESCE(COUNT(DISTINCT quizzes.id), 0)), 0) = 100");
        }}.toString();
    }
}
