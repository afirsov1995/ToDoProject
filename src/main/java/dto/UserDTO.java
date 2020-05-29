package dto;

import java.util.Objects;

public class UserDTO {

    private Long id;
    private String login;
    private String fio;

    public UserDTO(Long id, String login, String fio) {
        this.id = id;
        this.login = login;
        this.fio = fio;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserDTO)) return false;
        UserDTO userDTO = (UserDTO) o;
        return Objects.equals(id, userDTO.id) &&
                Objects.equals(login, userDTO.login) &&
                Objects.equals(fio, userDTO.fio);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, login, fio);
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", fio='" + fio + '\'' +
                '}';
    }
}
