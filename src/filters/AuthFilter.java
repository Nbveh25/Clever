package filters;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebFilter(filterName = "AuthFilter", urlPatterns = "/*")
public class AuthFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession(false);
        String uri = request.getRequestURI();

        // Проверяем, является ли запрос к статическим ресурсам
        boolean isStaticResource = uri.startsWith("/css/") || uri.startsWith("/js/") || uri.startsWith("/img/");

        // Проверяем, существует ли сессия и есть ли у пользователя роль
        boolean isLoggedIn = (session != null && session.getAttribute("role") != null);
        boolean isUser = isLoggedIn && (session.getAttribute("role").equals("SIMPLE") || session.getAttribute("role").equals("PRO"));
        boolean isLoginRequest = uri.contains("login") || uri.contains("auth");
        boolean isRegistrationRequest = uri.contains("register");
        boolean isIndex = !uri.endsWith("/");

        if (!isUser && !isLoginRequest && !isRegistrationRequest && !isStaticResource && isIndex) {
            // Если сессия не существует или роль не "user", перенаправляем на страницу входа
            response.sendRedirect("/login-jsp");
        } else {
            // Если пользователь аутентифицирован или запрашивает страницу входа, продолжаем цепочку фильтров
            filterChain.doFilter(request, response);
        }
    }
}