package nl.workshop1.controller;

import java.util.ArrayList;
import nl.workshop1.dao.AccountDAO;
import nl.workshop1.dao.DAOFactory;
import nl.workshop1.domain.Account;
import nl.workshop1.utility.PasswordStorage;

/**
 *
 * @author Vosjes
 */
public class AccountController {
    
    public boolean insertAccount(Account account) {
        AccountDAO accountDAO = DAOFactory.getDAOFactory().getAccountDAO();
        return accountDAO.insertAccount(account);
    }
    
    public Account selectAccount(int id) {
        AccountDAO accountDAO = DAOFactory.getDAOFactory().getAccountDAO();
        return accountDAO.selectAccount(id);
    }
    
    public ArrayList<Account> selectAccounts() {
        AccountDAO accountDAO = DAOFactory.getDAOFactory().getAccountDAO();
        return accountDAO.selectAccounts();
    }
    
    public boolean updateAccount(Account account) {
        AccountDAO accountDAO = DAOFactory.getDAOFactory().getAccountDAO();
        return accountDAO.updateAccount(account);
    }
    
    public boolean deleteAccount(int id) {
        AccountDAO accountDAO = DAOFactory.getDAOFactory().getAccountDAO();
        return accountDAO.deleteAccount(id);
    }
    
    public boolean validatePassword(String wachtwoord) {
        
        boolean validation = false;
        
        AccountDAO accountDAO = DAOFactory.getDAOFactory().getAccountDAO();
        Account account = accountDAO.selectAccount(LoginController.loginnaam);
        if (PasswordStorage.verifyPassword(wachtwoord, account.getWachtwoord())) {
            validation = true;
        }
        return validation;
    }
}
