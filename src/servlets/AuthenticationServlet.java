package servlets;

import dao.UserDAO;
import dto.User;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

@WebServlet(name = "AuthServlet", urlPatterns = "/auth-servlet")
public class AuthenticationServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession(false); // Получаем текущую сессию, если она существует
        if (session == null) {
            // Сессия истекла или не существует
            req.setAttribute("errorMessage", "Сессия истекла. Пожалуйста, войдите снова.");
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            RequestDispatcher dispatcher = req.getRequestDispatcher("/login-jsp");
            dispatcher.forward(req, resp);
            return;
        }

        String login = (String) session.getAttribute("login");
        String password = (String) session.getAttribute("password");
        String code = (String) session.getAttribute("code");
        String type_auth = (String) session.getAttribute("type_auth");

        String input_code = req.getParameter("input_code");

        if (input_code.equals(code)) {

            UserDAO userDAO = new UserDAO();

            if (type_auth.equals("login")) {

                session.setMaxInactiveInterval(60 * 60);
                session.setAttribute("role", "user");
                session.setAttribute("login", login);
                session.setAttribute("user_id", userDAO.getIdByLogin(login));

                RequestDispatcher dispatcher = req.getRequestDispatcher("/main-jsp");
                dispatcher.forward(req, resp);
            } else if (type_auth.equals("register")) {
                String email = (String) session.getAttribute("email");
                userDAO.registerUser(new User(login, email, password));
                
                RequestDispatcher dispatcher = req.getRequestDispatcher("html/congrat.html");
                dispatcher.forward(req, resp);
            } else if (type_auth.equals("forgot_password")) {
                String email = (String) session.getAttribute("email");
                userDAO.updateUserPassword(email, password);

                RequestDispatcher dispatcher = req.getRequestDispatcher("html/password_updated.html");
                dispatcher.forward(req, resp);
            }

        } else {
            req.setAttribute("errorMessage", "Неверный код.");
            RequestDispatcher dispatcher = req.getRequestDispatcher("/auth-jsp");
            dispatcher.forward(req, resp);
        }
    }
}