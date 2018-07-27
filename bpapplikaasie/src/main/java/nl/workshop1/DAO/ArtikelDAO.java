package nl.workshop1.DAO;

import nl.workshop1.domain.Artikel;

/**
 *
 * @author Vosjes
 */
public interface ArtikelDAO {
    
    /*
    Alle ISUD (INSERT, SELECT, UPDATE, DELETE) methodes die door MySQLArtikelDAO geïmplementeerd worden
    */
    
    public boolean insertArtikel(Artikel artikel);
    public Artikel selectArtikel(int id);
    public boolean updateArtikel(Artikel artikel);
    public boolean deleteArtikel(int id);
}
