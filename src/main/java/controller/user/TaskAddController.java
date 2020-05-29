package controller.user;

import exception.ValidationException;
import service.user.TasksService;
import utils.SessionUtils;

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

@WebServlet("/user/taskAdd")
public class TaskAddController extends HttpServlet {

    private static final String HOME_PAGE_PATH = "/user/tasks.jsp";
    public static final String VALIDATION_ERROR_ATTRIBUTE = "validationError";
    private static final String TASKS_ATTRIBUTE = "tasks";
    private static final String DESCRIPTION_PARAMETER = "description";
    private static final String DEADLINE_PARAMETER = "deadline";
    private static final String ERROR_MESSAGE = "Got error while adding new task";
    private final TasksService tasksService = new TasksService();
    private static final Logger LOGGER = Logger.getLogger(TaskAddController.class.getName());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            addTask(req, resp);
        } catch (SQLException | NamingException | ServletException e) {
            LOGGER.log(Level.WARNING, ERROR_MESSAGE, e);
        }
    }

    private void addTask(HttpServletRequest req, HttpServletResponse resp) throws IOException, SQLException, NamingException, ServletException {
        Long userId = SessionUtils.getUserId(req.getSession());
        String taskDescription = req.getParameter(DESCRIPTION_PARAMETER);
        try {
            Timestamp taskDeadline = Timestamp.valueOf(req.getParameter(DEADLINE_PARAMETER));
            tasksService.addTask(userId, taskDescription, taskDeadline);
            req.setAttribute(TASKS_ATTRIBUTE, tasksService.getTasks(userId));
            req.getRequestDispatcher(HOME_PAGE_PATH).forward(req, resp);
        } catch (ValidationException | IllegalArgumentException e) {
            req.setAttribute(VALIDATION_ERROR_ATTRIBUTE, e.getMessage());
            req.setAttribute(TASKS_ATTRIBUTE, tasksService.getTasks(userId));
            req.getRequestDispatcher(HOME_PAGE_PATH).forward(req, resp);
        }
    }
}
