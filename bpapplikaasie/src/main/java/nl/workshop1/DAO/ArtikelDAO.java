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
    
    public void insertArtikel();
    public void selectArtikel();
    /**
     * 
     * @return true in case of succes, false in case of failure
     */
    public boolean updateArtikel();
    /**
     * 
     * @return true in case of succes, false in case of failure
     */
    public boolean deleteArtikel();
    public Artikel findArtikel();
}
