package dao;

import services.DataBaseService;
import dto.Quiz;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class QuizDAO {

    private final Connection connection = DataBaseService.getConnection();

    public int addQuiz(Quiz quiz) {
        String INSERT_QUIZ_SQL = "INSERT INTO quizzes (quiz_title, quiz_description, quiz_type, quiz_icon_path) VALUES (?, ?, ?, ?)";

        int quiz_id = -1;

        try(PreparedStatement ps = connection.prepareStatement(INSERT_QUIZ_SQL, PreparedStatement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, quiz.getQuizName());
            ps.setString(2, quiz.getQuizDescription());
            ps.setString(3, quiz.getQuizType());
            ps.setString(4, quiz.getQuizIconPath());

            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    quiz_id = rs.getInt(1);
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return quiz_id;
    }

    public List<Quiz> getAllQuiz() {
        List<Quiz> quizList = new ArrayList<>();
        String SELECT_QUIZ_SQL = "SELECT id, quiz_title, quiz_description, quiz_type, quiz_icon_path FROM quizzes";

        try (PreparedStatement ps = connection.prepareStatement(SELECT_QUIZ_SQL)) {
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    int id = rs.getInt(1);
                    String quizName = rs.getString(2);
                    String quizDescription = rs.getString(3);
                    String quizType = rs.getString(4);
                    String quizIconPath = rs.getString(5);

                    Quiz quiz = new Quiz(id, quizName, quizDescription, quizType, quizIconPath);
                    quizList.add(quiz);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return quizList;
    }

    public int getIdByQuizName(String quizName) {
        String SELECT_QUIZ_SQL = "SELECT id FROM quizzes WHERE quiz_title = ?";
        int id = -1;

        try (PreparedStatement ps = connection.prepareStatement(SELECT_QUIZ_SQL)) {
            ps.setString(1, quizName);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    id = rs.getInt(1);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return id;
    }

}