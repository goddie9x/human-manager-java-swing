package Config;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectDbSql {

    private static final ConnectDbSql instance = new ConnectDbSql();
    private final String server = "DESKTOP-13CUDEO";
    private final String user = "sa";
    private final String password = "Stamhm97";
    private final String database = "humanResourceManager";
    private final int port = 1433;
    private final String driverClass = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    private final SQLServerDataSource dataSource = new SQLServerDataSource();

    private void init() {
        dataSource.setPassword(password);
        dataSource.setUser(user);
        dataSource.setPortNumber(port);
        dataSource.setDatabaseName(database);
        dataSource.setServerName(server);
        dataSource.setQueryTimeout(50);
    }

    public static ConnectDbSql getInstance() {
        return instance;
    }

    public Connection getConnection() {
        init();
        try {
            Connection connection = dataSource.getConnection();
            System.out.println("connect success");
            return connection;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
