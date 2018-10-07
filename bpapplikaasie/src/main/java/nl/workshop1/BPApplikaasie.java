package nl.workshop1;

import java.sql.SQLException;
import nl.workshop1.utility.HikariCPDataSource;
import nl.workshop1.view.LoginMenuView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Vosjes
 */
public class BPApplikaasie {
    
    private static final Logger log = LoggerFactory.getLogger(BPApplikaasie.class);
    
    public static void main(String[] args) {
        
        //Connect to database and check if connection is good (otherwise close app and log)
        try {
            HikariCPDataSource.getConnection().close();
            log.debug("Database connection tested, connection pool created");
        } catch (SQLException ex) {
            ex.printStackTrace();
            log.warn("Exception catched when connecting to database");
            System.exit(1);
        }
        
        //Start bpapplikaasie
        LoginMenuView loginMenuView = new LoginMenuView();
        loginMenuView.showStartMenu();
    }
}
