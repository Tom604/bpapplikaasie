package nl.workshop1.dao.mysqldao;

import nl.workshop1.dao.ArtikelDAO;
import nl.workshop1.dao.KlantDAO;
import nl.workshop1.dao.mysqldao.MySQLArtikelDAO;

/**
 *
 * @author Vosjes
 */
public class MySQLDAOFactory {
    
    /*
    Creatiemethodes voor alle DAO's in de factory
    */
    
    public ArtikelDAO getArtikelDAO() {
        return new MySQLArtikelDAO();
    }
    
    public KlantDAO getKlantDAO() {
        return new MySQLKlantDAO();
    }
}
