package nl.workshop1.dao;

import java.util.ArrayList;
import nl.workshop1.domain.Bestelling;

/**
 *
 * @author Vosjes
 */
public interface BestellingDAO {

    /*
    Alle ISUD (INSERT, SELECT, UPDATE, DELETE) methodes die door MySQLBestellingDAO geïmplementeerd worden
    */
    
    public boolean insertBestelling (Bestelling bestelling);
    public Bestelling selectBestelling (int id);
    public ArrayList<Bestelling> selectBestellingen();
    public boolean updateBestelling (Bestelling bestelling);
    public boolean deleteBestelling (int id);    
}
