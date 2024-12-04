package dao;

import java.util.Set;

public interface RoleDAO {
    void addRole(int user_id, String role);
    Set<String> getRolesByUserId(int userId);
}
