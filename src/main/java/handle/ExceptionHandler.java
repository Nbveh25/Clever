package handle;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "ExceptionHandler", urlPatterns = "/handle")
public class ExceptionHandler extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        handle(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        handle(req, resp);
    }

    private void handle(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Throwable throwable = (Throwable) req.getAttribute("jakarta.servlet.error.exception");
        Integer code = (Integer) req.getAttribute("jakarta.servlet.error.status_code");
        String uri = (String) req.getAttribute("jakarta.servlet.error.request_uri");

        req.setAttribute("statusCode", code);
        req.setAttribute("uri", uri == null ? "" : uri);
        if (code == 500) {
            req.setAttribute("message", throwable.getMessage());
        }

        resp.sendRedirect("/WEB-INF/views/exception.jsp");
    }
}
