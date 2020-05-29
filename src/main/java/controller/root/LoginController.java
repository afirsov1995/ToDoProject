package controller.root;

import exception.ValidationException;
import service.root.LoginService;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.logging.Level;
import java.util.logging.Logger;

import static utils.SessionUtils.LOGIN_ATTRIBUTE;

@WebServlet("/root/login")
public class LoginController extends HttpServlet {

    public static final String VALIDATION_ERROR_ATTRIBUTE = "validationError";
    private static final String LOGIN_PAGE_PATH = "/root/login.jsp";
    private static final String HOME_PAGE_PATH = "/user/tasks";
    private static final String LOGIN_PARAMETER = "login";
    private static final String PASSWORD_PARAMETER = "password";
    private static final Logger LOGGER = Logger.getLogger(LoginController.class.getName());
    private final LoginService loginService = new LoginService();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(LOGIN_PAGE_PATH).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            req.getSession().setAttribute(LOGIN_ATTRIBUTE, loginService.loginUser(req.getParameter(LOGIN_PARAMETER), req.getParameter(PASSWORD_PARAMETER)));
            resp.sendRedirect(req.getContextPath() + HOME_PAGE_PATH);
        } catch (ValidationException e) {
            req.setAttribute(VALIDATION_ERROR_ATTRIBUTE, e.getMessage());
            req.getRequestDispatcher(LOGIN_PAGE_PATH).forward(req, resp);
        } catch (SQLException | NamingException e) {
            LOGGER.log(Level.INFO, e.getMessage());
        }
    }
}
