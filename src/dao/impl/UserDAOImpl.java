package dao.impl;

import dao.UserDAO;
import model.User;
import utils.DataBaseUtil;

import java.sql.*;

public class UserDAOImpl implements UserDAO {

    private final Connection connection = DataBaseUtil.getConnection();

    @Override
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

    @Override
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

    @Override
    public int getIdByLogin(String login) {
        String SELECT_USERS_SQL = "SELECT * FROM users WHERE login = ?";

        int id = -1;

        try (PreparedStatement ps = connection.prepareStatement(SELECT_USERS_SQL)) {
            ps.setString(1, login);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    id = rs.getInt("id");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return id;
    }

    @Override
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

    @Override
    public void updateLogin(int id, String login) {
        String UPDATE_LOGIN_SQL = "UPDATE users SET login = ? WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(UPDATE_LOGIN_SQL)) {
            ps.setString(1, login);
            ps.setInt(2, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
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

    @Override
    public void deleteUser(int userId) {
        String DELETE_USERS_SQL = "DELETE FROM users WHERE id = ?";

        try (PreparedStatement ps = connection.prepareStatement(DELETE_USERS_SQL)) {
            ps.setInt(1, userId);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}