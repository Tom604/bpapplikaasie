package nl.workshop1.controller;

import nl.workshop1.dao.AccountDAO;
import nl.workshop1.dao.DAOFactory;
import nl.workshop1.domain.Account;

/**
 *
 * @author Vosjes
 */
public class LoginController {
    
    public static String loginnaam;
    public static String accounttype;

    public boolean validateLogin(String username, String wachtwoord) {
        
        boolean comparison = false;
        
        // Verkrijg data van de database via AccountDAO en valideer
        AccountDAO accountDAO = DAOFactory.getDAOFactory().getAccountDAO();
        Account account = accountDAO.selectAccount(username);
        if (wachtwoord.equals(account.getWachtwoord())) {
                comparison = true;
                loginnaam = account.getUsername();
                accounttype = account.getAccounttype();
            }
        return comparison;
    }
}
