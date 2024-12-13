package servlets;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import services.RoleService;
import services.UserService;
import utils.Constants;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Set;

@WebServlet(name = "UserServlet", urlPatterns = "/user-servlet")
public class UserServlet extends HttpServlet {
    private UserService userService;
    private RoleService roleService;

    @Override
    public void init() throws ServletException {
        userService = (UserService) getServletContext().getAttribute("userService");
        roleService = (RoleService) getServletContext().getAttribute("roleService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var iter = req.getSession().getAttributeNames().asIterator();
        iter.forEachRemaining(i -> {
            System.out.println(i);
            System.out.println(req.getSession().getAttribute(i));
        });
        getServletContext().getRequestDispatcher("/WEB-INF/views/personal_account.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        int user_id = (int) session.getAttribute("user_id");
        roleService.addRole(user_id, Constants.PRO);

        Set<String> roles = roleService.getRoles(user_id);
        session.setAttribute("roles", roles);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        int user_id = (int) session.getAttribute("user_id");

        JsonObject jsonObject = JsonParser.parseReader(new InputStreamReader(req.getInputStream())).getAsJsonObject();
        String newLogin = jsonObject.get("newLogin").getAsString();

        userService.updateLogin(user_id, newLogin);

        session.setAttribute("login", newLogin);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        int user_id = (int) session.getAttribute("user_id");
        userService.deleteUser(user_id);

        Cookie[] cookies = req.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                cookie.setValue("");
                cookie.setPath("/");
                cookie.setMaxAge(0);
                resp.addCookie(cookie);
            }
        }

        session.invalidate();
    }
}
