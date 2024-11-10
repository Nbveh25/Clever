package dao;

import services.DataBaseService;
import dto.User;

import java.sql.*;

public class UserDAO {

    private final Connection connection = DataBaseService.getConnection();

    public int containsUser(String arg, String par) {
        String SELECT_USERS_SQL = "SELECT * FROM users WHERE " + par + " = ?";

        int result = 0;

        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USERS_SQL)) {
            preparedStatement.setString(1, arg);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.isBeforeFirst()) {
                    result = -1; //такой пользователь есть
                } else {
                    result = 1; // такого пользователя нет
                }
            }
        } catch (SQLException e) {
            return -1;
        }

        return result;
    }

    public int registerUser(User user) {
        String INSERT_USERS_SQL = "INSERT INTO users (login, email, password) VALUES (?, ?, ?)";

        int result = 0;

        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USERS_SQL)) {
            preparedStatement.setString(1, user.getLogin().toLowerCase());
            preparedStatement.setString(2, user.getEmail().toLowerCase());
            preparedStatement.setString(3, user.getPassword());
            result = preparedStatement.executeUpdate();

            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return result;
    }

    public int getIdByLogin(String login) {
        String SELECT_USERS_SQL = "SELECT * FROM users WHERE login = ?";
        int id = -1;

        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USERS_SQL)) {
            preparedStatement.setString(1, login);
            try (ResultSet rs = preparedStatement.executeQuery()) {
                while (rs.next()) {
                    id = rs.getInt("id");
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

        try (PreparedStatement ps = connection.prepareStatement(SELECT_EMAIL_SQL)) {
            ps.setString(1, login);
            try (ResultSet rs = ps.executeQuery()) {
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

        try (PreparedStatement ps = connection.prepareStatement(UPDATE_PASSWORD_SQL)) {
            ps.setString(1, newPassword);
            ps.setString(2, email);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}