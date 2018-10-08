package nl.workshop1.controller;

import nl.workshop1.dao.AccountDAO;
import nl.workshop1.dao.DAOFactory;
import nl.workshop1.domain.Account;
import nl.workshop1.utility.PasswordStorage;

/**
 *
 * @author Vosjes
 */
public class LoginController {
    
    public static String loginnaam;
    public static String accounttype;

    public boolean validateLogin(String username, String wachtwoord) {
        
        boolean validation = false;
        
        // Verkrijg data van de database via AccountDAO en valideer
        AccountDAO accountDAO = DAOFactory.getDAOFactory().getAccountDAO();
        Account account = accountDAO.selectAccount(username);
        if (account.getUsername() != null && PasswordStorage.verifyPassword(wachtwoord, account.getWachtwoord())) {
            validation = true;
            loginnaam = account.getUsername();
            accounttype = account.getAccounttype();
        }
        return validation;
    }
}
