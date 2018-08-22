package nl.workshop1.dao.mysqldao;

import nl.workshop1.dao.AdresTypeDAO;
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
    public ArtikelDAO getArtikelDAO() {
        return new MySQLArtikelDAO();
    }
    
    @Override
    public KlantDAO getKlantDAO() {
        return new MySQLKlantDAO();
    }
    
    @Override
    public AdresTypeDAO getAdresTypeDAO() {
        return new MySQLAdresTypeDAO();
    }
}
