package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import services.UserService;
import utils.Constants;
import utils.EmailSenderUtil;
import utils.PasswordUtil;

import java.io.IOException;
import java.util.Random;

@WebServlet(name = "LoginServlet", urlPatterns = "/login-servlet")
public class LoginServlet extends HttpServlet {
    private UserService userService;

    @Override
    public void init() throws ServletException {
        userService = (UserService) getServletContext().getAttribute("userService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(getServletContext().getContextPath() + "/login-jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        String rememberMe = req.getParameter("rememberMe");

        if (userService.userExists(login, PasswordUtil.encrypt(password))) {
            HttpSession session = req.getSession();

            String code = String.format("%06d", new Random().nextInt(1000000));
            String email = userService.getUserEmail(login);

            EmailSenderUtil.sendVerificationCode(code, email);

            session.setAttribute("code", code);
            session.setAttribute("login", login);
            session.setAttribute("email", email);
            session.setAttribute("password", password);
            session.setAttribute("type_auth", Constants.LOGIN);

            session.setMaxInactiveInterval(60);

            if (rememberMe != null) {
                Cookie loginCookie = new Cookie("login", login);
                Cookie passwordCookie = new Cookie("password", password);
                loginCookie.setMaxAge(60 * 60 * 24);
                passwordCookie.setMaxAge(60 * 60 * 24);
                resp.addCookie(loginCookie);
                resp.addCookie(passwordCookie);
            }

            req.getRequestDispatcher(getServletContext().getContextPath() + "/auth-jsp").forward(req, resp);
        } else {
            req.getRequestDispatcher(getServletContext().getContextPath() + "/login-jsp").forward(req, resp);
        }
    }
}