package controller.user;

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
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet("/user/tasks")
public class TasksController extends HttpServlet {

    private static final String TASKS_ATTRIBUTE = "tasks";
    private static final String HOME_PAGE_PATH = "/user/tasks.jsp";
    private static final Logger LOGGER = Logger.getLogger(TasksController.class.getName());
    private final TasksService tasksService = new TasksService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            var tasks = tasksService.getTasks(SessionUtils.getUserId(req.getSession()));
            req.setAttribute(TASKS_ATTRIBUTE, tasks);
            req.getRequestDispatcher(HOME_PAGE_PATH).forward(req, resp);
        } catch (SQLException | NamingException e) {
            LOGGER.log(Level.INFO, e.getMessage());
        }
    }
}
