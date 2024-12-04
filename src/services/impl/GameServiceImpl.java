package services.impl;

import dao.GameDAO;
import dao.impl.GameDAOImpl;
import dto.GameDTO;
import model.Game;
import services.GameService;

public class GameServiceImpl implements GameService {
    private final GameDAO gameDAO;

    public GameServiceImpl() {
        this.gameDAO = new GameDAOImpl();
    }

    @Override
    public int addGame(GameDTO gameDTO) {
        return gameDAO.addGame(
                new Game(
                        gameDTO.getQuizId(),
                        gameDTO.getCode()
                )
        );
    }

    @Override
    public int getGameIdByCode(String code) {
        return gameDAO.getGameId(code);
    }

    @Override
    public int getQuizIdByGameId(int gameId) {
        return gameDAO.getQuizId(gameId);
    }

    @Override
    public void deleteGame(int gameId) {
        gameDAO.deleteGame(gameId);
    }
}
