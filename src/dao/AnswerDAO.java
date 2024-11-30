package dao;

import model.Answer;
import utils.DataBaseUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AnswerDAO {

    private final Connection connection = DataBaseUtil.getConnection();

    public void addAnswer(Answer answer, String table) {
        String INSERT_QUIZ_SQL = "INSERT INTO " + table + " (question_id, answer) VALUES (?,?)";

        try (PreparedStatement ps = connection.prepareStatement(INSERT_QUIZ_SQL)) {
            ps.setInt(1, answer.getQuestion_id());
            ps.setString(2, answer.getAnswer());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Answer> getAnswers(int question_id, String table) {
        String SELECT_ANSWER_SQL = "SELECT id, answer FROM " + table + " WHERE question_id = ?";
        List<Answer> answers = new ArrayList<>();

        try (PreparedStatement ps = connection.prepareStatement(SELECT_ANSWER_SQL)) {
            ps.setInt(1, question_id);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    int id = rs.getInt(1);
                    String answer = rs.getString(2);
                    answers.add(new Answer(id, question_id, answer));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return answers;
    }

    public String getAnswerById(int id, String table) {
        String SELECT_ANSWER_SQL = "SELECT answer FROM " + table + " WHERE id = ?";

        String answer = null;

        try (PreparedStatement ps = connection.prepareStatement(SELECT_ANSWER_SQL)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    answer = rs.getString(1);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return answer;
    }
}
