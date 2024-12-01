package filters;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import utils.Constants;

import java.io.IOException;
import java.util.Set;

@WebFilter(filterName = "AuthenticationFilter", urlPatterns = "/*")
public class AuthenticationFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession(false);
        String uri = request.getRequestURI();

        boolean isStaticResource = uri.startsWith("/css/") || uri.startsWith("/js/") || uri.startsWith("/img/");

        boolean isLoggedIn = (session != null && session.getAttribute("roles") != null);
        Set<String> roles = isLoggedIn ? (Set<String>) session.getAttribute("roles") : null;
        boolean isUser = isLoggedIn && (roles != null && roles.contains(Constants.SIMPLE));
        boolean isLoginRequest = uri.contains("login") || uri.contains("auth");
        boolean isRegistrationRequest = uri.contains("register");
        boolean isIndex = uri.equals("/") || uri.isEmpty();

        if (!isUser && !isLoginRequest && !isRegistrationRequest && !isStaticResource && !isIndex) {
            response.sendRedirect("/login-jsp");
        } else {
            filterChain.doFilter(request, response);
        }
    }
}