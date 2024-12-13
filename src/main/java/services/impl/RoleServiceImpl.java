package services.impl;

import dao.RoleDAO;
import dao.impl.RoleDAOImpl;
import services.RoleService;

import java.util.Set;

public class RoleServiceImpl implements RoleService {
    private final RoleDAO roleDAO;

    public RoleServiceImpl() {
        this.roleDAO = new RoleDAOImpl();
    }

    @Override
    public void addRole(int id, String role) {
        roleDAO.addRole(id, role);
    }

    @Override
    public Set<String> getRoles(int id) {
        return roleDAO.getRolesByUserId(id);
    }


}
