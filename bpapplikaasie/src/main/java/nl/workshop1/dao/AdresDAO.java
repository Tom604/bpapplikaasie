package nl.workshop1.dao;

import java.util.ArrayList;
import nl.workshop1.domain.Adres;

/**
 *
 * @author Vosjes
 */
public interface AdresDAO {
    
    /*
    Alle ISUD (INSERT, SELECT, UPDATE, DELETE) methodes die door MySQLAdresDAO geïmplementeerd worden
    */
    
    public boolean insertAdres(Adres adres);
    public Adres selectAdres(int id);
    public ArrayList<Adres> selectAdressen();
    public ArrayList<Adres> selectAdressen(int klantId);
    public boolean updateAdres(Adres adres);
    public boolean deleteAdres(int id);
}
