package nl.workshop1.dao;

import nl.workshop1.dao.mysqldao.MySQLDAOFactory;

/**
 *
 * @author Vosjes
 */
public abstract class DAOFactory {
    
    public static final int MYSQL = 1;
    // Meerdere data fields voor meerdere databases
    
    /*
    Hier bevinden zich de abstracte methodes die door de factories geïmplementeerd dienen te worden
    Gebruiken bij meerdere persistence manieren (bijv. MongoDB naast MySQL)
    */
    
    public abstract ArtikelDAO getArtikelDAO();
    public abstract KlantDAO getKlantDAO();
    public abstract AdresTypeDAO getAdresTypeDAO();
    
    public static DAOFactory getDAOFactory(int whichfactory) {
        
        switch (whichfactory) {
            case MYSQL: return new MySQLDAOFactory();
            // Meerdere cases voor meerdere databases
            
            default: return null;
        }
    }
}
