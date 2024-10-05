package model;

import db.DBFunctions;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class QuestionDAO {

    String DATABASE_NAME = "cleverdb";
    String USER_NAME = "postgres";
    String PASSWORD = "12345";

    public int addQuestion(Question question) {
        String INSERT_QUIZ_SQL = "INSERT INTO questions (quiz_id, question, type_question, media_path) VALUES (?, ?, ?, ?)";

        int questionId = 0;

        DBFunctions db = new DBFunctions();

        try (Connection conn = db.connect_to_db(DATABASE_NAME, USER_NAME, PASSWORD)) {
            PreparedStatement ps = conn.prepareStatement(INSERT_QUIZ_SQL, PreparedStatement.RETURN_GENERATED_KEYS);

            ps.setInt(1, question.getQuizId());
            ps.setString(2, question.getQuestion());
            ps.setString(3, question.getTypeQuestion());
            ps.setString(4, question.getMediaPath());
            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    questionId = rs.getInt(1);
                    System.out.println("Created question with ID: " + questionId);
                }
            }

            ps.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return questionId;
    }
}
