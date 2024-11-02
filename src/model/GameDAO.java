package model;

import db.DBFunctions;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GameDAO {
    String DATABASE_NAME = "cleverdb";
    String USER_NAME = "postgres";
    String PASSWORD = "12345";

    public void addGame(Game game) {
        String INSERT_GAME_SQL = "INSERT INTO games (quiz_id, code) VALUES (?, ?)";

        DBFunctions db = new DBFunctions();

        try (Connection conn = db.connect_to_db(DATABASE_NAME, USER_NAME, PASSWORD)) {
            try (PreparedStatement ps = conn.prepareStatement(INSERT_GAME_SQL, PreparedStatement.RETURN_GENERATED_KEYS)) {
                ps.setInt(1, game.getQuizId());
                ps.setString(2, game.getCode());
                if (ps.getGeneratedKeys().next()) {
                    game.setId(ps.getGeneratedKeys().getInt(1));
                }
                ps.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int getGameId(String code) {
        String SELECT_GAME_SQL = "SELECT id FROM games WHERE code = ?";
        int id = -1;
        DBFunctions db = new DBFunctions();

        try (Connection conn = db.connect_to_db(DATABASE_NAME, USER_NAME, PASSWORD)) {
            try (PreparedStatement ps = conn.prepareStatement(SELECT_GAME_SQL)) {
                ps.setString(1, code);
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        id = rs.getInt("id");
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return id;
    }

    public int getQuizId(int game_id) {
        String SELECT_GAME_SQL = "SELECT quiz_id FROM games WHERE id = ?";
        int quiz_id = -1;

        DBFunctions db = new DBFunctions();
        try (Connection conn = db.connect_to_db(DATABASE_NAME, USER_NAME, PASSWORD)) {
            try (PreparedStatement ps = conn.prepareStatement(SELECT_GAME_SQL)) {
                ps.setInt(1, game_id);
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        quiz_id = rs.getInt("quiz_id");
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return quiz_id;
    }
}
