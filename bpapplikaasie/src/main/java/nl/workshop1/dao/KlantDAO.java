package nl.workshop1.dao;

import java.util.ArrayList;
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
    public Klant insertAndSelectKlant(Klant klant);
    public ArrayList<Klant> selectKlanten();
    public boolean updateKlant(Klant klant);
    public boolean deleteKlant(int id);
}
