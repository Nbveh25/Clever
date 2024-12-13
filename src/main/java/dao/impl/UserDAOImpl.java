package dao.impl;

import dao.UserDAO;
import model.User;
import utils.DataBaseUtil;

import java.sql.*;

public class UserDAOImpl implements UserDAO {

    private final Connection connection = DataBaseUtil.getConnection();

    public UserDAOImpl() {
        createTableIfNotExists();
    }

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
    public void updateProfileIconUrl(int userId, String iconUrl) {
        String UPDATE_PROFILE_ICONS_SQL = "UPDATE users SET icon_url = ? WHERE id = ?";

        try (PreparedStatement ps = connection.prepareStatement(UPDATE_PROFILE_ICONS_SQL)) {
            ps.setString(1, iconUrl);
            ps.setInt(2, userId);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public String getProfileIconUrl(int userId) {
        String SELECT_PROFILE_ICONS_SQL = "SELECT icon_url FROM users WHERE id = ?";
        String iconUrl = null;
        try (PreparedStatement ps = connection.prepareStatement(SELECT_PROFILE_ICONS_SQL)) {
            ps.setInt(1, userId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    iconUrl = rs.getString("icon_url");
                    System.out.println(userId);
                    System.out.println("ahahah" + iconUrl);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return iconUrl;
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

    private void createTableIfNotExists() {
        String CREATE_TABLE_IF_NOT_EXISTS_SQL = "CREATE TABLE IF NOT EXISTS users (\n" +
                "    id SERIAL PRIMARY KEY,\n" +
                "    login VARCHAR(255) NOT NULL,\n" +
                "    email VARCHAR(255) NOT NULL UNIQUE,\n" +
                "    password VARCHAR(250) NOT NULL,\n" +
                "    icon_url VARCHAR(255) DEFAULT 'https://res.cloudinary.com/dsrqq4er2/image/upload/v1733679866/person_account_pic_default_krh7ic.png'" +
                ");";

        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(CREATE_TABLE_IF_NOT_EXISTS_SQL);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}