package model;

import db.DBFunctions;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class QuizDAO {

    String DATABASE_NAME = "cleverdb";
    String USER_NAME = "postgres";
    String PASSWORD = "12345";

    public int addQuiz(Quiz quiz) {
        String INSERT_QUIZ_SQL = "INSERT INTO quizzes (title, description, quiz_type) VALUES (?, ?, ?)";

        int quizId = 0;

        DBFunctions db = new DBFunctions();

        try (Connection conn = db.connect_to_db(DATABASE_NAME, USER_NAME, PASSWORD)) {

            PreparedStatement ps = conn.prepareStatement(INSERT_QUIZ_SQL, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setString(1, quiz.getQuizName());
            ps.setString(2, quiz.getQuizDescription());
            ps.setString(3, quiz.getQuizType());
            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    quizId = rs.getInt(1);
                    System.out.println("Created quiz with ID: " + quizId);
                }
            }

            ps.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return quizId;
    }
}