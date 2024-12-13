package dao.impl;

import dao.PlayerDAO;
import model.Player;
import utils.DataBaseUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PlayerDAOImpl implements PlayerDAO {

    private final Connection connection = DataBaseUtil.getConnection();

    public PlayerDAOImpl() {
        createPlayersTableIfNotExists();
    }

    @Override
    public void addPlayer(Player player) {
        String INSERT_PLAYER_SQL = "INSERT INTO players (user_id, game_id, login, total_score) VALUES(?,?,?,?)";

        try (PreparedStatement ps = connection.prepareStatement(INSERT_PLAYER_SQL)) {
            ps.setInt(1, player.getUser_id());
            ps.setInt(2, player.getGame_id());
            ps.setString(3, player.getLogin());
            ps.setInt(4, player.getTotal_score());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Player> getAllPlayers(int game_id) {
        String SELECT_PLAYER_SQL = "SELECT * FROM players WHERE game_id=? ORDER BY total_score";
        List<Player> players = new ArrayList<>();

        try (PreparedStatement ps = connection.prepareStatement(SELECT_PLAYER_SQL)) {
            ps.setInt(1, game_id);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    int user_id = rs.getInt("user_id");
                    String login = rs.getString("login");
                    int total_score = rs.getInt("total_score");
                    String icon_url = rs.getString("icon_url");
                    Player player = new Player(user_id, game_id, login, total_score, icon_url);
                    players.add(player);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return players;
    }

    @Override
    public int getTotalScore(int userId, int gameId) {
        String SELECT_POINTS_SQL = "SELECT * FROM players WHERE user_id=? AND game_id=?";

        int total_score = 0;

        try (PreparedStatement ps = connection.prepareStatement(SELECT_POINTS_SQL)) {
            ps.setInt(1, userId);
            ps.setInt(2, gameId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    total_score = rs.getInt("total_score");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return total_score;
    }

    @Override
    public void updateTotalScore(int user_id, int game_id, int point) {
        String UPDATE_SCORE_SQL = "UPDATE players SET total_score=? WHERE user_id=? AND game_id=?";

        try (PreparedStatement ps = connection.prepareStatement(UPDATE_SCORE_SQL)) {
            ps.setInt(1, point);
            ps.setInt(2, user_id);
            ps.setInt(3, game_id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void createPlayersTableIfNotExists() {
        String CREATE_TABLE_IF_NOT_EXISTS_SQL = "CREATE TABLE IF NOT EXISTS players (\n" +
                "    user_id INTEGER NOT NULL,\n" +
                "    game_id INTEGER NOT NULL,\n" +
                "    login VARCHAR(255) NOT NULL,\n" +
                "    total_score INTEGER NOT NULL,\n" +
                "    icon_url VARCHAR(255),\n" +
                "    PRIMARY KEY (user_id, game_id)\n" +
                ")";

        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(CREATE_TABLE_IF_NOT_EXISTS_SQL);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
