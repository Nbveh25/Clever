package dao;

import model.Player;

import java.util.List;

public interface PlayerDAO {
    void addPlayer(Player player);
    List<Player> getAllPlayers(int game_id);
    int getTotalScore(int userId, int gameId);
    void updateTotalScore(int user_id, int game_id, int point);
}
