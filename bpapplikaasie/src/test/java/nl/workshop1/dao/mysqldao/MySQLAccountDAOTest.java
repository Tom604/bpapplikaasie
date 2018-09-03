package nl.workshop1.dao.mysqldao;

import java.sql.Connection;
import java.sql.Statement;
import nl.workshop1.dao.AccountDAO;
import nl.workshop1.dao.DAOFactory;
import nl.workshop1.domain.Account;
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
public class MySQLAccountDAOTest {
    
    public MySQLAccountDAOTest() {
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

            // Drop and create table account
            statement.executeUpdate(
                    "CREATE TABLE IF NOT EXISTS bpapplikaasie.account " +
                    "(id INT NOT NULL AUTO_INCREMENT, " +
                    "username VARCHAR(25) NOT NULL, " +
                    "wachtwoord VARCHAR(180) NOT NULL, " +
                    "accounttype ENUM(\"admin\", \"klant\", \"medewerker\") NOT NULL, " +
                    "klant_id INT NULL, " +
                    "PRIMARY KEY (id), " +
                    "UNIQUE INDEX username_UNIQUE (username ASC), " +
                    "INDEX fk_account_klant_idx (klant_id ASC), " +
                    "UNIQUE INDEX klant_id_UNIQUE (klant_id ASC)," +
                    "CONSTRAINT fk_account_klant " +
                        "FOREIGN KEY (klant_id) " +
                        "REFERENCES bpapplikaasie.klant (id) " +
                        "ON DELETE CASCADE " +
                        "ON UPDATE NO ACTION)" +
                    "ENGINE = InnoDB");
            
            // Insert testdata into the account table
            statement.executeUpdate(
                    "INSERT INTO account (username, wachtwoord, accounttype) " +
                    "VALUES (\"mistertom\", \"Appelmoes!*!\", \"medewerker\")");
            statement.executeUpdate(
                    "INSERT INTO account (username, wachtwoord, accounttype) " +
                    "VALUES (\"sirpiet\", \"Pol&lepel*\", \"admin\")");
            statement.executeUpdate(
                    "INSERT INTO account (username, wachtwoord, accounttype, klant_id) " +
                    "VALUES (\"sjaak\", \"ScherpR@ndje\", \"klant\", 3)");
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
            statement.executeUpdate("TRUNCATE TABLE bpapplikaasie.account");
            statement.executeUpdate("SET foreign_key_checks = 1");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Test of insertAccount method, of class MySQLAccountDAO.
     */
    @Test
    public void testInsertAccount() {
        System.out.println("insertAccount");
        Account account = new Account();
        Klant klant = new Klant();
        klant.setId(4);
        account.setUsername("Opper");
        account.setWachtwoord("Langi$$$lang");
        account.setAccounttype("klant");
        account.setKlant(klant);
        AccountDAO accountDAO = DAOFactory.getDAOFactory().getAccountDAO();
        boolean expResult = true;
        boolean result = accountDAO.insertAccount(account);
        assertEquals(expResult, result);
    }

    /**
     * Test of selectAccount method, of class MySQLAccountDAO.
     */
    @Test
    public void testSelectAccount() {
        System.out.println("selectAccount");
        int id = 3;
        AccountDAO accountDAO = DAOFactory.getDAOFactory().getAccountDAO();
        Account expResult = new Account();
        Klant klant = new Klant();
        klant.setId(3);
        expResult.setId(3);
        expResult.setUsername("sjaak");
        expResult.setWachtwoord("ScherpR@ndje");
        expResult.setAccounttype("klant");
        expResult.setKlant(klant);
        Account result = accountDAO.selectAccount(id);
        assertEquals(expResult, result);
    }

    /**
     * Test of updateAccount method, of class MySQLAccountDAO.
     */
    @Test
    public void testUpdateAccount() {
        System.out.println("updateAccount");
        Account account = new Account();
        Klant klant = new Klant();
        klant.setId(3);
        account.setId(3);
        account.setUsername("newuser");
        account.setWachtwoord("nieuw~wachtWoord");
        account.setAccounttype("klant");
        account.setKlant(klant);
        AccountDAO accountDAO = DAOFactory.getDAOFactory().getAccountDAO();
        boolean expResult = true;
        boolean result = accountDAO.updateAccount(account);
        assertEquals(expResult, result);
    }

    /**
     * Test of deleteAccount method, of class MySQLAccountDAO.
     */
    @Test
    public void testDeleteAccount() {
        System.out.println("deleteAccount");
        int id = 1;
        AccountDAO accountDAO = DAOFactory.getDAOFactory().getAccountDAO();
        boolean expResult = true;
        boolean result = accountDAO.deleteAccount(id);
        assertEquals(expResult, result);
    }
}
