package model;

import db.DBFunctions;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AnswerDAO {

    String DATABASE_NAME = "cleverdb";
    String USER_NAME = "postgres";
    String PASSWORD = "12345";

    public void addAnswer(Answer answer, String table) {
        String INSERT_QUIZ_SQL = "INSERT INTO " + table + " (question_id, answer) VALUES (?,?)";

        DBFunctions db = new DBFunctions();

        try (Connection conn = db.connect_to_db(DATABASE_NAME, USER_NAME, PASSWORD)) {
            try (PreparedStatement ps = conn.prepareStatement(INSERT_QUIZ_SQL, Statement.RETURN_GENERATED_KEYS)) {
                ps.setInt(1, answer.getQuestion_id());
                ps.setString(2, answer.getAnswer());
                ps.executeUpdate();
                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) {
                        answer.setId(rs.getInt(1));
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Answer> getAnswers(int question_id, String table) {
        String SELECT_ANSWER_SQL = "SELECT id, answer FROM " + table + " WHERE question_id = ?";
        List<Answer> answers = new ArrayList<Answer>();
        DBFunctions db = new DBFunctions();

        try(Connection connection = db.connect_to_db(DATABASE_NAME, USER_NAME, PASSWORD)) {
            try (PreparedStatement ps = connection.prepareStatement(SELECT_ANSWER_SQL)) {
                ps.setInt(1, question_id);
                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        int id = rs.getInt(1);
                        String answer = rs.getString(2);
                        answers.add(new Answer(id, question_id, answer));
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return answers;
    }

    public String getAnswerById(int id, String table) {
        String SELECT_ANSWER_SQL = "SELECT answer FROM " + table + " WHERE id = ?";
        DBFunctions db = new DBFunctions();
        String answer = null;
        try (Connection connection = db.connect_to_db(DATABASE_NAME, USER_NAME, PASSWORD)) {
            try (PreparedStatement ps = connection.prepareStatement(SELECT_ANSWER_SQL)) {
                ps.setInt(1, id);
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        answer = rs.getString(1);
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return answer;
    }
}
