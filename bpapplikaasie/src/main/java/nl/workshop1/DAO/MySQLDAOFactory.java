package nl.workshop1.DAO;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import javax.xml.parsers.ParserConfigurationException;
import nl.workshop1.utility.ConnectToDatabase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.SAXException;

/**
 *
 * @author Vosjes
 */
public class MySQLDAOFactory {
    
    /*
    MySQL connection en de creatiemethodes voor alle DAO's in de factory
    */
    
    static Logger log = LoggerFactory.getLogger(MySQLDAOFactory.class);
    
    public static Connection createConnection() {
        
        Connection connection = null;
        
        try {
            connection = ConnectToDatabase.connectToMySQLWithXml();
            log.debug("Database connected");
        } catch (ParserConfigurationException | SAXException | IOException |
                ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
            log.warn("Exception catched");
        }
        
        return connection;
    }
    
    public static void closeConnection(Connection connection) throws SQLException {
        connection.close();
        log.debug("Database closed");
    }
    
    public ArtikelDAO getArtikelDAO() {
        return new MySQLArtikelDAO();
    }
    
    public KlantDAO getKlantDAO() {
        return new MySQLKlantDAO();
    }
}
