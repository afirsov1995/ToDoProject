package repository;

import entity.Task;

import javax.naming.NamingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class TaskRepository extends AbstractRepository {

    private static final String GET_BY_ID_QUERY = "select * from task where id = ?;";
    private static final String GET_BY_USER_ID_QUERY = "select * from task where user_id = ?;";
    private static final String GET_ALL_QUERY = "select * from task;";
    private static final String INSERT_QUERY = "INSERT INTO task(user_id, created, is_done, description, deadline)" +
            " VALUES (?, ?, ?, ?, ?);";
    private static final String UPDATE_QUERY = "UPDATE task SET user_id = ?, created = ?, is_done = ?," +
            " description = ?, deadline = ? WHERE id = ?;";
    private static final String DELETE_QUERY = "DELETE FROM task WHERE id = ?;";
    private static final int FIRST_PARAMETER_INDEX = 1;
    private static final int SECOND_PARAMETER_INDEX = 2;
    private static final int THIRD_PARAMETER_INDEX = 3;
    private static final int FOURTH_PARAMETER_INDEX = 4;
    private static final int FIFTH_PARAMETER_INDEX = 5;
    private static final int SIXTH_PARAMETER_INDEX = 6;
    private static final int FIRST_COLUMN_INDEX = 1;
    private static final String COLUMN_LABEL_ID = "id";
    private static final String COLUMN_LABEL_USER_ID = "user_id";
    private static final String COLUMN_LABEL_CREATED = "created";
    private static final String COLUMN_LABEL_IS_DONE = "is_done";
    private static final String COLUMN_LABEL_DESCRIPTION = "description";
    private static final String COLUMN_LABEL_DEADLINE = "deadline";

    public Task getById(Long taskId) throws SQLException, NamingException {
        try (Connection connection = getConnection()) {
            PreparedStatement ps = connection.prepareStatement(GET_BY_ID_QUERY);
            ps.setLong(FIRST_PARAMETER_INDEX, taskId);
            ResultSet rs = ps.executeQuery();
            if (!rs.next()) {
                return null;
            }
            return getTask(rs);
        }
    }

    public List<Task> getAllByUserId(Long userId) throws SQLException, NamingException {
        try (Connection connection = getConnection()) {
            PreparedStatement ps = connection.prepareStatement(GET_BY_USER_ID_QUERY);
            ps.setLong(FIRST_PARAMETER_INDEX, userId);
            ResultSet rs = ps.executeQuery();
            return getTasksByResultSet(rs);
        }
    }

    public List<Task> getAll() throws SQLException, NamingException {
        try (Connection connection = getConnection()) {
            PreparedStatement ps = connection.prepareStatement(GET_ALL_QUERY);
            ResultSet rs = ps.executeQuery();
            return getTasksByResultSet(rs);
        }
    }

    public Long save(Task task) throws SQLException, NamingException {
        try (Connection connection = getConnection()) {
            PreparedStatement ps = connection.prepareStatement(INSERT_QUERY, Statement.RETURN_GENERATED_KEYS);
            setParametersToQuery(task, ps);
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            rs.next();
            task.setId(rs.getLong(FIRST_COLUMN_INDEX));
            return task.getId();
        }
    }

    public void update(Task task) throws SQLException, NamingException {
        try (Connection connection = getConnection()) {
            PreparedStatement ps = connection.prepareStatement(UPDATE_QUERY);
            setParametersToQuery(task, ps);
            ps.setLong(SIXTH_PARAMETER_INDEX, task.getId());
            ps.execute();
        }
    }

    public void delete(Task task) throws SQLException, NamingException {
        try (Connection connection = getConnection()) {
            PreparedStatement ps = connection.prepareStatement(DELETE_QUERY);
            ps.setLong(FIRST_PARAMETER_INDEX, task.getId());
            ps.execute();
        }
    }

    private List<Task> getTasksByResultSet(ResultSet rs) throws SQLException {
        List<Task> tasks = new ArrayList<>();
        if (!rs.next()) {
            return tasks;
        }
        do {
            tasks.add(getTask(rs));
        }
        while (rs.next());
        return tasks;
    }

    private Task getTask(ResultSet resultSet) throws SQLException {
        Task task = new Task();
        task.setId(resultSet.getLong(COLUMN_LABEL_ID));
        task.setUserId(resultSet.getLong(COLUMN_LABEL_USER_ID));
        task.setCreated(resultSet.getTimestamp(COLUMN_LABEL_CREATED));
        task.setIsDone(resultSet.getBoolean(COLUMN_LABEL_IS_DONE));
        task.setDescription(resultSet.getString(COLUMN_LABEL_DESCRIPTION));
        task.setDeadline(resultSet.getTimestamp(COLUMN_LABEL_DEADLINE));
        return task;
    }

    private void setParametersToQuery(Task task, PreparedStatement ps) throws SQLException {
        ps.setLong(FIRST_PARAMETER_INDEX, task.getUserId());
        ps.setTimestamp(SECOND_PARAMETER_INDEX, task.getCreated());
        ps.setBoolean(THIRD_PARAMETER_INDEX, task.getIsDone());
        ps.setString(FOURTH_PARAMETER_INDEX, task.getDescription());
        ps.setTimestamp(FIFTH_PARAMETER_INDEX, task.getDeadline());
    }


}
