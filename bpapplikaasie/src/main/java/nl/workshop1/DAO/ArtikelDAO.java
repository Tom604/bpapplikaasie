package nl.workshop1.DAO;

import java.math.BigDecimal;
import nl.workshop1.domain.Artikel;

/**
 *
 * @author Vosjes
 */
public interface ArtikelDAO {
    
    /*
    Alle ISUD (INSERT, SELECT, UPDATE, DELETE) methodes die door MySQLArtikelDAO geïmplementeerd worden
    */
    
    public boolean insertArtikel(String naam, BigDecimal prijs, int voorraad);
    public Artikel selectArtikel(int id);
    public boolean updateArtikel(int id, String naam, BigDecimal prijs, int voorraad);
    public boolean deleteArtikel(int id);
}
