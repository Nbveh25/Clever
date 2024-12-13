package dao.impl;

import dao.GameDAO;
import model.Game;
import utils.DataBaseUtil;

import java.sql.*;

public class GameDAOImpl implements GameDAO {
    private final Connection connection = DataBaseUtil.getConnection();

    public GameDAOImpl() {
        createGamesTableIfNotExists();
    }

    @Override
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

    @Override
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

    @Override
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

    @Override
    public void deleteGame(int game_id) {
        String DELETE_GAME_SQL = "DELETE FROM games WHERE id = ?";

        try (PreparedStatement ps = connection.prepareStatement(DELETE_GAME_SQL)) {
            ps.setInt(1, game_id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void createGamesTableIfNotExists() {
        String CREATE_TABLE_IF_NOT_EXISTS_SQL = "CREATE TABLE IF NOT EXISTS games (\n" +
                "    id SERIAL PRIMARY KEY,\n" +
                "    quiz_id INTEGER NOT NULL,\n" +
                "    code VARCHAR(255) NOT NULL\n" +
                ");";

        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(CREATE_TABLE_IF_NOT_EXISTS_SQL);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
