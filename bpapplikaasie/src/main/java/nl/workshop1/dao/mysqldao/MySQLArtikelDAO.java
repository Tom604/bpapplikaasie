package nl.workshop1.dao.mysqldao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import nl.workshop1.dao.ArtikelDAO;
import nl.workshop1.domain.Artikel;
import nl.workshop1.utility.DatabaseConnection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Vosjes
 */
public class MySQLArtikelDAO implements ArtikelDAO {
    
    /*
    Alle ISUD methodes uit interface en evt. extra methodes specifiek voor MySQLArtikelDAO object
    */
    
    static Logger log = LoggerFactory.getLogger(MySQLArtikelDAO.class);
    
    @Override
    public boolean insertArtikel(Artikel artikel) {
        
        int updateExecuted = 0;
        PreparedStatement preparedStatement;
        
        try (Connection connection = DatabaseConnection.getConnection()) {
            log.debug("Database connected through insertArtikel");
            
            preparedStatement = connection.prepareStatement(
                    "insert into artikel (naam, prijs, voorraad) values (?, ?, ?)");
            preparedStatement.setString(1, artikel.getNaam());
            preparedStatement.setBigDecimal(2, artikel.getPrijs());
            preparedStatement.setInt(3, artikel.getVoorraad());
            
            updateExecuted = preparedStatement.executeUpdate();
            
        } catch (SQLException ex) {
            ex.printStackTrace();
            log.warn("Exception catched in insertArtikel");
        }
        
        return updateExecuted != 0;
    }
    
    @Override
    public Artikel selectArtikel(int id) {

        Artikel artikel = new Artikel();
        PreparedStatement preparedStatement;
        
        try (Connection connection = DatabaseConnection.getConnection()) {
            log.debug("Database connected through selectArtikel");
            
            preparedStatement = connection.prepareStatement(
                    "select id, naam, prijs, voorraad from artikel where id = ?");
            preparedStatement.setInt(1, id);
        
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                artikel.setId(resultSet.getInt("id"));
                artikel.setNaam(resultSet.getString("naam"));
                artikel.setPrijs(resultSet.getBigDecimal("prijs"));
                artikel.setVoorraad(resultSet.getInt("voorraad"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            log.warn("Exception catched in selectArtikel");
        }
        
        return artikel;
    }
    
    @Override
    public boolean updateArtikel(Artikel artikel) {
        
        int updateExecuted = 0;
        PreparedStatement preparedStatement;
        
        try (Connection connection = DatabaseConnection.getConnection()) {
            log.debug("Database connected through updateArtikel");
            
            preparedStatement = connection.prepareStatement(
                    "update artikel set naam = ?, prijs = ?, voorraad = ? where id = ?");
            preparedStatement.setString(1, artikel.getNaam());
            preparedStatement.setBigDecimal(2, artikel.getPrijs());
            preparedStatement.setInt(3, artikel.getVoorraad());
            preparedStatement.setInt(4, artikel.getId());
        
            updateExecuted = preparedStatement.executeUpdate();
            
        } catch (SQLException ex) {
            ex.printStackTrace();
            log.warn("Exception catched in updateArtikel");
        }
        
        return updateExecuted != 0;
    }
    
    @Override
    public boolean deleteArtikel(int id) {
        
        int updateExecuted = 0;
        PreparedStatement preparedStatement;
        
        try (Connection connection = DatabaseConnection.getConnection()) {
            log.debug("Database connected through deleteArtikel");
            
            preparedStatement = connection.prepareStatement(
                    "delete from artikel where id = ?");
            preparedStatement.setInt(1, id);
        
            updateExecuted = preparedStatement.executeUpdate();
            
        } catch (SQLException ex) {
            ex.printStackTrace();
            log.warn("Exception catched in deleteArtikel");
        }
        
        return updateExecuted != 0;
    }
}
