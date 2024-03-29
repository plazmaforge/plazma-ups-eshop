package plazma.ups.eshop.dao.jdbc;

import plazma.ups.eshop.dao.UserDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;

public class JdbcUserDao implements UserDao {

    private static final String FIND_QUERY = "SELECT id FROM user WHERE username = ? AND password = ?";

    private DefaultDataSource dataSource;

    private static Logger logger = LogManager.getLogger(JdbcUserDao.class);

    public JdbcUserDao(DefaultDataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public boolean existsUser(String username, String password) {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_QUERY)) {
            statement.setString(1, username);
            statement.setString(2, password);
            try (ResultSet resultSet = statement.executeQuery();) {
                return resultSet.next();
            }
        } catch (SQLException e) {
            logger.error(e);
            throw new RuntimeException("Cannot get user from db", e);
        }
    }

    private Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

}
