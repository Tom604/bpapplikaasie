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
    
    private static final HikariConfig CONFIG = new HikariConfig("src/main/resources/hikaricp.properties");
    private static final HikariDataSource DS = new HikariDataSource(CONFIG);
    
    public static Connection getConnection() throws SQLException {
        return DS.getConnection();
    }
}
