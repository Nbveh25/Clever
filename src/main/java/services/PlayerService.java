package services;

import dto.PlayerDTO;
import model.Player;

import java.util.List;

public interface PlayerService {

    void addPlayer(PlayerDTO playerDTO);
    List<PlayerDTO> getAllPlayers(int game_id);
    int getTotalScore(int user_id, int game_id);
    void updateTotalScore(int user_id, int game_id, int point);
    PlayerDTO toPlayerDTO(Player player);
}
