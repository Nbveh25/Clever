package dao.impl;

import dao.PlayerDAO;
import model.Player;
import utils.DataBaseUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PlayerDAOImpl implements PlayerDAO {

    private final Connection connection = DataBaseUtil.getConnection();

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
                    Player player = new Player(user_id, game_id, login, total_score);
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
}
