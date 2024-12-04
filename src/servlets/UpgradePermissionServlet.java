package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import services.RoleService;
import utils.Constants;

import java.io.IOException;
import java.util.Set;

@WebServlet(name = "UpgradePermissionServlet", urlPatterns = "/upgrade-permission-servlet")
public class UpgradePermissionServlet extends HttpServlet {
    private RoleService roleService;

    @Override
    public void init() throws ServletException {
        super.init();
        roleService = (RoleService) getServletContext().getAttribute("roleService");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        int user_id = (int) session.getAttribute("user_id");
        roleService.addRole(user_id, Constants.PRO);

        Set<String> roles = roleService.getRoles(user_id);
        session.setAttribute("roles", roles);

        resp.sendRedirect("html/notification.html");
    }
}