package services.impl;

import dao.UserDAO;
import dao.impl.UserDAOImpl;
import dto.UserDTO;
import model.User;
import services.UserService;
import utils.Constants;

public class UserServiceImpl implements UserService {
    private final UserDAO userDAO;

    public UserServiceImpl() {
        this.userDAO = new UserDAOImpl();
    }

    @Override
    public void registerUser(UserDTO userDTO) {
        if (dataOfUserExists(userDTO.getLogin(), Constants.LOGIN) || dataOfUserExists(userDTO.getEmail(), Constants.EMAIL)) {
            return;
        }
        userDAO.registerUser(
                new User(
                        userDTO.getLogin(),
                        userDTO.getEmail(),
                        userDTO.getPassword()
                )
        );
    }

    @Override
    public boolean userExists(String login, String password) {
        return userDAO.containsUser(login, Constants.LOGIN) && userDAO.containsUser(password, Constants.PASSWORD);
    }

    @Override
    public boolean userExists(UserDTO userDTO) {
        return userDAO.containsUser(userDTO.getLogin(), Constants.LOGIN) || userDAO.containsUser(userDTO.getEmail(), Constants.EMAIL) || userDAO.containsUser(userDTO.getPassword(), Constants.PASSWORD);
    }

    @Override
    public boolean dataOfUserExists(String identifier, String type) {
        return userDAO.containsUser(identifier, type);
    }

    @Override
    public String getUserEmail(String login) {
        return userDAO.getUserEmail(login);
    }

    @Override
    public int getIdByLogin(String login) {
        return userDAO.getIdByLogin(login);
    }

    @Override
    public void updateLogin(int id, String login) {
        userDAO.updateLogin(id, login);
    }

    @Override
    public void updateUserPassword(String email, String newPassword) {
        userDAO.updateUserPassword(email, newPassword);
    }

    @Override
    public void updateProfileIconUrl(int user_id, String iconUrl) {
        userDAO.updateProfileIconUrl(user_id, iconUrl);
    }

    @Override
    public String getProfileIconUrl(int userId) {
        return userDAO.getProfileIconUrl(userId);
    }

    @Override
    public void deleteUser(int userId) {
        userDAO.deleteUser(userId);
    }
}