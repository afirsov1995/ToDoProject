package controller.user;

import controller.root.RegistrationController;
import service.user.TasksService;

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

@WebServlet("/user/taskComplete")
public class TaskCompleteController extends HttpServlet {

    private static final String HOME_PAGE_PATH = "/user/tasks";
    private static final String TASK_ID_PARAMETER = "taskId";
    private static final Logger LOGGER = Logger.getLogger(TaskCompleteController.class.getName());
    private final TasksService tasksService = new TasksService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            tasksService.completeTask(Long.valueOf(req.getParameter(TASK_ID_PARAMETER)));
            resp.sendRedirect(req.getContextPath() + HOME_PAGE_PATH);
        } catch (SQLException | NamingException e) {
            LOGGER.log(Level.INFO, e.getMessage());
        }
    }
}
