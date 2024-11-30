package services.impl;

import dao.GameDAO;
import services.GameService;

public class GameServiceImpl implements GameService {
    private final GameDAO gameDAO;

    public GameServiceImpl() {
        this.gameDAO = new GameDAO();
    }
    @Override
    public int getGameIdByCode(String code) {
        return gameDAO.getGameId(code);
    }

    @Override
    public int getQuizIdByGameId(int gameId) {
        return gameDAO.getQuizId(gameId);
    }
}
