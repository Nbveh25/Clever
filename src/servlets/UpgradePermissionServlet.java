package servlets;

import dao.RoleDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import services.RoleService;
import services.impl.RoleServiceImpl;

import java.io.IOException;

@WebServlet(name = "UpgradePermissionServlet", urlPatterns = "/upgrade-permission-servlet")
public class UpgradePermissionServlet extends HttpServlet {
    private final RoleService roleService = new RoleServiceImpl();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        int user_id = (int) session.getAttribute("user_id");
        roleService.addRole(user_id);
    }
}
