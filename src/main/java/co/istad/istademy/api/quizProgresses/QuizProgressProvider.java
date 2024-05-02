package co.istad.istademy.api.quizProgresses;


import org.apache.ibatis.builder.annotation.ProviderMethodResolver;
import org.apache.ibatis.jdbc.SQL;

public class QuizProgressProvider implements ProviderMethodResolver {

    private static final String table = "quiz_progresses";

    public String buildSelectQuizProgressesSql(Integer userId, Integer quizId){
        return new SQL(){{
            SELECT("*");
            FROM(table);
            if(userId !=0 && quizId!=0) {
                WHERE("user_id=#{userId}", "quiz_id=#{quizId}");
            }
        }}.toString();
    }

  public String buildSetIsQuizTakenSql() {
        return new SQL(){{
            INSERT_INTO(table);
            VALUES("uuid","#{qp.uuid}");
            VALUES("user_id","#{qp.userId}");
            VALUES("quiz_id","#{qp.quizId}");
            VALUES("course_id","#{qp.courseId}");
            VALUES("is_taken","#{qp.isTaken}");
        }}.toString();
  }

}
