package filter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static utils.SessionUtils.LOGIN_ATTRIBUTE;

@WebFilter("/root/*")
public class UserLoggedFilter implements Filter {

    private static final String HOME_PAGE_PATH = "/ToDoProject_war/user/tasks";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
        if (isUserLogged(httpServletRequest)) {
            httpServletResponse.sendRedirect(HOME_PAGE_PATH);
            return;
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }

    private boolean isUserLogged(HttpServletRequest httpServletRequest) {
        return httpServletRequest.getSession().getAttribute(LOGIN_ATTRIBUTE) != null;
    }
}
