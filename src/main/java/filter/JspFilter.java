package filter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

@WebFilter("*.jsp")
public class JspFilter implements Filter {

    private static final String NOT_FOUND_PAGE_PATH = "/pagesForFilters/notFoundPage.jsp";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse
            , FilterChain filterChain) throws IOException, ServletException {
        servletRequest.getRequestDispatcher(NOT_FOUND_PAGE_PATH).forward(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
