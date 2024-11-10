package dao;

import services.DataBaseService;
import dto.Question;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class QuestionDAO {

    private final Connection connection = DataBaseService.getConnection();

    public int addQuestion(Question question) {
        String INSERT_QUESTION_SQL = "INSERT INTO questions (quiz_id, question, type_question, media_path) VALUES (?, ?, ?, ?)";
        int question_id = -1;

        try (PreparedStatement ps = connection.prepareStatement(INSERT_QUESTION_SQL, PreparedStatement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, question.getQuizId());
            ps.setString(2, question.getQuestion());
            ps.setString(3, question.getTypeQuestion());
            ps.setString(4, question.getMediaPath());

            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    question_id = rs.getInt(1);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return question_id;
    }

    public List<Question> getQuizQuestions(int quizId) {
        List<Question> questionList = new ArrayList<>();
        String SELECT_QUESTION_SQL = "SELECT id, quiz_id, question, type_question, media_path FROM questions WHERE quiz_id=?";

        try(PreparedStatement ps = connection.prepareStatement(SELECT_QUESTION_SQL)) {
            ps.setInt(1, quizId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    int id = rs.getInt(1);
                    String question_text = rs.getString(3);
                    String typeQuestion = rs.getString(4);
                    String mediaPath = rs.getString(5);
                    Question question = new Question(id, quizId, question_text, typeQuestion, mediaPath);
                    questionList.add(question);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return questionList;
    }

}
