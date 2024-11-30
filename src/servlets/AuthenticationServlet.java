package servlets;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import services.UserService;
import services.impl.UserServiceImpl;
import utils.Constants;

import java.io.IOException;

@WebServlet(name = "AuthServlet", urlPatterns = "/auth-servlet")
public class AuthenticationServlet extends HttpServlet {
    private final UserService userService = new UserServiceImpl();

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

        if (input_code.equals(code)) {
            switch (type_auth) {
                case Constants.LOGIN: {

                    session.setMaxInactiveInterval(60 * 60);
                    session.setAttribute("role", Constants.SIMPLE);
                    session.setAttribute("email", email);
                    session.setAttribute("user_id", userService.getIdByLogin(login));

                    req.getRequestDispatcher("/main-jsp").forward(req, resp);

                    break;
                }
                case Constants.REGISTER: {

                    userService.registerUser(login, email, password );

                    req.getRequestDispatcher("html/congrat.html").forward(req, resp);

                    break;
                }
                case Constants.FORGOT_PASSWORD: {

                    userService.updateUserPassword(email, password);

                    req.getRequestDispatcher("html/password_updated.html").forward(req, resp);

                    break;
                }
            }
        } else {

            req.getRequestDispatcher("/auth-jsp").forward(req, resp);

        }
    }
}