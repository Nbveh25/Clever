package dao.impl;

import dao.QuizDAO;
import dto.QuizDTO;
import model.Quiz;
import utils.DataBaseUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class QuizDAOImpl implements QuizDAO {

    private final Connection connection = DataBaseUtil.getConnection();

    @Override
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

    @Override
    public List<QuizDTO> getAllQuiz() {
        List<QuizDTO> quizList = new ArrayList<>();
        String SELECT_QUIZ_SQL = "SELECT id, quiz_title, quiz_description, quiz_type, quiz_icon_path FROM quizzes";

        try (PreparedStatement ps = connection.prepareStatement(SELECT_QUIZ_SQL)) {
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    int id = rs.getInt(1);
                    String quizName = rs.getString(2);
                    String quizDescription = rs.getString(3);
                    String quizType = rs.getString(4);
                    String quizIconPath = rs.getString(5);

                    QuizDTO quizDTO = new QuizDTO(id, quizName, quizDescription, quizType, quizIconPath);
                    quizList.add(quizDTO);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return quizList;
    }
}