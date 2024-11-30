package services;

public interface GameService {
    int getGameIdByCode(String code);
    int getQuizIdByGameId(int gameId);
}
