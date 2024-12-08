package dao;

import model.User;

public interface UserDAO {
    boolean containsUser(String arg, String par);
    void registerUser(User user);
    int getIdByLogin(String login);
    String getUserEmail(String login);
    void updateLogin(int id, String login);
    void updateUserPassword(String email, String newPassword);
    void updateProfileIconUrl(int userId, String iconUrl);
    String getProfileIconUrl(int userId);
    void deleteUser(int userId);
}
