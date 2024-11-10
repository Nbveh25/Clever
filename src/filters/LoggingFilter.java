package filters;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.lang.invoke.MethodHandles;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

@WebFilter(filterName = "LoggingFilter", urlPatterns = "/*")
public class LoggingFilter implements Filter {

    private static final Logger LOG =
            LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        ServletContext servletContext = filterConfig.getServletContext();
        servletContext.log("LoggingFilter initialized");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;

        Map<String, String[]> params = httpRequest.getParameterMap();

        if (params != null) {
            String paramStr = params.entrySet().stream().map(
                            e -> e.getKey() + "=" + Arrays.toString(e.getValue()))
                    .collect(Collectors.joining(", ", "{", "}"));
            LOG.info("{} : request params : {}", httpRequest.getRemoteAddr(), paramStr);
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }
}
