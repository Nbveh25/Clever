package model;

import db.DBFunctions;

import java.sql.*;

public class UserDAO {

    public int containsUser(String arg, String par) {
        String SELECT_USERS_SQL = "SELECT * FROM users WHERE " + par + " = ?";
        DBFunctions db = new DBFunctions();
        Connection connection = db.connect_to_db("users", "postgres", "12345");
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

        Connection connection = db.connect_to_db("users", "postgres", "12345");

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

    public String getUserEmail(String login) {
        String SELECT_EMAIL_SQL = "SELECT email FROM users WHERE login = ?";
        String email = null;
        DBFunctions db = new DBFunctions();

        try (Connection conn = db.connect_to_db("users", "postgres", "12345")) {
            try (PreparedStatement pstmt = conn.prepareStatement(SELECT_EMAIL_SQL)) {
                pstmt.setString(1, login); // Установка значения параметра

                try (ResultSet rs = pstmt.executeQuery()) {
                    while (rs.next()) {
                        email = rs.getString("email");
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return email;
    }
}