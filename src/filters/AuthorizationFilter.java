package filters;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import utils.Constants;

import java.io.IOException;
import java.util.Set;

@WebFilter(filterName = "AuthorizationFilter", urlPatterns = "/*")
public class AuthorizationFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        String uri = ((HttpServletRequest) servletRequest).getRequestURI();
        HttpSession session = ((HttpServletRequest) servletRequest).getSession(false);

        if (session != null) {
            Set<String> roles = (Set<String>) session.getAttribute("roles");
            if (uri.contains("create") && (roles == null || !roles.contains(Constants.PRO))) {
                servletRequest.getRequestDispatcher("/main-jsp").forward(servletRequest, servletResponse);
                return;
            }
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }
}