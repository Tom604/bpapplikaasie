package nl.workshop1.dao.mysqldao;

import nl.workshop1.dao.AccountDAO;
import nl.workshop1.dao.AdresDAO;
import nl.workshop1.dao.ArtikelDAO;
import nl.workshop1.dao.DAOFactory;
import nl.workshop1.dao.KlantDAO;

/**
 *
 * @author Vosjes
 */
public class MySQLDAOFactory extends DAOFactory {
    
    /*
    Creatiemethodes voor alle DAO's in de factory
    */
    
    @Override
    public AccountDAO getAccountDAO() {
        return new MySQLAccountDAO();
    }
    
    @Override
    public AdresDAO getAdresDAO() {
        return new MySQLAdresDAO();
    }
    
    @Override
    public ArtikelDAO getArtikelDAO() {
        return new MySQLArtikelDAO();
    }
    
    @Override
    public KlantDAO getKlantDAO() {
        return new MySQLKlantDAO();
    }
}
