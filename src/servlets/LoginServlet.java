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

import java.io.IOException;
import java.util.Random;

@WebServlet(name = "LoginServlet", urlPatterns = "/login-servlet")
public class LoginServlet extends HttpServlet {
    private UserService userService;

    @Override
    public void init() throws ServletException {
        super.init();
        userService = (UserService) getServletContext().getAttribute("userService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cookie[] cookies = req.getCookies();
        String login = "";
        String password = "";

        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("login".equals(cookie.getName())) {
                    login = cookie.getValue();
                } else if ("password".equals(cookie.getName())) {
                    password = cookie.getValue();
                }
            }
        }

        req.setAttribute("login", login);
        req.setAttribute("password", password);

        req.getRequestDispatcher("/login-jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        String rememberMe = req.getParameter("rememberMe"); // Получаем значение чекбокса

        if (userService.userExists(login, password)) {
            HttpSession session = req.getSession();

            String code = String.format("%06d", new Random().nextInt(1000000));
            String email = userService.getUserEmail(login);

            EmailSenderUtil.sendVerificationCode(code, email);

            session.setAttribute("code", code);
            session.setAttribute("login", login);
            session.setAttribute("email", email);
            session.setAttribute("type_auth", Constants.LOGIN);

            session.setMaxInactiveInterval(60); // Устанавливаем время жизни сессии в 1 минуту (60 секунд)

            if (rememberMe != null) {
                Cookie loginCookie = new Cookie("login", login);
                Cookie passwordCookie = new Cookie("password", password);
                loginCookie.setMaxAge(60 * 60 * 24); // Устанавливаем время жизни cookie в 1 день
                passwordCookie.setMaxAge(60 * 60 * 24); // Устанавливаем время жизни cookie в 1 день
                resp.addCookie(loginCookie); // Добавляем куки в ответ
                resp.addCookie(passwordCookie); // Добавляем куки в ответ
            }

            req.getRequestDispatcher("/auth-jsp").forward(req, resp);
        } else {
            req.getRequestDispatcher("/login-jsp").forward(req, resp);
        }
    }
}