package nl.workshop1.DAO;

import nl.workshop1.domain.Klant;

/**
 *
 * @author Vosjes
 */
public interface KlantDAO {
    
    /*
    Alle ISUD (INSERT, SELECT, UPDATE, DELETE) methodes die door MySQLKlantDAO geïmplementeerd worden
    */
    
    public void insertKlant();
    public void selectKlant();
    /**
     * 
     * @return true in case of succes, false in case of failure
     */
    public boolean updateKlant();
    /**
     * 
     * @return true in case of succes, false in case of failure
     */
    public boolean deleteKlant();
    public Klant findKlant();
}
