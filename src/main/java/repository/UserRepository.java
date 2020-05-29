package repository;

import entity.User;

import javax.naming.NamingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserRepository extends AbstractRepository {

    private static final String GET_BY_ID_QUERY = "select * from \"user\" where id = ?;";
    private static final String GET_BY_LOGIN_QUERY = "select * from \"user\" where login = ?;";
    private static final String INSERT_QUERY = "INSERT INTO \"user\" (login, password, fio) VALUES (?, ?, ?);";
    private static final String UPDATE_QUERY = "UPDATE \"user\" SET login = ?, password = ?, fio = ? WHERE id = ?;";
    private static final String DELETE_QUERY = "DELETE FROM \"user\" WHERE id = ?;";
    private static final String GET_ALL_QUERY = "select * from \"user\";";
    private static final String COLUMN_LABEL_ID = "id";
    private static final String COLUMN_LABEL_LOGIN = "login";
    private static final String COLUMN_LABEL_PASSWORD = "password";
    private static final String COLUMN_LABEL_FIO = "fio";
    private static final int FIRST_PARAMETER_INDEX = 1;
    private static final int SECOND_PARAMETER_INDEX = 2;
    private static final int THIRD_PARAMETER_INDEX = 3;
    private static final int FOURTH_PARAMETER_INDEX = 4;
    private static final int FIRST_COLUMN_INDEX = 1;

    public User getById(Long id) throws SQLException, NamingException {
        try (Connection connection = getConnection()) {
            PreparedStatement ps = connection.prepareStatement(GET_BY_ID_QUERY);
            ps.setLong(FIRST_PARAMETER_INDEX, id);
            ResultSet rs = ps.executeQuery();
            if (!rs.next()) {
                return null;
            }
            return getUser(rs);
        }
    }

    public User getByLogin(String login) throws SQLException, NamingException {
        try (Connection connection = getConnection()) {
            PreparedStatement ps = connection.prepareStatement(GET_BY_LOGIN_QUERY);
            ps.setString(FIRST_PARAMETER_INDEX, login);
            ResultSet rs = ps.executeQuery();
            if (!rs.next()) {
                return null;
            }
            return getUser(rs);
        }
    }

    public Long save(User user) throws SQLException, NamingException {
        try (Connection connection = getConnection()) {
            PreparedStatement ps = connection.prepareStatement(INSERT_QUERY, Statement.RETURN_GENERATED_KEYS);
            setParametersToQuery(user, ps);
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            rs.next();
            user.setId(rs.getLong(FIRST_COLUMN_INDEX));
            return user.getId();
        }
    }

    public void update(User user) throws SQLException, NamingException {
        try (Connection connection = getConnection()) {
            PreparedStatement ps = connection.prepareStatement(UPDATE_QUERY);
            setParametersToQuery(user, ps);
            ps.setLong(FOURTH_PARAMETER_INDEX, user.getId());
            ps.execute();
        }
    }

    public void delete(User user) throws SQLException, NamingException {
        try (Connection connection = getConnection()) {
            PreparedStatement ps = connection.prepareStatement(DELETE_QUERY);
            ps.setLong(FIRST_PARAMETER_INDEX, user.getId());
            ps.execute();
        }
    }

    public List<User> getAll() throws SQLException, NamingException {
        try (Connection connection = getConnection()) {
            PreparedStatement ps = getConnection().prepareStatement(GET_ALL_QUERY);
            ResultSet rs = ps.executeQuery();
            List<User> users = new ArrayList<>();
            if (!rs.next()) {
                return null;
            }
            do {
                users.add(getUser(rs));
            }
            while (rs.next());
            return users;
        }
    }

    private User getUser(ResultSet rs) throws SQLException {
        User user = new User();
        user.setId(rs.getLong(COLUMN_LABEL_ID));
        user.setLogin(rs.getString(COLUMN_LABEL_LOGIN));
        user.setPassword(rs.getString(COLUMN_LABEL_PASSWORD));
        user.setFio(rs.getString(COLUMN_LABEL_FIO));
        return user;
    }

    private void setParametersToQuery(User user, PreparedStatement ps) throws SQLException {
        ps.setString(FIRST_PARAMETER_INDEX, user.getLogin());
        ps.setString(SECOND_PARAMETER_INDEX, user.getPassword());
        ps.setString(THIRD_PARAMETER_INDEX, user.getFio());
    }


}
