package nl.workshop1.dao;

import nl.workshop1.domain.Bestelregel;

/**
 *
 * @author Vosjes
 */
public interface BestelregelDAO {

    /*
    Alle ISUD (INSERT, SELECT, UPDATE, DELETE) methodes die door MySQLBestelregelDAO geïmplementeerd worden
    */
    
    public boolean insertBestelregel(Bestelregel bestelregel);
    public Bestelregel selectBestelregel (int id);
    public boolean updateBestelregel (Bestelregel bestelregel);
    public boolean deleteBestelregel (int id);
}
