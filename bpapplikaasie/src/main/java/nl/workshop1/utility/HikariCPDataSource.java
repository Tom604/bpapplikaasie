package nl.workshop1.utility;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author Vosjes
 */
public class HikariCPDataSource {
    
    private static HikariConfig config = new HikariConfig("src/main/resources/hikaricp.properties");
    private static HikariDataSource ds = new HikariDataSource(config);
    
    public static Connection getConnection() throws SQLException {
        return ds.getConnection();
    }
}
