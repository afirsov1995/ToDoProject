package controller.user;

import dto.UserDTO;
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

@WebServlet("/user/taskEdit")
public class TaskEditController extends HttpServlet {

    public static final String VALIDATION_ERROR_ATTRIBUTE = "validationError";
    private static final String HOME_PAGE_PATH = "/user/tasks.jsp";
    private static final String TASK_EDIT_PAGE_PATH = "/user/taskEdit.jsp";
    private static final String TASKS_ATTRIBUTE = "tasks";
    private static final String LOGIN_ATTRIBUTE = "login";
    private static final String TASK_ATTRIBUTE = "task";
    private static final String TASK_ID_PARAMETER = "taskId";
    private static final String DESCRIPTION_PARAMETER = "description";
    private static final String DEADLINE_PARAMETER = "deadline";
    private static final Logger LOGGER = Logger.getLogger(TaskEditController.class.getName());
    private final TasksService tasksService = new TasksService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            req.setAttribute(TASK_ATTRIBUTE, tasksService.getTask(Long.valueOf(req.getParameter(TASK_ID_PARAMETER))));
            req.getRequestDispatcher(TASK_EDIT_PAGE_PATH).forward(req, resp);
        } catch (SQLException | NamingException e) {
            LOGGER.log(Level.INFO, e.getMessage());
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            editTask(req, resp);
        } catch (SQLException | NamingException e) {
            LOGGER.log(Level.INFO, e.getMessage());
        }
    }

    private void editTask(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, SQLException, NamingException {
        var taskId = Long.valueOf(req.getParameter(TASK_ID_PARAMETER));
        var description = req.getParameter(DESCRIPTION_PARAMETER);
        var deadline = Timestamp.valueOf(req.getParameter(DEADLINE_PARAMETER));
        try {
            tasksService.editTask(taskId, description, deadline);
            req.setAttribute(TASKS_ATTRIBUTE, tasksService.getTasks(SessionUtils.getUserId(req.getSession())));
            req.getRequestDispatcher(HOME_PAGE_PATH).forward(req, resp);
        } catch (ValidationException | IllegalArgumentException e) {
            req.setAttribute(TASK_ATTRIBUTE, tasksService.getTask(taskId));
            req.setAttribute(VALIDATION_ERROR_ATTRIBUTE, e.getMessage());
            req.getRequestDispatcher(TASK_EDIT_PAGE_PATH).forward(req, resp);
        }
    }
}
