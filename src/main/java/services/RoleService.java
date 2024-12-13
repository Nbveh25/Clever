package services;

import java.util.Set;

public interface RoleService {
    void addRole(int id, String role);
    Set<String> getRoles(int id);
}
