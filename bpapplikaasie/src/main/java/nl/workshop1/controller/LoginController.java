package nl.workshop1.controller;

import nl.workshop1.dao.AccountDAO;
import nl.workshop1.dao.DAOFactory;
import nl.workshop1.domain.Account;

/**
 *
 * @author Vosjes
 */
public class LoginController {
    
    public boolean compareData(String username, String wachtwoord) {
        
        boolean comparison = false;
        
        // Verkrijg data van de database via AccountDAO en vergelijk
        AccountDAO accountDAO = DAOFactory.getDAOFactory().getAccountDAO();
        try {
            Account account = accountDAO.selectAccount(username);
            if (account.getUsername().compareTo(username) == 0 &&
                    account.getWachtwoord().compareTo(wachtwoord) == 0) {
                comparison = true;
            }
        } catch (NullPointerException ex) {
            comparison = false;
        }
        
        return comparison;
    }
}
