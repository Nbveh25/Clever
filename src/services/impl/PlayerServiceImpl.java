package services.impl;

import dao.PlayerDAO;
import dto.PlayerDTO;
import model.Player;
import services.PlayerService;

public class PlayerServiceImpl implements PlayerService {
    private final PlayerDAO playerDAO;

    public PlayerServiceImpl() {
        this.playerDAO = new PlayerDAO();
    }

    @Override
    public void addPlayer(PlayerDTO playerDTO) {
        playerDAO.addPlayer(
                new Player(
                        playerDTO.getUser_id(),
                        playerDTO.getGame_id(),
                        playerDTO.getLogin(),
                        playerDTO.getTotal_score()
                )
        );
    }

    @Override
    public int getTotalScore(int user_id, int game_id) {
        return playerDAO.getTotalScore(user_id, game_id);
    }

    @Override
    public void updateTotalScore(int user_id, int game_id, int point) {
        playerDAO.updateTotalScore(user_id, game_id, point);
    }
}
