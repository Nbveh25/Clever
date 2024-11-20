package servlets;

import dao.RoleDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet(name = "UpgradeServlet", urlPatterns = "/upgrade-servlet")
public class UpgradeServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        RoleDAO roleDAO = new RoleDAO();

        int user_id = (int) session.getAttribute("user_id");
        roleDAO.updateRoleForUser(user_id);
    }
}
