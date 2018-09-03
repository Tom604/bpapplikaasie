package nl.workshop1.dao.mysqldao;

import java.sql.Connection;
import java.sql.Statement;
import nl.workshop1.dao.DAOFactory;
import nl.workshop1.dao.KlantDAO;
import nl.workshop1.domain.Klant;
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
public class MySQLKlantDAOTest {
    
    public MySQLKlantDAOTest() {
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
            
            statement.executeUpdate("USE bpapplikaasie");
            
            // Drop and create table klant
            statement.executeUpdate(
                    "CREATE TABLE IF NOT EXISTS bpapplikaasie.klant " +
                    "(id INT NOT NULL AUTO_INCREMENT, " +
                    "voornaam VARCHAR(50) NOT NULL, " +
                    "achternaam VARCHAR(50) NOT NULL, " +
                    "tussenvoegsel VARCHAR(15) NULL DEFAULT NULL, " +
                    "PRIMARY KEY (id)) " +
                    "ENGINE = InnoDB");
            
            // Insert testdata into the klant table
            statement.executeUpdate(
                    "INSERT INTO klant (voornaam, achternaam, tussenvoegsel) VALUES (\"tom\", \"vos\", \"de\")");
            statement.executeUpdate(
                    "INSERT INTO klant (voornaam, achternaam) VALUES (\"boer\", \"piet\")");
            statement.executeUpdate(
                    "INSERT INTO klant (voornaam, achternaam) VALUES (\"jacques\", \"contour\")");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    @After
    public void tearDown() {
        try (Connection connection = DatabaseConnection.getConnection()) {
            Statement statement = connection.createStatement();
            
            // Truncate table bpapplikaasie.klant
            statement.executeUpdate("SET foreign_key_checks = 0");
            statement.executeUpdate("TRUNCATE TABLE bpapplikaasie.klant");
            statement.executeUpdate("SET foreign_key_checks = 1");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Test of insertKlant method, of class MySQLKlantDAO.
     */
    @Test
    public void testInsertKlant() {
        System.out.println("insertKlant");
        Klant klant = new Klant();
        klant.setVoornaam("Sjaak");
        klant.setAchternaam("Rand");
        klant.setTussenvoegsel("van de");
        KlantDAO klantDAO = DAOFactory.getDAOFactory().getKlantDAO();
        boolean expResult = true;
        boolean result = klantDAO.insertKlant(klant);
        assertEquals(expResult, result);
    }

    /**
     * Test of selectKlant method, of class MySQLKlantDAO.
     */
    @Test
    public void testSelectKlant() {
        System.out.println("selectKlant");
        int id = 1;
        KlantDAO klantDAO = DAOFactory.getDAOFactory().getKlantDAO();
        Klant expResult = new Klant();
        expResult.setId(1);
        expResult.setVoornaam("tom");
        expResult.setAchternaam("vos");
        expResult.setTussenvoegsel("de");
        Klant result = klantDAO.selectKlant(id);
        assertEquals(expResult, result);
    }

    /**
     * Test of updateKlant method, of class MySQLKlantDAO.
     */
    @Test
    public void testUpdateKlant() {
        System.out.println("updateKlant");
        Klant klant = new Klant();
        klant.setId(2);
        klant.setVoornaam("Kees");
        klant.setAchternaam("Jong");
        KlantDAO klantDAO = DAOFactory.getDAOFactory().getKlantDAO();
        boolean expResult = true;
        boolean result = klantDAO.updateKlant(klant);
        assertEquals(expResult, result);
    }

    /**
     * Test of deleteKlant method, of class MySQLKlantDAO.
     */
    @Test
    public void testDeleteKlant() {
        System.out.println("deleteKlant");
        int id = 1;
        KlantDAO klantDAO = DAOFactory.getDAOFactory().getKlantDAO();
        boolean expResult = true;
        boolean result = klantDAO.deleteKlant(id);
        assertEquals(expResult, result);
    }
}
