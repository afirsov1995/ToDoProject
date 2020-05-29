package repository;

import utils.ConnectionFactory;

import javax.naming.NamingException;
import java.sql.Connection;
import java.sql.SQLException;

public class AbstractRepository {

    protected Connection getConnection() throws SQLException, NamingException {
        return ConnectionFactory.getConnection();
    }
}
