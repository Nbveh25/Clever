package servlets;

import dto.UserDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import services.UserService;
import utils.Constants;
import utils.EmailSenderUtil;

import java.io.IOException;
import java.util.Random;

@WebServlet(name = "RegisterServlet", urlPatterns = "/register-servlet")
public class RegisterServlet extends HttpServlet {
    private UserService userService;

    @Override
    public void init() throws ServletException {
        userService = (UserService) getServletContext().getAttribute("userService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/WEB-INF/views/register.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        String login = req.getParameter("login");
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        UserDTO userDTO = new UserDTO(login, email, password);

        if (!userService.userExists(userDTO)) {

            String code = String.valueOf(new Random().nextInt(999999));
            EmailSenderUtil.sendVerificationCode(code, email);

            session.setMaxInactiveInterval(60);
            session.setAttribute("login", login);
            session.setAttribute("email", email);
            session.setAttribute("password", password);
            session.setAttribute("code", code);
            session.setAttribute("type_auth", Constants.REGISTER);

            resp.sendRedirect("/auth-servlet");

        } else {
            req.getRequestDispatcher("/register-servlet").forward(req, resp);
        }
    }
}