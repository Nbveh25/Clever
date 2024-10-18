package model;

import db.DBFunctions;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class QuizDAO {

    String DATABASE_NAME = "cleverdb";
    String USER_NAME = "postgres";
    String PASSWORD = "12345";

    public int addQuiz(Quiz quiz) {
        String INSERT_QUIZ_SQL = "INSERT INTO quizzes (quiz_title, quiz_description, quiz_type, quiz_icon_path) VALUES (?, ?, ?, ?)";

        int quizId = 0;

        DBFunctions db = new DBFunctions();

        try (Connection conn = db.connect_to_db(DATABASE_NAME, USER_NAME, PASSWORD)) {
            try(PreparedStatement ps = conn.prepareStatement(INSERT_QUIZ_SQL, PreparedStatement.RETURN_GENERATED_KEYS)) {
                ps.setString(1, quiz.getQuizName());
                ps.setString(2, quiz.getQuizDescription());
                ps.setString(3, quiz.getQuizType());
                ps.setString(4, quiz.getQuizIconPath());
                ps.executeUpdate();

                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) {
                        quizId = rs.getInt(1);
                        System.out.println("Created quiz with ID: " + quizId);
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return quizId;
    }

    public List<Quiz> getAllQuiz() {
        List<Quiz> quizList = new ArrayList<>();
        String SELECT_QUIZ_SQL = "SELECT quiz_title, quiz_description, quiz_type, quiz_icon_path FROM quizzes";
        DBFunctions db = new DBFunctions();

        try(Connection connection = db.connect_to_db(DATABASE_NAME, USER_NAME, PASSWORD)) {
            try (PreparedStatement ps = connection.prepareStatement(SELECT_QUIZ_SQL)) {
                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        String quizName = rs.getString(1);
                        String quizDescription = rs.getString(2);
                        String quizType = rs.getString(3);
                        String quizIconPath = rs.getString(4);

                        Quiz quiz = new Quiz(quizName, quizDescription, quizType, quizIconPath);
                        quizList.add(quiz);
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return quizList;
    }
}