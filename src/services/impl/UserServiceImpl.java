package services.impl;

import dao.UserDAO;
import model.User;
import services.UserService;
import utils.Constants;

public class UserServiceImpl implements UserService {
    private final UserDAO userDAO;

    public UserServiceImpl() {
        this.userDAO = new UserDAO();
    }

    @Override
    public boolean registerUser(String login, String email, String password) {
        if (dataOfUserExists(login, "login") || dataOfUserExists(email, "email")) {
            return false;
        }
        userDAO.registerUser(new User(login, email, password));
        return true;
    }

    @Override
    public boolean userExists(String login, String password) {
        return userDAO.containsUser(login, Constants.LOGIN) && userDAO.containsUser(password, Constants.PASSWORD);
    }

    @Override
    public boolean userExists(String login, String email, String password) {
        return userDAO.containsUser(login, Constants.LOGIN) && userDAO.containsUser(email, Constants.EMAIL) && userDAO.containsUser(password, Constants.PASSWORD);
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
    public void updateUserPassword(String email, String newPassword) {
        userDAO.updateUserPassword(email, newPassword);
    }
}