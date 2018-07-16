package nl.workshop1.DAO;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import nl.workshop1.domain.Artikel;

/**
 *
 * @author Vosjes
 */
public interface ArtikelDAO {
    
    /*
    Alle ISUD (INSERT, SELECT, UPDATE, DELETE) methodes die door MySQLArtikelDAO geïmplementeerd worden
    */
    
    public void insertArtikel(Connection connection, String naam, BigDecimal prijs,
            int voorraad) throws SQLException;
    public Artikel selectArtikel(Connection connection, String naam) throws SQLException;
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
