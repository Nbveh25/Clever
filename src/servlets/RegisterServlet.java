package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import services.EmailService;
import services.UserService;
import services.impl.EmailServiceImpl;
import services.impl.UserServiceImpl;
import utils.Constants;

import java.io.IOException;
import java.util.Random;

@WebServlet(name = "RegisterServlet", urlPatterns = "/register-servlet")
public class RegisterServlet extends HttpServlet {
    private final UserService userService = new UserServiceImpl();
    private final EmailService emailService = new EmailServiceImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        String login = req.getParameter("login");
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        if (!userService.userExists(login, email, password)) {

            String code = String.valueOf(new Random().nextInt(999999));
            emailService.sendVerificationCode(code, email);

            session.setMaxInactiveInterval(60);
            session.setAttribute("login", login);
            session.setAttribute("email", email);
            session.setAttribute("password", password);
            session.setAttribute("code", code);
            session.setAttribute("type_auth", Constants.REGISTER);

            req.getRequestDispatcher("/auth-jsp").forward(req, resp);

        } else {

            req.getRequestDispatcher("/register-jsp").forward(req, resp);

        }
    }
}