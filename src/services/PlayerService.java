package services;

import dto.PlayerDTO;

public interface PlayerService {

    void addPlayer(PlayerDTO playerDTO);
    int getTotalScore(int user_id, int game_id);
    void updateTotalScore(int user_id, int game_id, int point);
}
