package nl.workshop1.controller;

import java.util.ArrayList;
import nl.workshop1.dao.AccountDAO;
import nl.workshop1.dao.DAOFactory;
import nl.workshop1.domain.Account;
import nl.workshop1.domain.Klant;

/**
 *
 * @author Vosjes
 */
public class AccountController {
    
    public boolean insertAccount(String username, String wachtwoord, String accounttype, Klant klant) {
        AccountDAO accountDAO = DAOFactory.getDAOFactory().getAccountDAO();
        Account account = new Account();
        account.setUsername(username);
        account.setWachtwoord(wachtwoord);
        account.setAccounttype(accounttype);
        account.setKlant(klant);
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
    
    public boolean updateAccount(String username, String wachtwoord, String accounttype, Klant klant) {
        AccountDAO accountDAO = DAOFactory.getDAOFactory().getAccountDAO();
        Account account = new Account();
        account.setUsername(username);
        account.setWachtwoord(wachtwoord);
        account.setAccounttype(accounttype);
        account.setKlant(klant);
        return accountDAO.updateAccount(account);
    }
    
    public boolean deleteAccount(int id) {
        AccountDAO accountDAO = DAOFactory.getDAOFactory().getAccountDAO();
        return accountDAO.deleteAccount(id);
    }
}
