package dao;

import model.Game;
import utils.DataBaseUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GameDAO {
    private final Connection connection = DataBaseUtil.getConnection();

    public int addGame(Game game) {
        String INSERT_GAME_SQL = "INSERT INTO games (quiz_id, code) VALUES (?, ?)";

        int game_id = -1;

        try (PreparedStatement ps = connection.prepareStatement(INSERT_GAME_SQL, PreparedStatement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, game.getQuizId());
            ps.setString(2, game.getCode());

            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    game_id = rs.getInt(1);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return game_id;
    }

    public int getGameId(String code) {
        String SELECT_GAME_SQL = "SELECT id FROM games WHERE code = ?";
        int id = -1;

        try (PreparedStatement ps = connection.prepareStatement(SELECT_GAME_SQL)) {
            ps.setString(1, code);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    id = rs.getInt("id");
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

        try (PreparedStatement ps = connection.prepareStatement(SELECT_GAME_SQL)) {
            ps.setInt(1, game_id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    quiz_id = rs.getInt("quiz_id");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return quiz_id;
    }
}
