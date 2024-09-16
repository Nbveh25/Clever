package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.User;
import model.UserDAO;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/user-servlet")
public class UserServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private final UserDAO userDAO = new UserDAO();

    public UserServlet() {
        super();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Получение параметров из запроса
        String login = req.getParameter("login");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String repassword = req.getParameter("repassword");

        if (validate(login, email, password, repassword)) {
            User user = new User(login, email, password);
            UserDAO userDAO = new UserDAO();

            try {
                userDAO.registerUser(user);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private boolean validate(String login, String email, String password, String repassword) {
        return (login != null && email != null && password != null && repassword != null)
                && password.equals(repassword);
    }
}
