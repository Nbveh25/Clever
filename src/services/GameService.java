package services;

import dto.GameDTO;

public interface GameService {
    int addGame(GameDTO gameDTO);
    int getGameIdByCode(String code);
    int getQuizIdByGameId(int gameId);
}
