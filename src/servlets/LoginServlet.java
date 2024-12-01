package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import services.EmailService;
import services.RoleService;
import services.UserService;
import services.impl.EmailServiceImpl;
import services.impl.RoleServiceImpl;
import services.impl.UserServiceImpl;
import utils.Constants;

import java.io.IOException;
import java.util.Random;
import java.util.Set;

@WebServlet(name = "LoginServlet", urlPatterns = "/login-servlet")
public class LoginServlet extends HttpServlet {
    private final UserService userService = new UserServiceImpl();
    private final EmailService emailService = new EmailServiceImpl();
    private final RoleService roleService = new RoleServiceImpl();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");

        if (userService.userExists(login, password)) {
            HttpSession session = req.getSession();

            String code = String.format("%06d", new Random().nextInt(1000000));
            String email = userService.getUserEmail(login);

            emailService.sendVerificationCode(code, email);

            session.setAttribute("code", code);
            session.setAttribute("login", login);
            session.setAttribute("email", email);
            session.setAttribute("code", code);
            session.setAttribute("type_auth", Constants.LOGIN);

            session.setMaxInactiveInterval(60); // Устанавливаем время жизни сессии в 1 минуту (60 секунд)

            req.getRequestDispatcher("/auth-jsp").forward(req, resp);
        } else {
            req.getRequestDispatcher("/login-jsp").forward(req, resp);
        }
    }
}


