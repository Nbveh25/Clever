package dao;

import model.Game;

public interface GameDAO {
    int addGame(Game game);
    int getGameId(String code);
    int getQuizId(int game_id);
    void deleteGame(int game_id);
}
