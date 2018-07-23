package nl.workshop1.DAO;

import java.math.BigDecimal;
import java.sql.Connection;
import nl.workshop1.domain.Artikel;
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
        // TODO drop en create database
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of insertArtikel method, of class MySQLArtikelDAO.
     */
    @Test
    public void testInsertArtikel() throws Exception {
        System.out.println("insertArtikel");
        Connection connection = null;
        String naam = "";
        BigDecimal prijs = null;
        int voorraad = 0;
        MySQLArtikelDAO instance = new MySQLArtikelDAO();
        boolean expResult = false;
        boolean result = instance.insertArtikel(connection, naam, prijs, voorraad);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of selectArtikel method, of class MySQLArtikelDAO.
     */
    @Test
    public void testSelectArtikel() throws Exception {
        System.out.println("selectArtikel");
        Connection connection = null;
        String naam = "";
        MySQLArtikelDAO instance = new MySQLArtikelDAO();
        Artikel expResult = null;
        Artikel result = instance.selectArtikel(connection, naam);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updateArtikel method, of class MySQLArtikelDAO.
     */
    @Test
    public void testUpdateArtikel_4args_1() throws Exception {
        System.out.println("updateArtikel");
        Connection connection = null;
        String kolomNaam = "";
        String nieuweWaarde = "";
        String naam = "";
        MySQLArtikelDAO instance = new MySQLArtikelDAO();
        boolean expResult = false;
        boolean result = instance.updateArtikel(connection, kolomNaam, nieuweWaarde, naam);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updateArtikel method, of class MySQLArtikelDAO.
     */
    @Test
    public void testUpdateArtikel_4args_2() throws Exception {
        System.out.println("updateArtikel");
        Connection connection = null;
        String kolomNaam = "";
        BigDecimal nieuweWaarde = null;
        String naam = "";
        MySQLArtikelDAO instance = new MySQLArtikelDAO();
        instance.updateArtikel(connection, kolomNaam, nieuweWaarde, naam);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updateArtikel method, of class MySQLArtikelDAO.
     */
    @Test
    public void testUpdateArtikel_4args_3() throws Exception {
        System.out.println("updateArtikel");
        Connection connection = null;
        String kolomNaam = "";
        int nieuweWaarde = 0;
        String naam = "";
        MySQLArtikelDAO instance = new MySQLArtikelDAO();
        instance.updateArtikel(connection, kolomNaam, nieuweWaarde, naam);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of deleteArtikel method, of class MySQLArtikelDAO.
     */
    @Test
    public void testDeleteArtikel() throws Exception {
        System.out.println("deleteArtikel");
        Connection connection = null;
        String naam = "";
        MySQLArtikelDAO instance = new MySQLArtikelDAO();
        boolean expResult = false;
        boolean result = instance.deleteArtikel(connection, naam);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
