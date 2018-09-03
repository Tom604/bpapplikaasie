package nl.workshop1.dao.mysqldao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Statement;
import java.time.LocalDateTime;
import nl.workshop1.dao.BestellingDAO;
import nl.workshop1.dao.DAOFactory;
import nl.workshop1.domain.Bestelling;
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
public class MySQLBestellingDAOTest {
    
    public MySQLBestellingDAOTest() {
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
            statement.executeUpdate(
                    "INSERT INTO klant (voornaam, achternaam) VALUES (\"per\", \"volkort\")");

            // Drop and create table bestelling
            statement.executeUpdate(
                    "CREATE TABLE IF NOT EXISTS bpapplikaasie.bestelling " +
                    "(id INT NOT NULL AUTO_INCREMENT, " +
                    "totaalprijs DECIMAL(6,2) NOT NULL, " +
                    "datum_tijd TIMESTAMP(3) NOT NULL, " +
                    "klant_id INT NOT NULL, " +
                    "PRIMARY KEY (id), " +
                    "INDEX fk_bestelling_klant_idx (klant_id ASC), " +
                    "CONSTRAINT fk_bestelling_klant " +
                        "FOREIGN KEY (klant_id) " +
                        "REFERENCES bpapplikaasie.klant (id) " +
                        "ON DELETE CASCADE " +
                        "ON UPDATE NO ACTION) " +
                    "ENGINE = InnoDB");
            
            // Insert testdata into the bestelling table
            statement.executeUpdate(
                    "INSERT INTO bestelling (totaalprijs, datum_tijd, klant_id) " +
                    "VALUES (45.65, '2015-10-25 14:37:01', 1)");
            statement.executeUpdate(
                    "INSERT INTO bestelling (totaalprijs, datum_tijd, klant_id) " +
                    "VALUES (102.5, '2016-09-13 08:57:58', 2)");
            statement.executeUpdate(
                    "INSERT INTO bestelling (totaalprijs, datum_tijd, klant_id) " +
                    "VALUES (53.75, '2018-08-13 22:43:23', 3)");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    @After
    public void tearDown() {
        try (Connection connection = DatabaseConnection.getConnection()) {
            Statement statement = connection.createStatement();
            
            // Truncate tables bpapplikaasie.klant and bpapplikaasie.bestelling
            statement.executeUpdate("SET foreign_key_checks = 0");
            statement.executeUpdate("TRUNCATE TABLE bpapplikaasie.klant");
            statement.executeUpdate("TRUNCATE TABLE bpapplikaasie.bestelling");
            statement.executeUpdate("SET foreign_key_checks = 1");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Test of insertBestelling method, of class MySQLBestellingDAO.
     */
    @Test
    public void testInsertBestelling() {
        System.out.println("insertBestelling");
        Bestelling bestelling = new Bestelling();
        Klant klant = new Klant();
        klant.setId(4);
        bestelling.setTotaalprijs(new BigDecimal("85.95"));
        bestelling.setDatumTijd(LocalDateTime.of(2018, 8, 13, 22, 43, 23));
        bestelling.setKlant(klant);
        BestellingDAO bestellingDAO = DAOFactory.getDAOFactory().getBestellingDAO();
        boolean expResult = true;
        boolean result = bestellingDAO.insertBestelling(bestelling);
        assertEquals(expResult, result);
    }

    /**
     * Test of selectBestelling method, of class MySQLBestellingDAO.
     */
    @Test
    public void testSelectBestelling() {
        System.out.println("selectBestelling");
        int id = 1;
        BestellingDAO bestellingDAO = DAOFactory.getDAOFactory().getBestellingDAO();
        Bestelling expResult = new Bestelling();
        Klant klant = new Klant();
        klant.setId(1);
        expResult.setId(1);
        expResult.setTotaalprijs(new BigDecimal("45.65"));
        expResult.setDatumTijd(LocalDateTime.of(2015, 10, 25, 14, 37, 01));
        expResult.setKlant(klant);
        Bestelling result = bestellingDAO.selectBestelling(id);
        assertEquals(expResult, result);
    }

    /**
     * Test of updateBestelling method, of class MySQLBestellingDAO.
     */
    @Test
    public void testUpdateBestelling() {
        System.out.println("updateBestelling");
        Bestelling bestelling = new Bestelling();        
        Klant klant = new Klant();
        klant.setId(2);
        bestelling.setId(2);
        bestelling.setTotaalprijs(new BigDecimal("73.94"));
        bestelling.setDatumTijd(LocalDateTime.now());
        bestelling.setKlant(klant);
        BestellingDAO bestellingDAO = DAOFactory.getDAOFactory().getBestellingDAO();
        boolean expResult = true;
        boolean result = bestellingDAO.updateBestelling(bestelling);
        assertEquals(expResult, result);
    }

    /**
     * Test of deleteBestelling method, of class MySQLBestellingDAO.
     */
    @Test
    public void testDeleteBestelling() {
        System.out.println("deleteBestelling");
        int id = 1;
        BestellingDAO bestellingDAO = DAOFactory.getDAOFactory().getBestellingDAO();
        boolean expResult = true;
        boolean result = bestellingDAO.deleteBestelling(id);
        assertEquals(expResult, result);
    }
}
