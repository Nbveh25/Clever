package servlets;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import dto.User;
import dao.UserDAO;
import java.io.IOException;

@WebServlet(name = "AuthServlet", urlPatterns = "/auth-servlet")
public class AuthenticationServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();

        String code = session.getAttribute("code").toString();
        String input_code = req.getParameter("input_code");

        if (code.equals(input_code)) {
            UserDAO userDAO = new UserDAO();
            String type_auth = session.getAttribute("type_auth").toString();

            if (type_auth.equals("register")) {
                String login = session.getAttribute("login").toString();
                String email = session.getAttribute("email").toString();
                String password = session.getAttribute("password").toString();

                userDAO.registerUser(new User(login, email, password));

                RequestDispatcher dispatcher = req.getRequestDispatcher("html/congrat.html");
                dispatcher.forward(req, resp);
            } else if (type_auth.equals("login")) {
                // переход на страницу
                session.setAttribute("user_id", userDAO.getIdByLogin(session.getAttribute("login").toString()));
                session.setAttribute("login", session.getAttribute("login").toString());
                RequestDispatcher dispatcher = req.getRequestDispatcher("/main-jsp");
                dispatcher.forward(req, resp);
            } else if (type_auth.equals("forgot-password")) {
                // Вызываю функцию изменения пароля в бд
                UserDAO dao = new UserDAO();
                String email = session.getAttribute("email").toString();
                String password = session.getAttribute("password").toString();
                dao.updateUserPassword(email, password);

                RequestDispatcher dispatcher = req.getRequestDispatcher("html/password_updated.html");
                dispatcher.forward(req, resp);
            }

        } else {
            String message = "Неправильный код";
            req.setAttribute("errorMessage", message);
            RequestDispatcher dispatcher = req.getRequestDispatcher("/auth-jsp");
            dispatcher.forward(req, resp);
        }
    }
}