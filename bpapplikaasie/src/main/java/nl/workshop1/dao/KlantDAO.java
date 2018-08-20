package nl.workshop1.dao;

import nl.workshop1.domain.Klant;

/**
 *
 * @author Vosjes
 */
public interface KlantDAO {
    
    /*
    Alle ISUD (INSERT, SELECT, UPDATE, DELETE) methodes die door MySQLKlantDAO geïmplementeerd worden
    */
    
    public boolean insertKlant(Klant klant);
    public Klant selectKlant(int id);
    public boolean updateKlant(Klant klant);
    public boolean deleteKlant(int id);
}
