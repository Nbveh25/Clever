package logic;

import db.DBFunctions;
import model.User;
import model.UserDAO;

public class Main {
    public static void main(String[] args) {
        UserDAO userDAO = new UserDAO();
        User user = new User("Tir", "tima.bik@box.ru", "123");

        try {
            userDAO.registerUser(user);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
