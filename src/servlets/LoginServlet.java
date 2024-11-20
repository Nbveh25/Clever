package servlets;

import dao.UserDAO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import utils.EmailSenderUtil;
import utils.UserUtil;

import java.io.IOException;
import java.util.Random;

@WebServlet(name = "LoginServlet", urlPatterns = "/login-servlet")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");

        String message = UserUtil.validateUser(login,password);
        if (message.equals("OK")) {
            HttpSession session = req.getSession();
            UserDAO userDAO = new UserDAO();

            String code = String.valueOf(new Random().nextInt(999999));
            String email = userDAO.getUserEmail(login);

            EmailSenderUtil.sendEmail(code, email);

            session.setAttribute("code", code);
            session.setAttribute("login", login);
            session.setAttribute("code", code);
            session.setAttribute("type_auth", "login");

            session.setMaxInactiveInterval(60); // Устанавливаем время жизни сессии в 1 минуту (60 секунд)

            RequestDispatcher dispatcher = req.getRequestDispatcher("/auth-jsp");
            dispatcher.forward(req, resp);
        } else {
            req.setAttribute("errorMessage", message);
            RequestDispatcher dispatcher = req.getRequestDispatcher("/login-jsp");
            dispatcher.forward(req, resp);
        }
    }
}


