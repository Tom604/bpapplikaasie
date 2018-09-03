package nl.workshop1.dao.mysqldao;

import java.sql.Connection;
import java.sql.Statement;
import nl.workshop1.dao.BestelregelDAO;
import nl.workshop1.dao.DAOFactory;
import nl.workshop1.domain.Artikel;
import nl.workshop1.domain.Bestelling;
import nl.workshop1.domain.Bestelregel;
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
public class MySQLBestelregelDAOTest {
    
    public MySQLBestelregelDAOTest() {
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
            statement.executeUpdate(
                    "INSERT INTO bestelling (totaalprijs, datum_tijd, klant_id) " +
                    "VALUES (76.5, '2018-05-26 10:37:55', 4)");
            
            // Drop and create table artikel
            statement.executeUpdate("USE bpapplikaasie");
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
            statement.executeUpdate(
                    "INSERT INTO artikel (naam, prijs, voorraad) VALUES (\"brandnetel\", 6, 20)");
            statement.executeUpdate(
                    "INSERT INTO artikel (naam, prijs, voorraad) VALUES (\"fenegriek\", 6.5, 25)");
            statement.executeUpdate(
                    "INSERT INTO artikel (naam, prijs, voorraad) VALUES (\"mosterd\", 5.5, 15)");
            statement.executeUpdate(
                    "INSERT INTO artikel (naam, prijs, voorraad) VALUES (\"oude\", 3.5, 19)");
            
            // Drop and create table bestelregel
            statement.executeUpdate(
                    "CREATE TABLE IF NOT EXISTS bpapplikaasie.bestelregel " +
                    "(id INT NOT NULL AUTO_INCREMENT, " +
                    "aantal INT NULL DEFAULT 1, " +
                    "bestelling_id INT NOT NULL, " +
                    "artikel_id INT NOT NULL, " +
                    "INDEX fk_bestelling_has_artikel_artikel1_idx (artikel_id ASC), " +
                    "INDEX fk_bestelling_has_artikel_bestelling1_idx (bestelling_id ASC), " +
                    "PRIMARY KEY (id), " +
                    "UNIQUE INDEX uq_bestelling_artikel (bestelling_id ASC, artikel_id ASC), " +
                    "CONSTRAINT fk_bestelling_has_artikel_bestelling1 " +
                        "FOREIGN KEY (bestelling_id) " +
                        "REFERENCES bpapplikaasie.bestelling (id) " +
                        "ON DELETE CASCADE " +
                        "ON UPDATE NO ACTION, " +
                    "CONSTRAINT fk_bestelling_has_artikel_artikel1 " +
                        "FOREIGN KEY (artikel_id) " +
                        "REFERENCES bpapplikaasie.artikel (id) " +
                        "ON DELETE CASCADE " +
                        "ON UPDATE NO ACTION) " +
                    "ENGINE = InnoDB");
            
            // Insert testdate into the bestelregel table
            statement.executeUpdate(
                    "INSERT INTO bestelregel (aantal, bestelling_id, artikel_id) " +
                    "VALUES (4, 1, 1)");
            statement.executeUpdate(
                    "INSERT INTO bestelregel (aantal, bestelling_id, artikel_id) " +
                    "VALUES (3, 1, 2)");
            statement.executeUpdate(
                    "INSERT INTO bestelregel (aantal, bestelling_id, artikel_id) " +
                    "VALUES (5, 1, 3)");
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
            statement.executeUpdate("TRUNCATE TABLE bpapplikaasie.bestelling");
            statement.executeUpdate("TRUNCATE TABLE bpapplikaasie.artikel");
            statement.executeUpdate("TRUNCATE TABLE bpapplikaasie.bestelregel");
            statement.executeUpdate("SET foreign_key_checks = 1");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Test of insertBestelregel method, of class MySQLBestelregelDAO.
     */
    @Test
    public void testInsertBestelregel() {
        System.out.println("insertBestelregel");
        Bestelregel bestelregel = new Bestelregel();
        Bestelling bestelling = new Bestelling();
        Artikel artikel = new Artikel();
        bestelling.setId(4);
        artikel.setId(4);
        bestelregel.setAantal(6);
        bestelregel.setBestelling(bestelling);
        bestelregel.setArtikel(artikel);
        BestelregelDAO bestelregelDAO = DAOFactory.getDAOFactory().getBestelregelDAO();
        boolean expResult = true;
        boolean result = bestelregelDAO.insertBestelregel(bestelregel);
        assertEquals(expResult, result);
    }

    /**
     * Test of selectBestelregel method, of class MySQLBestelregelDAO.
     */
    @Test
    public void testSelectBestelregel() {
        System.out.println("selectBestelregel");
        int id = 1;
        BestelregelDAO bestelregelDAO = DAOFactory.getDAOFactory().getBestelregelDAO();
        Bestelregel expResult = new Bestelregel();
        Bestelling bestelling = new Bestelling();
        Artikel artikel = new Artikel();
        bestelling.setId(1);
        artikel.setId(1);
        expResult.setId(1);
        expResult.setAantal(4);
        expResult.setBestelling(bestelling);
        expResult.setArtikel(artikel);
        Bestelregel result = bestelregelDAO.selectBestelregel(id);
        assertEquals(expResult, result);
    }

    /**
     * Test of updateBestelregel method, of class MySQLBestelregelDAO.
     */
    @Test
    public void testUpdateBestelregel() {
        System.out.println("updateBestelregel");
        Bestelregel bestelregel = new Bestelregel();
        Bestelling bestelling = new Bestelling();
        Artikel artikel = new Artikel();
        bestelling.setId(2);
        artikel.setId(2);
        bestelregel.setId(2);
        bestelregel.setAantal(6);
        bestelregel.setBestelling(bestelling);
        bestelregel.setArtikel(artikel);
        BestelregelDAO bestelregelDAO = DAOFactory.getDAOFactory().getBestelregelDAO();
        boolean expResult = true;
        boolean result = bestelregelDAO.updateBestelregel(bestelregel);
        assertEquals(expResult, result);
    }

    /**
     * Test of deleteBestelregel method, of class MySQLBestelregelDAO.
     */
    @Test
    public void testDeleteBestelregel() {
        System.out.println("deleteBestelregel");
        int id = 1;
        BestelregelDAO bestelregelDAO = DAOFactory.getDAOFactory().getBestelregelDAO();
        boolean expResult = true;
        boolean result = bestelregelDAO.deleteBestelregel(id);
        assertEquals(expResult, result);
    }
}
