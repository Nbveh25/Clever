package servlets;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.User;
import model.UserDAO;

import java.io.IOException;


@WebServlet("/register-servlet")
public class RegisterServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public RegisterServlet() {
        super();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String login = req.getParameter("login");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String repassword = req.getParameter("repassword");

        String message;

        UserDAO userDAO = new UserDAO();

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
            userDAO.registerUser(new User(login, email, password));
        }
    }

}
