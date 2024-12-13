package dao.impl;

import dao.AnswerDAO;
import model.Answer;
import utils.DataBaseUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AnswerDAOImpl implements AnswerDAO {

    private final Connection connection = DataBaseUtil.getConnection();
    private final static String WRONG_ANSWERS = "wrong_answers";
    private final static String RIGHT_ANSWERS = "right_answers";

    public AnswerDAOImpl() {
        createTableIfNotExists(WRONG_ANSWERS);
        createTableIfNotExists(RIGHT_ANSWERS);
    }

    @Override
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

    @Override
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

    @Override
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

    private void createTableIfNotExists(String tableName) {
        String CREATE_TABLE_IF_NOT_EXISTS_SQL = "CREATE TABLE IF NOT EXISTS " + tableName + " (\n" +
                "    id SERIAL PRIMARY KEY,\n" +
                "    question_id INTEGER NOT NULL,\n" +
                "    answer TEXT NOT NULL\n" +
                ");";

        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(CREATE_TABLE_IF_NOT_EXISTS_SQL);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
