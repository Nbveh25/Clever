package dao.impl;

import dao.RoleDAO;
import utils.Constants;
import utils.DataBaseUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

public class RoleDAOImpl implements RoleDAO {
    private final Connection connection = DataBaseUtil.getConnection();

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
}
