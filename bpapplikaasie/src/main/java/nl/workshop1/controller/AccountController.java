package nl.workshop1.controller;

import java.util.ArrayList;
import nl.workshop1.dao.AccountDAO;
import nl.workshop1.dao.DAOFactory;
import nl.workshop1.domain.Account;

/**
 *
 * @author Vosjes
 */
public class AccountController {
    
    public boolean insertAccount(Account account) {
        AccountDAO accountDAO = DAOFactory.getDAOFactory().getAccountDAO();
        return accountDAO.insertAccount(account);
    }
    
    public boolean insertKlantaccount(Account account) {
        /*
        Deze methode gebruiken om de insertKlantaccount(Account account) methode in
        de DAO aan te roepen (nog aanmaken). Ook insertAccount in de DAO nog aanpassen - 
        geen klant toevoegen aan het account.
        Anders doen:
        Gewoon de algemene insertAccount methode hierboven gebruiken, zorgen dat er in
        de view al een null klant gezet wordt.
        */
        
        return true;
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
}
