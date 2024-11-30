package services;

public interface UserService {
    boolean registerUser(String login, String email, String password);
    boolean userExists(String login, String password);
    boolean userExists(String login, String email, String password);
    boolean dataOfUserExists(String identifier, String type);
    String getUserEmail(String login);
    int getIdByLogin(String login);
    void updateUserPassword(String email, String newPassword);
}