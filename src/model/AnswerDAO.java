package model;

import db.DBFunctions;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AnswerDAO {

    String DATABASE_NAME = "cleverdb";
    String USER_NAME = "postgres";
    String PASSWORD = "12345";

    public void addAnswer(int question_id, String answer, String table) {
        String INSERT_QUIZ_SQL = "INSERT INTO " + table + " (question_id, answer) VALUES (?,?)";

        DBFunctions db = new DBFunctions();

        try (Connection conn = db.connect_to_db(DATABASE_NAME, USER_NAME, PASSWORD)) {

            PreparedStatement ps = conn.prepareStatement(INSERT_QUIZ_SQL);
            ps.setInt(1, question_id);
            ps.setString(2, answer);
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
