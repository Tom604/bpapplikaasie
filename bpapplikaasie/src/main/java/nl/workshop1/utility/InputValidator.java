package nl.workshop1.utility;

/**
 *
 * @author tomhannah
 */
public class InputValidator {
    
    /*
    In deze klasse valideren:
    1.  postcode
    2.  wachtwoord (momenteel alleen lengte)
    3.  ...
    */
    
    public boolean validatePostcode(String postcode) {
        return postcode.matches("[1-9][0-9]{3}\\s*(?!sa|sd|ss|SA|SD|SS)[a-zA-Z]{2}");
    }
    
    public boolean validateWachtwoord(String wachtwoord) {
        return wachtwoord.matches(".{4,}");
    }
}
