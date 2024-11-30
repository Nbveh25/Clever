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
}
