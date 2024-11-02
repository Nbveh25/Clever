package model;

import db.DBFunctions;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PlayerDAO {
    String DATABASE_NAME = "cleverdb";
    String USER_NAME = "postgres";
    String PASSWORD = "12345";

    public void addPlayer(Player player) {
        String INSERT_PLAYER_SQL = "INSERT INTO players (user_id, game_id, total_score) VALUES(?,?,?)";

        DBFunctions db = new DBFunctions();

        try (Connection conn = db.connect_to_db(DATABASE_NAME, USER_NAME, PASSWORD)) {
            try (PreparedStatement ps = conn.prepareStatement(INSERT_PLAYER_SQL)) {
                ps.setInt(1, player.getUser_id());
                ps.setInt(2, player.getGame_id());
                ps.setInt(3, player.getTotal_score());
                ps.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //public void updateTotalScore(int user_id, int point) {}
}
