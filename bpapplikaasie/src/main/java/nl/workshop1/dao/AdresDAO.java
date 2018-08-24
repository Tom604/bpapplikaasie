package nl.workshop1.dao;

import nl.workshop1.domain.Adres;

/**
 *
 * @author Vosjes
 */
public interface AdresDAO {
    
    /*
    Alle ISUD (INSERT, SELECT, UPDATE, DELETE) methodes die door MySQLAdresTypeDAO geïmplementeerd worden
    */
    
    public boolean insertAdres(Adres adres);
    public Adres selectAdres(int id);
    public boolean updateAdres(Adres adres);
    public boolean deleteAdres(int id);
}
