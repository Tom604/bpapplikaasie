package nl.workshop1.DAO;

import java.sql.Connection;

/**
 *
 * @author Vosjes
 */
public class MySQLDAOFactory {
    
    /*
    MySQL connection en de creatiemethodes voor alle DAO's in de factory
    */
    
    public static Connection createConnection() {
        
    }
    
    public ArtikelDAO getArtikelDAO() {
        return new MySQLArtikelDAO();
    }
    
    public KlantDAO getKlantDAO() {
        return new MySQLKlantDAO();
    }
}
