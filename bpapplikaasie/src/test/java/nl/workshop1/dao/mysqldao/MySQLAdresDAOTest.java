package nl.workshop1.dao.mysqldao;

import java.sql.Connection;
import java.sql.Statement;
import nl.workshop1.dao.AdresDAO;
import nl.workshop1.dao.DAOFactory;
import nl.workshop1.domain.Adres;
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
public class MySQLAdresDAOTest {
    
    public MySQLAdresDAOTest() {
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
            statement.executeUpdate("USE bpapplikaasie");
            statement.executeUpdate(
                    "INSERT INTO klant (voornaam, achternaam, tussenvoegsel) VALUES (\"tom\", \"vos\", \"de\")");
            statement.executeUpdate(
                    "INSERT INTO klant (voornaam, achternaam) VALUES (\"boer\", \"piet\")");
            statement.executeUpdate(
                    "INSERT INTO klant (voornaam, achternaam) VALUES (\"jacques\", \"contour\")");
            statement.executeUpdate(
                    "INSERT INTO klant (voornaam, achternaam) VALUES (\"per\", \"volkort\")");

            // Drop and create table adres
            statement.executeUpdate(
                    "CREATE TABLE IF NOT EXISTS bpapplikaasie.adres " +
                    "(id INT NOT NULL AUTO_INCREMENT, " +
                    "straatnaam VARCHAR(50) NOT NULL, " +
                    "huisnummer INT NOT NULL, " +
                    "toevoeging VARCHAR(5) NULL DEFAULT NULL, " +
                    "postcode VARCHAR(6) NOT NULL, " +
                    "woonplaats VARCHAR(50) NOT NULL, " +
                    "adrestype ENUM(\"factuur\", \"post\", \"woon\") NOT NULL, " +
                    "klant_id INT NOT NULL, " +
                    "PRIMARY KEY (id), " +
                    "INDEX fk_adres_klant_idx (klant_id ASC), " +
                    "UNIQUE INDEX uq_klant_adrestype (klant_id ASC, adrestype ASC), " +
                    "CONSTRAINT fk_adres_klant " +
                        "FOREIGN KEY (klant_id) " +
                        "REFERENCES bpapplikaasie.klant (id) " +
                        "ON DELETE NO ACTION " +
                        "ON UPDATE NO ACTION) " +
                    "ENGINE = InnoDB");
            
            // Insert testdata into the adres table
            statement.executeUpdate("USE bpapplikaasie");
            statement.executeUpdate(
                    "INSERT INTO adres (straatnaam, huisnummer, toevoeging, postcode, woonplaats, " +
                    "adrestype, klant_id) VALUES (\"rijk\", 3, \"bis\", \"3432AD\", \"grutjes\", " +
                    "\"woon\", 1)");
            statement.executeUpdate(
                    "INSERT INTO adres (straatnaam, huisnummer, toevoeging, postcode, woonplaats, " +
                    "adrestype, klant_id) VALUES (\"veld\", 5, \"c\", \"5748DD\", \"vinder\", " +
                    "\"woon\", 2)");
            statement.executeUpdate(
                    "INSERT INTO adres (straatnaam, huisnummer, toevoeging, postcode, woonplaats, " +
                    "adrestype, klant_id) VALUES (\"zon\", 7, \"d\", \"9435CT\", \"klotsing\", " +
                    "\"woon\", 3)");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    @After
    public void tearDown() {
        try (Connection connection = DatabaseConnection.getConnection()) {
            Statement statement = connection.createStatement();
            
            // Truncate tables bpapplikaasie.klant and bpapplikaasie.adres
            statement.executeUpdate("SET foreign_key_checks = 0");
            statement.executeUpdate("TRUNCATE TABLE bpapplikaasie.klant");
            statement.executeUpdate("TRUNCATE TABLE bpapplikaasie.adres");
            statement.executeUpdate("SET foreign_key_checks = 1");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Test of insertAdres method, of class MySQLAdresDAO.
     */
    @Test
    public void testInsertAdres() {
        System.out.println("insertAdres");
        Adres adres = new Adres();
        Klant klant = new Klant();
        klant.setId(4);
        adres.setStraatnaam("maan");
        adres.setHuisnummer(56);
        adres.setToevoeging("b");
        adres.setPostcode("4545EF");
        adres.setWoonplaats("groest");
        adres.setAdrestype("post");
        adres.setKlant(klant);
        AdresDAO adresDAO = DAOFactory.getDAOFactory().getAdresDAO();
        boolean expResult = true;
        boolean result = adresDAO.insertAdres(adres);
        assertEquals(expResult, result);
    }

    /**
     * Test of selectAdres method, of class MySQLAdresDAO.
     */
    @Test
    public void testSelectAdres() {
        System.out.println("selectAdres");
        int id = 1;
        AdresDAO adresDAO = DAOFactory.getDAOFactory().getAdresDAO();
        Adres expResult = new Adres();
        Klant klant = new Klant();
        klant.setId(1);
        expResult.setId(1);
        expResult.setStraatnaam("rijk");
        expResult.setHuisnummer(3);
        expResult.setToevoeging("bis");
        expResult.setPostcode("3432AD");
        expResult.setWoonplaats("grutjes");
        expResult.setAdrestype("woon");
        expResult.setKlant(klant);
        Adres result = adresDAO.selectAdres(id);
        assertEquals(expResult, result);
    }

    /**
     * Test of updateAdres method, of class MySQLAdresDAO.
     */
    @Test
    public void testUpdateAdres() {
        System.out.println("updateAdres");
        Adres adres = new Adres();
        Klant klant = new Klant();
        klant.setId(2);
        adres.setId(2);
        adres.setStraatnaam("beer");
        adres.setHuisnummer(13);
        adres.setToevoeging("f");
        adres.setPostcode("9283AA");
        adres.setWoonplaats("verghel");
        adres.setAdrestype("factuur");
        adres.setKlant(klant);
        AdresDAO adresDAO = DAOFactory.getDAOFactory().getAdresDAO();
        boolean expResult = true;
        boolean result = adresDAO.updateAdres(adres);
        assertEquals(expResult, result);
    }

    /**
     * Test of deleteAdres method, of class MySQLAdresDAO.
     */
    @Test
    public void testDeleteAdres() {
        System.out.println("deleteAdres");
        int id = 1;
        AdresDAO adresDAO = DAOFactory.getDAOFactory().getAdresDAO();
        boolean expResult = true;
        boolean result = adresDAO.deleteAdres(id);
        assertEquals(expResult, result);
    }
}
