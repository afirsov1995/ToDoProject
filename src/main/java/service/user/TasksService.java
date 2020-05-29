package service.user;

import dto.TaskDTO;

import entity.Task;
import exception.ValidationException;
import repository.TaskRepository;
import utils.MessagesUtils;

import javax.naming.NamingException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class TasksService {

    private final TaskRepository taskRepository = new TaskRepository();

    public List<TaskDTO> getTasks(Long userId) throws SQLException, NamingException {
        return taskRepository.getAllByUserId(userId).stream().map(this::toTaskDto).collect(Collectors.toList());
    }

    public TaskDTO getTask(Long taskId) throws SQLException, NamingException {
        return toTaskDto(taskRepository.getById(taskId));
    }

    public void completeTask(Long taskId) throws SQLException, NamingException {
        Task task = taskRepository.getById(taskId);
        task.setIsDone(true);
        taskRepository.update(task);
    }

    public void editTask(Long taskId, String description, Timestamp deadLine) throws SQLException, NamingException {
        Task task = taskRepository.getById(taskId);
        if (task.getDeadline().getTime() > deadLine.getTime()) {
            throw new ValidationException(MessagesUtils.INVALID_DEADLINE_MESSAGE);
        }
        task.setDescription(description);
        task.setDeadline(Timestamp.valueOf(deadLine.toString()));
        taskRepository.update(task);
    }

    public void addTask(Long userId, String description, Timestamp deadline) throws SQLException, NamingException {
        Timestamp timestamp = new Timestamp(new Date().getTime());
        if (timestamp.getTime() > deadline.getTime()){
            throw new ValidationException(MessagesUtils.INVALID_DEADLINE_MESSAGE);
        }
        Task task = new Task();
        task.setUserId(userId);
        task.setCreated(timestamp);
        task.setIsDone(false);
        task.setDescription(description);
        task.setDeadline(deadline);
        taskRepository.save(task);
    }

    private TaskDTO toTaskDto(Task task) {
        TaskDTO taskDTO = new TaskDTO();
        taskDTO.setId(task.getId());
        taskDTO.setUserId(task.getUserId());
        taskDTO.setCreated(task.getCreated());
        taskDTO.setIsDone(task.getIsDone());
        taskDTO.setDescription(task.getDescription());
        taskDTO.setDeadline(task.getDeadline());
        return taskDTO;
    }

}
