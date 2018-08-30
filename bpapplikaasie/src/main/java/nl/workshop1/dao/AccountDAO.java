package nl.workshop1.dao;

import nl.workshop1.domain.Account;

/**
 *
 * @author Vosjes
 */
public interface AccountDAO {
    
    /*
    Alle ISUD (INSERT, SELECT, UPDATE, DELETE) methodes die door MySQLAccountDAO geïmplementeerd worden
    */
    
    public boolean insertAccount(Account account);
    public Account selectAccount(int id);
    public boolean updateAccount(Account account);
    public boolean deleteAccount(int id);
}
