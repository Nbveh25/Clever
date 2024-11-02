package model;

import db.DBFunctions;

import java.sql.*;

public class UserDAO {

    String DATABASE_NAME = "cleverdb";
    String USER_NAME = "postgres";
    String PASSWORD = "12345";

    public int containsUser(String arg, String par) {
        String SELECT_USERS_SQL = "SELECT * FROM users WHERE " + par + " = ?";
        DBFunctions db = new DBFunctions();
        Connection connection = db.connect_to_db(DATABASE_NAME, USER_NAME, PASSWORD);
        PreparedStatement preparedStatement = null;

        int result = 0;

        try {
            preparedStatement = connection.prepareStatement(SELECT_USERS_SQL);
            preparedStatement.setString(1, arg);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.isBeforeFirst()) {
                result = -1; //такой пользователь есть
            } else {
                result = 1; // такого пользователя нет
            }

            connection.close();
            preparedStatement.close();
            resultSet.close();
        } catch (SQLException e) {
            return -1;
        }

        return result;
    }

    public int registerUser(User user) {
        String INSERT_USERS_SQL = "INSERT INTO users (login, email, password) VALUES (?, ?, ?)";

        int result = 0;

        DBFunctions db = new DBFunctions();

        Connection connection = db.connect_to_db(DATABASE_NAME, USER_NAME, PASSWORD);

        PreparedStatement preparedStatement = null;


        try {
            preparedStatement = connection.prepareStatement(INSERT_USERS_SQL);

            preparedStatement.setString(1, user.getLogin().toLowerCase());
            preparedStatement.setString(2, user.getEmail().toLowerCase());
            preparedStatement.setString(3, user.getPassword());
            result = preparedStatement.executeUpdate();

            connection.close();
            preparedStatement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return result;
    }

    public int getIdByLogin(String login) {
        String SELECT_USERS_SQL = "SELECT * FROM users WHERE login = ?";
        DBFunctions db = new DBFunctions();
        int id = -1;
        try (Connection conn = db.connect_to_db(DATABASE_NAME, USER_NAME, PASSWORD)) {
            try (PreparedStatement preparedStatement = conn.prepareStatement(SELECT_USERS_SQL)) {
                preparedStatement.setString(1, login);
                try (ResultSet rs = preparedStatement.executeQuery()) {
                    while (rs.next()) {
                        id = rs.getInt("id");
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return id;
    }

    public String getUserEmail(String login) {
        String SELECT_EMAIL_SQL = "SELECT email FROM users WHERE login = ?";
        String email = null;
        DBFunctions db = new DBFunctions();

        try (Connection conn = db.connect_to_db(DATABASE_NAME, USER_NAME, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(SELECT_EMAIL_SQL)) {
            pstmt.setString(1, login); // Установка значения параметра

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    email = rs.getString("email");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return email;
    }

    public void updateUserPassword(String email, String newPassword) {
        String UPDATE_PASSWORD_SQL = "UPDATE users SET password = ? WHERE email = ?";
        DBFunctions db = new DBFunctions();

        try (Connection connection = db.connect_to_db(DATABASE_NAME, USER_NAME, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_PASSWORD_SQL)) {

            preparedStatement.setString(1, newPassword);
            preparedStatement.setString(2, email);

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Password updated");
            } else {
                    System.out.println("User not found");
            }
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }
}