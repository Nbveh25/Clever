package logic;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;


public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String userEmail = req.getParameter("user_email");
        String userPass = req.getParameter("user_pass");

        resp.setContentType("text/html");
        resp.getWriter().println("<h1>Email: " + userEmail + "</h1>");
        resp.getWriter().println("<h1>Password: " + userPass + "</h1>");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect("/jsp/Login.jsp");
    }
}