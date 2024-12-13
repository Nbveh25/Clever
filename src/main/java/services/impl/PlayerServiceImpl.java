package services.impl;

import dao.PlayerDAO;
import dao.impl.PlayerDAOImpl;
import dto.PlayerDTO;
import model.Player;
import services.PlayerService;

import java.util.List;
import java.util.stream.Collectors;

public class PlayerServiceImpl implements PlayerService {
    private final PlayerDAO playerDAO;

    public PlayerServiceImpl() {
        this.playerDAO = new PlayerDAOImpl();
    }

    @Override
    public void addPlayer(PlayerDTO playerDTO) {
        playerDAO.addPlayer(
                new Player(
                        playerDTO.getUser_id(),
                        playerDTO.getGame_id(),
                        playerDTO.getLogin(),
                        playerDTO.getTotal_score(),
                        playerDTO.getIcon_url()
                )
        );
    }

    @Override
    public List<PlayerDTO> getAllPlayers(int game_id) {
        return playerDAO.getAllPlayers(game_id).stream().map(this::toPlayerDTO).collect(Collectors.toList());
    }

    @Override
    public int getTotalScore(int user_id, int game_id) {
        return playerDAO.getTotalScore(user_id, game_id);
    }

    @Override
    public void updateTotalScore(int user_id, int game_id, int point) {
        playerDAO.updateTotalScore(user_id, game_id, point);
    }

    @Override
    public PlayerDTO toPlayerDTO(Player player) {
        return new PlayerDTO(
                player.getUser_id(),
                player.getGame_id(),
                player.getLogin(),
                player.getTotal_score(),
                player.getIcon_url()
        );
    }


}
