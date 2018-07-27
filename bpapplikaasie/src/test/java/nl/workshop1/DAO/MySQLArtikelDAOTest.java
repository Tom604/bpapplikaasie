package nl.workshop1.DAO;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Statement;
import nl.workshop1.domain.Artikel;
import nl.workshop1.utility.DatabaseConnection;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Vosjes
 */
public class MySQLArtikelDAOTest {
    
    public MySQLArtikelDAOTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        
        try (Connection connection = DatabaseConnection.getConnection()) {
            Statement statement = connection.createStatement();
            
            // Drop and create schema bpapplikaasie
            statement.executeUpdate("DROP SCHEMA IF EXISTS bpapplikaasie");
            statement.executeUpdate("CREATE SCHEMA IF NOT EXISTS bpapplikaasie "
                    + "DEFAULT CHARACTER SET utf8");
            statement.executeUpdate("USE bpapplikaasie");
            
            // Drop and create table artikel
            statement.executeUpdate("DROP TABLE IF EXISTS bpapplikaasie.artikel");
            statement.executeUpdate(
                    "CREATE TABLE IF NOT EXISTS bpapplikaasie.artikel " +
                    "(id INT NOT NULL AUTO_INCREMENT, " +
                    "naam VARCHAR(45) NOT NULL, " +
                    "prijs DECIMAL(6,2) NOT NULL, " +
                    "voorraad INT NULL DEFAULT NULL, " +
                    "PRIMARY KEY (id), " +
                    "UNIQUE INDEX naam_UNIQUE (naam ASC)) " +
                    "ENGINE = InnoDB");
            
            // Insert testdata into the artikel table
            statement.executeUpdate("USE bpapplikaasie");
            statement.executeUpdate(
                    "INSERT INTO artikel (naam, prijs, voorraad) VALUES (\"brandnetel\", 6, 20)");
            statement.executeUpdate(
                    "INSERT INTO artikel (naam, prijs, voorraad) VALUES (\"fenegriek\", 6.5, 25)");
            statement.executeUpdate(
                    "INSERT INTO artikel (naam, prijs, voorraad) VALUES (\"mosterd\", 5.5, 15)");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    @After
    public void tearDown() {
        
        try (Connection connection = DatabaseConnection.getConnection()) {
            Statement statement = connection.createStatement();
            
            // Drop schema bpapplikaasie
            statement.executeUpdate("DROP SCHEMA IF EXISTS bpapplikaasie");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Test of insertArtikel method, of class MySQLArtikelDAO.
     */
    @Test
    public void testInsertArtikel() {
        System.out.println("insertArtikel");
        Artikel artikel = new Artikel();
        artikel.setNaam("oude");
        artikel.setPrijs(new BigDecimal(4));
        artikel.setVoorraad(21);
        MySQLArtikelDAO instance = new MySQLArtikelDAO();
        boolean expResult = true;
        boolean result = instance.insertArtikel(artikel);
        assertEquals(expResult, result);
    }

    /**
     * Test of selectArtikel method, of class MySQLArtikelDAO.
     */
    @Test
    public void testSelectArtikel() {
        System.out.println("selectArtikel");
        int id = 1;
        MySQLArtikelDAO instance = new MySQLArtikelDAO();
        Artikel expResult = new Artikel();
        expResult.setId(1);
        expResult.setNaam("brandnetel");
        expResult.setPrijs(new BigDecimal("6.00"));
        expResult.setVoorraad(20);
        Artikel result = instance.selectArtikel(id);
        assertEquals(expResult, result);
    }

    /**
     * Test of updateArtikel method, of class MySQLArtikelDAO.
     */
    @Test
    public void testUpdateArtikel() {
        System.out.println("updateArtikel");
        Artikel artikel = new Artikel();
        artikel.setId(2);
        artikel.setNaam("jonge");
        artikel.setPrijs(new BigDecimal(3.5));
        artikel.setVoorraad(17);
        MySQLArtikelDAO instance = new MySQLArtikelDAO();
        boolean expResult = true;
        boolean result = instance.updateArtikel(artikel);
        assertEquals(expResult, result);
    }

    /**
     * Test of deleteArtikel method, of class MySQLArtikelDAO.
     */
    @Test
    public void testDeleteArtikel() {
        System.out.println("deleteArtikel");
        int id = 1;
        MySQLArtikelDAO instance = new MySQLArtikelDAO();
        boolean expResult = true;
        boolean result = instance.deleteArtikel(id);
        assertEquals(expResult, result);
    }

    /**
     * Test of hashCode method, of class MySQLArtikelDAO.
     */
    @Test
    public void testHashCode() {
        System.out.println("hashCode");
        MySQLArtikelDAO instance = new MySQLArtikelDAO();
        int expResult = 507;
        int result = instance.hashCode();
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class MySQLArtikelDAO.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        Object obj = null;
        MySQLArtikelDAO instance = new MySQLArtikelDAO();
        boolean expResult = false;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);
    }
}
