package servlets;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import services.EmailSenderService;
import dao.UserDAO;
import dto.Utils;

import java.io.IOException;

@WebServlet("/login-servlet")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        String login = req.getParameter("login");
        String password = req.getParameter("password");
        String message;
        UserDAO userDAO = new UserDAO();

        if (userDAO.containsUser(login, "login") == 1) {
            message = "Пользователь с таким логином отсутствует";
            req.setAttribute("errorMessage", message);
            RequestDispatcher dispatcher = req.getRequestDispatcher("jsp/login.jsp");
            dispatcher.forward(req, resp);
        } else if (userDAO.containsUser(password, "password") == 1) {
            message = "Неверный пароль";
            req.setAttribute("errorMessage", message);
            RequestDispatcher dispatcher = req.getRequestDispatcher("jsp/login.jsp");
            dispatcher.forward(req, resp);
        } else {
            EmailSenderService emailSenderService = new EmailSenderService();
            String code = Utils.generateCode();
            String email = userDAO.getUserEmail(login);

            emailSenderService.sendEmail(code, email);

            session.setAttribute("code", code);
            session.setAttribute("login", login);
            session.setAttribute("password", password);
            session.setAttribute("email", email);
            session.setAttribute("type_auth", "login");

            RequestDispatcher dispatcher = req.getRequestDispatcher("/auth-jsp");
            dispatcher.forward(req, resp);
        }
    }
}