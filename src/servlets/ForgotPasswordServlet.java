package servlets;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import services.EmailSenderService;
import dto.Utils;

import java.io.IOException;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@WebServlet("/forgot-pass-servlet")
public class ForgotPasswordServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

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

        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String repassword = req.getParameter("repassword");

        if (password.equals(repassword)) {
            session.setMaxInactiveInterval(60);
            session.setAttribute("email", email);
            session.setAttribute("password", password);

            String code = String.valueOf(new Random().nextInt(999999));

            EmailSenderService.sendEmail(code ,email);

            session.setAttribute("type_auth", "forgot_password");
            session.setAttribute("code", code);

            RequestDispatcher dispatcher = req.getRequestDispatcher("/auth-jsp");
            dispatcher.forward(req, resp);
        } else {
            // Вывести сообщение о несовпадающих паролях
            String message = "Пароли не совпадают";
            req.setAttribute("errorMessage", message);
            RequestDispatcher dispatcher = req.getRequestDispatcher("/forgot-pass-jsp");
            dispatcher.forward(req, resp);
        }
    }
}
