package dao.impl;

import dao.RoleDAO;
import utils.Constants;
import utils.DataBaseUtil;

import java.sql.*;
import java.util.HashSet;
import java.util.Set;

public class RoleDAOImpl implements RoleDAO {
    private final Connection connection = DataBaseUtil.getConnection();

    public RoleDAOImpl() {
        createUserRolesTableIfNotExists();
        createRolesTableIfNotExists();
    }

    @Override
    public void addRole (int user_id, String role) {

        int role_id = 0;
        if (role.equals(Constants.SIMPLE)) {
            role_id = 1;
        } else if (role.equals(Constants.PRO)) {
            role_id = 2;
        }

        String UPDATE_ROLE_SQL = "INSERT INTO user_roles (user_id, role_id) VALUES (?, ?) ON CONFLICT (user_id, role_id) DO NOTHING;";

        try (PreparedStatement ps = connection.prepareStatement(UPDATE_ROLE_SQL)) {
            ps.setInt(1, user_id);
            ps.setInt(2, role_id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Set<String> getRolesByUserId(int userId) {
        String SELECT_ROLES_SQL = "SELECT r.role FROM user_roles ur JOIN roles r ON ur.role_id = r.id WHERE ur.user_id = ?";
        Set<String> roles = new HashSet<>();

        try (PreparedStatement ps = connection.prepareStatement(SELECT_ROLES_SQL)) {
            ps.setInt(1, userId);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    roles.add(rs.getString("role"));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return roles;
    }

    private void createRolesTableIfNotExists() {
        String CREATE_TABLE_IF_NOT_EXISTS_SQL = "CREATE TABLE IF NOT EXISTS roles (\n" +
                "    id SERIAL PRIMARY KEY,\n" +
                "    role VARCHAR(50) NOT NULL\n" +
                ");";

        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(CREATE_TABLE_IF_NOT_EXISTS_SQL);

            String CHECK_ROLES_SQL = "SELECT COUNT(*) FROM roles";
            try (ResultSet rs = statement.executeQuery(CHECK_ROLES_SQL)) {
                if (rs.next() && rs.getInt(1) == 0) {
                    String INSERT_ROLES_SQL = "INSERT INTO roles (id, role) VALUES (1, 'SIMPLE'), (2, 'PRO')";
                    statement.executeUpdate(INSERT_ROLES_SQL);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void createUserRolesTableIfNotExists() {
        String CREATE_TABLE_IF_NOT_EXISTS_SQL = "CREATE TABLE IF NOT EXISTS user_roles (\n" +
                "    user_id INTEGER NOT NULL,\n" +
                "    role_id INTEGER NOT NULL,\n" +
                "    PRIMARY KEY (user_id, role_id)\n" +
                ");";

        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(CREATE_TABLE_IF_NOT_EXISTS_SQL);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
