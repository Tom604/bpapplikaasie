package nl.workshop1.DAO;

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
