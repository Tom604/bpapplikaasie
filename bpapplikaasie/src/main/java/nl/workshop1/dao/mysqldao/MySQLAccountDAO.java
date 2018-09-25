package nl.workshop1.dao.mysqldao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import nl.workshop1.dao.AccountDAO;
import nl.workshop1.domain.Account;
import nl.workshop1.domain.Klant;
import nl.workshop1.utility.DatabaseConnection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Vosjes
 */
public class MySQLAccountDAO implements AccountDAO {

    static Logger log = LoggerFactory.getLogger(MySQLAccountDAO.class);
    
    /*
    Alle ISUD methodes uit interface en evt. extra methodes specifiek voor MySQLAccountDAO object
    */
    
    @Override
    public boolean insertAccount(Account account) {
        
        int updateExecuted = 0;
        PreparedStatement preparedStatement;
        
        try (Connection connection = DatabaseConnection.getConnection()) {
            log.debug("Database connected through insertAccount");
            
            preparedStatement = connection.prepareStatement(
                    "INSERT INTO account (username, wachtwoord, accounttype, klant_id) " +
                    "VALUES (?, ?, ?, (SELECT id FROM klant WHERE id = ?))");
            preparedStatement.setString(1, account.getUsername());
            preparedStatement.setString(2, account.getWachtwoord());
            preparedStatement.setString(3, account.getAccounttype());
            preparedStatement.setInt(4, account.getKlant().getId());
            
            updateExecuted = preparedStatement.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
            log.warn("Exception catched in insertAccount");
        }
        
        return updateExecuted != 0;
    }

    @Override
    public Account selectAccount(int id) {
        
        Klant klant = new Klant();
        Account account = new Account();
        PreparedStatement preparedStatement;
        
        try (Connection connection = DatabaseConnection.getConnection()) {
            log.debug("Database connected through selectAccount-id");
            
            preparedStatement = connection.prepareStatement(
                    "SELECT id, username, wachtwoord, accounttype, klant_id " +
                    "FROM account WHERE id = ?");
            preparedStatement.setInt(1, id);
        
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                account.setId(resultSet.getInt("id"));
                account.setUsername(resultSet.getString("username"));
                account.setWachtwoord(resultSet.getString("wachtwoord"));
                account.setAccounttype(resultSet.getString("accounttype"));
                klant.setId(resultSet.getInt("klant_id"));
                account.setKlant(klant);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            log.warn("Exception catched in selectAccount-id");
        }
        
        return account;
    }
    
    @Override
    public Account selectAccount(String username) {
        
        Klant klant = new Klant();
        Account account = new Account();
        PreparedStatement preparedStatement;
        
        try (Connection connection = DatabaseConnection.getConnection()) {
            log.debug("Database connected through selectAccount-username");
            
            preparedStatement = connection.prepareStatement(
                    "SELECT id, username, wachtwoord, accounttype, klant_id " +
                    "FROM account WHERE username = ?");
            preparedStatement.setString(1, username);
        
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                account.setId(resultSet.getInt("id"));
                account.setUsername(resultSet.getString("username"));
                account.setWachtwoord(resultSet.getString("wachtwoord"));
                account.setAccounttype(resultSet.getString("accounttype"));
                klant.setId(resultSet.getInt("klant_id"));
                account.setKlant(klant);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            log.warn("Exception catched in selectAccount-username");
        }
        
        return account;
    }
    
    @Override
    public ArrayList<Account> selectAccounts() {
        
        ArrayList<Account> accounts = new ArrayList<>();
        Klant klant;
        Account account;
        
        try (Connection connection = DatabaseConnection.getConnection()) {
            log.debug("Database connected through selectAccount-id");
            
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(
                    "SELECT id, username, wachtwoord, accounttype, klant_id " +
                    "FROM account");
        
            while (resultSet.next()) {
                account = new Account();
                klant = new Klant();
                account.setId(resultSet.getInt("id"));
                account.setUsername(resultSet.getString("username"));
                account.setWachtwoord(resultSet.getString("wachtwoord"));
                account.setAccounttype(resultSet.getString("accounttype"));
                klant.setId(resultSet.getInt("klant_id"));
                account.setKlant(klant);
                accounts.add(account);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            log.warn("Exception catched in selectAccount-id");
        }
        
        return accounts;
    }

    @Override
    public boolean updateAccount(Account account) {
        
        int updateExecuted = 0;
        PreparedStatement preparedStatement;
        
        try (Connection connection = DatabaseConnection.getConnection()) {
            log.debug("Database connected through updateAccount");
            
            preparedStatement = connection.prepareStatement(
                    "UPDATE account SET username = ?, wachtwoord = ?, " +
                    "accounttype = ?, klant_id = ? WHERE id = ?");
            preparedStatement.setString(1, account.getUsername());
            preparedStatement.setString(2, account.getWachtwoord());
            preparedStatement.setString(3, account.getAccounttype());
            preparedStatement.setInt(4, account.getKlant().getId());
            preparedStatement.setInt(5, account.getId());
        
            updateExecuted = preparedStatement.executeUpdate();
            
        } catch (SQLException ex) {
            ex.printStackTrace();
            log.warn("Exception catched in updateAccount");
        }
        
        return updateExecuted != 0;
    }

    @Override
    public boolean deleteAccount(int id) {
        
        int updateExecuted = 0;
        PreparedStatement preparedStatement;
        
        try (Connection connection = DatabaseConnection.getConnection()) {
            log.debug("Database connected through deleteAccount");
            
            preparedStatement = connection.prepareStatement(
                    "DELETE FROM account WHERE id = ?");
            preparedStatement.setInt(1, id);
        
            updateExecuted = preparedStatement.executeUpdate();
            
        } catch (SQLException ex) {
            ex.printStackTrace();
            log.warn("Exception catched in deleteAccount");
        }
        
        return updateExecuted != 0;
    }
}
