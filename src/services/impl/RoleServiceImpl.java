package services.impl;

import dao.RoleDAO;
import services.RoleService;

public class RoleServiceImpl implements RoleService {
    private final RoleDAO roleDAO;

    public RoleServiceImpl() {
        this.roleDAO = new RoleDAO();
    }

    @Override
    public void addRole(int id) {
        roleDAO.addRole(id);
    }


}
