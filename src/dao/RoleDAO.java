package dao;

import utils.DataBaseUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RoleDAO {
    private final Connection connection = DataBaseUtil.getConnection();

    public void updateRoleForUser(int user_id) {
        String UPDATE_ROLE_SQL = "INSERT INTO user_roles (user_id, role_id) VALUES (?, 2) ON CONFLICT (user_id, role_id) DO NOTHING;";

        try (PreparedStatement ps = connection.prepareStatement(UPDATE_ROLE_SQL)) {
            ps.setInt(1, user_id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public String getRoleById(int user_id) {
        String SELECT_ROLE_SQL = "SELECT * FROM roles WHERE user_id=?";
        String role = null;
        try (PreparedStatement ps = connection.prepareStatement(SELECT_ROLE_SQL)){
            ps.setInt(1, user_id);
            try(ResultSet rs = ps.executeQuery()) {
                if(rs.next()) {
                    role = rs.getString("role");
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return role;
    }
}
