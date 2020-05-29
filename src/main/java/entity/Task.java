package entity;

import java.sql.Timestamp;
import java.util.Objects;

public class Task {

    private Long id;
    private Long userId;
    private Timestamp created;
    private Boolean isDone;
    private String description;
    private Timestamp deadline;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Timestamp getCreated() {
        return created;
    }

    public void setCreated(Timestamp created) {
        this.created = created;
    }

    public Boolean getIsDone() {
        return isDone;
    }

    public void setIsDone(Boolean done) {
        isDone = done;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Timestamp getDeadline() {
        return deadline;
    }

    public void setDeadline(Timestamp deadLine) {
        this.deadline = deadLine;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Task)) return false;
        Task task = (Task) o;
        return Objects.equals(id, task.id) &&
                Objects.equals(userId, task.userId) &&
                Objects.equals(created, task.created) &&
                Objects.equals(isDone, task.isDone) &&
                Objects.equals(description, task.description) &&
                Objects.equals(deadline, task.deadline);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, created, isDone, description, deadline);
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", userId=" + userId +
                ", creation=" + created +
                ", isDone=" + isDone +
                ", description='" + description + '\'' +
                ", deadline=" + deadline +
                '}';
    }
}
