package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import utils.Constants;
import utils.EmailSenderUtil;

import java.io.IOException;
import java.util.Random;

@WebServlet(name = "ForgotPassServlet", urlPatterns = "/forgot-pass-servlet")
public class ForgotPasswordServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/WEB-INF/views/forgot_password.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        if (session == null) {
            resp.sendRedirect("/login-servlet");
            return;
        }

        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String repassword = req.getParameter("repassword");

        if (password.equals(repassword)) {
            session.setMaxInactiveInterval(60);
            session.setAttribute("email", email);
            session.setAttribute("password", password);

            String code = String.valueOf(new Random().nextInt(999999));

            EmailSenderUtil.sendVerificationCode(code, email);

            session.setAttribute("type_auth", Constants.FORGOT_PASSWORD);
            session.setAttribute("code", code);

            getServletContext().getRequestDispatcher("/auth-servlet").forward(req, resp);
        } else {
            String message = "Пароли не совпадают";
            req.setAttribute("errorMessage", message);
            resp.sendRedirect("/forgot-pass-servlet");
        }
    }
}