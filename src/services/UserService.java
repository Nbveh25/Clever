package services;

import dto.UserDTO;

public interface UserService {
    void registerUser(UserDTO userDTO);
    boolean userExists(String login, String password);
    boolean userExists(UserDTO userDTO);
    boolean dataOfUserExists(String identifier, String type);
    String getUserEmail(String login);
    int getIdByLogin(String login);
    void updateLogin(int id, String login);
    void updateUserPassword(String email, String newPassword);
    void deleteUser(int userId);
}