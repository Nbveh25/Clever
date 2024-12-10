package servlets;

import dto.UserDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import services.RoleService;
import services.UserService;
import utils.Constants;
import utils.PasswordUtil;

import java.io.IOException;
import java.util.Set;

@WebServlet(name = "AuthServlet", urlPatterns = "/auth-servlet")
public class AuthenticationServlet extends HttpServlet {
    private UserService userService;
    private RoleService roleService;

    @Override
    public void init() throws ServletException {
        userService = (UserService) getServletContext().getAttribute("userService");
        roleService = (RoleService) getServletContext().getAttribute("roleService");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);

        if (session == null) {
            req.getRequestDispatcher("/login-jsp").forward(req, resp);
            return;
        }

        String login = (String) session.getAttribute("login");
        String email = (String) session.getAttribute("email");
        String password = (String) session.getAttribute("password");
        String code = (String) session.getAttribute("code");
        String type_auth = (String) session.getAttribute("type_auth");
        String input_code = req.getParameter("input_code");

        UserDTO userDTO = new UserDTO(login, email, PasswordUtil.encrypt(password));

        if (input_code.equals(code)) {
            switch (type_auth) {
                case Constants.LOGIN: {
                    session.setMaxInactiveInterval(60 * 60);

                    int user_id = userService.getIdByLogin(userDTO.getLogin());
                    Set<String> roles = roleService.getRoles(user_id);
                    String icon_url = userService.getProfileIconUrl(user_id);

                    session.setAttribute("email", email);
                    session.setAttribute("user_id", user_id);
                    session.setAttribute("roles", roles);
                    session.setAttribute("icon_url", icon_url);

                    req.getRequestDispatcher("/main-jsp").forward(req, resp);
                    break;
                }
                case Constants.REGISTER: {
                    userService.registerUser(userDTO);
                    roleService.addRole(userService.getIdByLogin(login), Constants.SIMPLE);

                    req.getRequestDispatcher("/congrat-jsp").forward(req, resp);
                    break;
                }
                case Constants.FORGOT_PASSWORD: {
                    userService.updateUserPassword(email, password);

                    req.getRequestDispatcher("/password-updated-jsp").forward(req, resp);
                    break;
                }
            }
        } else {
            req.getRequestDispatcher("/auth-jsp").forward(req, resp);
        }
    }
}