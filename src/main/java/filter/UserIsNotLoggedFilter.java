package filter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter("/user/*")
public class UserIsNotLoggedFilter implements Filter {

    private static final String LOGIN_ATTRIBUTE = "login";
    private static final String LOGIN_PAGE_PATH = "/root/login.jsp";

    @Override
    public void init(FilterConfig filterConfig) {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
        if (isUserLogged(httpServletRequest)) {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }
        httpServletRequest.getRequestDispatcher(LOGIN_PAGE_PATH).forward(httpServletRequest, httpServletResponse);
    }

    @Override
    public void destroy() {

    }

    private boolean isUserLogged(HttpServletRequest httpServletRequest) {
        return httpServletRequest.getSession().getAttribute(LOGIN_ATTRIBUTE) != null;
    }
}
