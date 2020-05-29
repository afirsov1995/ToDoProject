package controller.root;

import exception.ValidationException;
import service.root.LoginService;
import service.root.RegistrationService;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import static utils.SessionUtils.LOGIN_ATTRIBUTE;

@WebServlet("/root/registration")
public class RegistrationController extends HttpServlet {

    public static final String VALIDATION_ERROR_ATTRIBUTE = "validationError";
    private static final String LOGIN_PARAMETER = "login";
    private static final String PASSWORD_PARAMETER = "password";
    private static final String FIO_PARAMETER = "fio";
    private static final String REGISTRATION_PAGE_PATH = "/root/registration.jsp";
    private static final String HOME_PAGE_PATH = "/user/tasks.jsp";
    private static final Logger LOGGER = Logger.getLogger(RegistrationController.class.getName());
    private final LoginService loginService = new LoginService();
    private final RegistrationService registrationService = new RegistrationService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(REGISTRATION_PAGE_PATH).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var login = req.getParameter(LOGIN_PARAMETER);
        var password = req.getParameter(PASSWORD_PARAMETER);
        var fio = req.getParameter(FIO_PARAMETER);
        try {
            registrationService.registerUser(login, password, fio);
            req.getSession().setAttribute(LOGIN_ATTRIBUTE, loginService.loginUser(login, password));
            req.getRequestDispatcher(HOME_PAGE_PATH).forward(req, resp);
        } catch (ValidationException e) {
            req.setAttribute(VALIDATION_ERROR_ATTRIBUTE, e.getMessage());
            req.getRequestDispatcher(REGISTRATION_PAGE_PATH).forward(req, resp);
        } catch (SQLException | NamingException e) {
            LOGGER.log(Level.INFO, e.getMessage());
        }
    }

}
