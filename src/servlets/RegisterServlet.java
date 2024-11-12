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
import java.util.Random;

@WebServlet("/register-servlet")
public class RegisterServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public RegisterServlet() {
        super();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();

        String login = req.getParameter("login");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String repassword = req.getParameter("repassword");

        String message;

        UserDAO userDAO = new UserDAO();

        //TODO (Добавить проверку на простоту пароля и валидацию email)
        if (!(password.equals(repassword))) {
            message = "Пароли не совпадают";
            req.setAttribute("errorMessage", message);
            RequestDispatcher dispatcher = req.getRequestDispatcher("jsp/register.jsp");
            dispatcher.forward(req, resp);
        } else if(userDAO.containsUser(login, "login") == -1) {
            message = "Пользователь с таким логином уже существует";
            req.setAttribute("errorMessage", message);
            RequestDispatcher dispatcher = req.getRequestDispatcher("jsp/register.jsp");
            dispatcher.forward(req, resp);
        } else if (userDAO.containsUser(email, "email") == -1) {
            message = "Пользователь с таким email уже существует";
            req.setAttribute("errorMessage", message);
            RequestDispatcher dispatcher = req.getRequestDispatcher("jsp/register.jsp");
            dispatcher.forward(req, resp);
        } else {
            String code = String.valueOf(new Random().nextInt(999999));

            EmailSenderService.sendEmail(code, email);

            session.setMaxInactiveInterval(60);
            session.setAttribute("login", login);
            session.setAttribute("email", email);
            session.setAttribute("password", password);
            session.setAttribute("code", code);
            session.setAttribute("type_auth", "register");

            RequestDispatcher dispatcher = req.getRequestDispatcher("/auth-jsp");
            dispatcher.forward(req, resp);
        }
    }
}
