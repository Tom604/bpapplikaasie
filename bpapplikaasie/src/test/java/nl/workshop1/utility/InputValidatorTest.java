package nl.workshop1.utility;

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
public class InputValidatorTest {
    
    public InputValidatorTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of validatePostcode method, of class InputValidator.
     */
    @Test
    public void testValidatePostcode() {
        System.out.println("validatePostcode");
        InputValidator instance = new InputValidator();
        boolean expResult = true;
        boolean result = instance.validatePostcode("1234AB");
        assertEquals(expResult, result);
        expResult = true;
        result = instance.validatePostcode("1234 AB");
        assertEquals(expResult, result);
        expResult = true;
        result = instance.validatePostcode("1234ab");
        assertEquals(expResult, result);
        expResult = true;
        result = instance.validatePostcode("1234 ab");
        assertEquals(expResult, result);
        expResult = false;
        result = instance.validatePostcode("0234AB");
        assertEquals(expResult, result);
        expResult = false;
        result = instance.validatePostcode("1234sd");
        assertEquals(expResult, result);
    }

    /**
     * Test of validateWachtwoord method, of class InputValidator.
     */
    @Test
    public void testValidateWachtwoord() {
        System.out.println("validateWachtwoord");
        InputValidator instance = new InputValidator();
        boolean expResult = true;
        boolean result = instance.validateWachtwoord("9874534789n");
        assertEquals(expResult, result);
        expResult = false;
        result = instance.validateWachtwoord("*6Y");
        assertEquals(expResult, result);
        expResult = true;
        result = instance.validateWachtwoord("*6Y1");
        assertEquals(expResult, result);
        expResult = true;
        result = instance.validateWachtwoord("luybN)@&*(#6bpbnyqwr9876b)&*(^V02");
        assertEquals(expResult, result);
    }
}
