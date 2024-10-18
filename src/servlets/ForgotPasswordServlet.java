package servlets;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import services.EmailSenderService;
import model.Utils;

import java.io.IOException;

@WebServlet("/forgot-pass-servlet")
public class ForgotPasswordServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String repassword = req.getParameter("repassword");

        if (password.equals(repassword)) {
            session.setAttribute("email", email);
            session.setAttribute("password", password);

            EmailSenderService emailSenderService = new EmailSenderService();
            String code = Utils.generateCode();

            emailSenderService.sendEmail(code ,email);

            session.setAttribute("type_auth", "forgot-password");
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
