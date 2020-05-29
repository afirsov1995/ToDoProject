package controller.user;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static utils.SessionUtils.LOGIN_ATTRIBUTE;

@WebServlet("/user/logout")
public class LogoutController extends HttpServlet {

    private static final String START_PAGE_PATH = "/root/startPage.jsp";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getSession().removeAttribute(LOGIN_ATTRIBUTE);
        req.getRequestDispatcher(START_PAGE_PATH).forward(req, resp);
    }

}
