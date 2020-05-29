package utils;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionFactory {

    private static final String RESOURCE_LINK_NAME = "java:/comp/env/jdbc/taskManagerDataSource";

    public static Connection getConnection() throws NamingException, SQLException {
        InitialContext initialContext = new InitialContext();
        DataSource dataSource = (DataSource) initialContext.lookup(RESOURCE_LINK_NAME);
        return dataSource.getConnection();
    }
}
