package dao;

import model.User;
import utils.DataBaseUtil;

import java.sql.*;

public class UserDAO {

    private final Connection connection = DataBaseUtil.getConnection();

    public boolean containsUser(String arg, String par) {
        String SELECT_USERS_SQL = "SELECT * FROM users WHERE " + par + " = ?";

        boolean result = false;

        try(PreparedStatement ps = connection.prepareStatement(SELECT_USERS_SQL)) {
            ps.setString(1, arg);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.isBeforeFirst()) {
                    result = true; //такой пользователь есть
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return result;
    }

    public void registerUser(User user) {
        String INSERT_USERS_SQL = "INSERT INTO users (login, email, password) VALUES (?, ?, ?)";

        try(PreparedStatement ps = connection.prepareStatement(INSERT_USERS_SQL)) {
            ps.setString(1, user.getLogin().toLowerCase());
            ps.setString(2, user.getEmail().toLowerCase());
            ps.setString(3, user.getPassword());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

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

        try(PreparedStatement ps = connection.prepareStatement(SELECT_EMAIL_SQL)) {
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

        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_PASSWORD_SQL)) {
            preparedStatement.setString(1, newPassword);
            preparedStatement.setString(2, email);

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}