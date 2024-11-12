package utils;

import dao.UserDAO;

import java.util.regex.Pattern;

public class UserUtil {

    public static String validateUser(String login, String password) {
        UserDAO userDAO = new UserDAO();
        String message = "OK";
        int minLength = 6;

        if (userDAO.containsUser(login, "login") == 0) {
            message = "Пользователь с таким логином отсутствует.";
        } else if (password.length() < minLength) {
            message = "Пароль должен содержать минимум 6 символов.";
        } else if (!Pattern.compile("[A-Z]").matcher(password).find()) {
            message = "Пароль должен содержать хотя бы одну заглавную букву.";
        } else if (!Pattern.compile("[a-z]").matcher(password).find()) {
            message = "Пароль должен содержать хотя бы одну строчную букву.";
        } else if (!Pattern.compile("[0-9]").matcher(password).find()) {
            message = "Пароль должен содержать хотя бы одну цифру.";
        } else if (!Pattern.compile("[!@#$%^&*(),.?\":{}|<>]").matcher(password).find()) {
            message = "Пароль должен содержать хотя бы один специальный символ.";
        } else if (userDAO.containsUser(login, password) == 0) {
            message = "Неверный пароль.";
        }
        return message;
    }
}