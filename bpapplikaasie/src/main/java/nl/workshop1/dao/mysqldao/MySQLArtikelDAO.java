package nl.workshop1.dao.mysqldao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import nl.workshop1.dao.ArtikelDAO;
import nl.workshop1.domain.Artikel;
import nl.workshop1.utility.HikariCPDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Vosjes
 */
public class MySQLArtikelDAO implements ArtikelDAO {
    
    static Logger log = LoggerFactory.getLogger(MySQLArtikelDAO.class);
    
    /*
    Alle ISUD methodes uit interface en evt. extra methodes specifiek voor MySQLArtikelDAO object
    */
    
    @Override
    public boolean insertArtikel(Artikel artikel) {
        
        int updateExecuted = 0;
        PreparedStatement preparedStatement;
        
        try (Connection connection = HikariCPDataSource.getConnection()) {
            log.debug("Database connected through insertArtikel");
            
            preparedStatement = connection.prepareStatement(
                    "INSERT INTO artikel (naam, prijs, voorraad) VALUES (?, ?, ?)");
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
        
        try (Connection connection = HikariCPDataSource.getConnection()) {
            log.debug("Database connected through selectArtikel-id");
            
            preparedStatement = connection.prepareStatement(
                    "SELECT id, naam, prijs, voorraad FROM artikel WHERE id = ?");
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
            log.warn("Exception catched in selectArtikel-id");
        }
        
        return artikel;
    }
    
    @Override
    public Artikel selectArtikel(String naam) {

        Artikel artikel = new Artikel();
        PreparedStatement preparedStatement;
        
        try (Connection connection = HikariCPDataSource.getConnection()) {
            log.debug("Database connected through selectArtikel-naam");
            
            preparedStatement = connection.prepareStatement(
                    "SELECT id, naam, prijs, voorraad FROM artikel WHERE naam = ?");
            preparedStatement.setString(1, naam);
        
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                artikel.setId(resultSet.getInt("id"));
                artikel.setNaam(resultSet.getString("naam"));
                artikel.setPrijs(resultSet.getBigDecimal("prijs"));
                artikel.setVoorraad(resultSet.getInt("voorraad"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            log.warn("Exception catched in selectArtikel-naam");
        }
        
        return artikel;
    }
    
    @Override
    public ArrayList<Artikel> selectArtikelen() {
        
        ArrayList<Artikel> artikelen = new ArrayList<>();
        Artikel artikel;
        
        try (Connection connection = HikariCPDataSource.getConnection()) {
            log.debug("Database connected through selectArtikelen");
            
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(
                    "SELECT id, naam, prijs, voorraad FROM artikel");
            
            while (resultSet.next()) {
                artikel = new Artikel();
                artikel.setId(resultSet.getInt("id"));
                artikel.setNaam(resultSet.getString("naam"));
                artikel.setPrijs(resultSet.getBigDecimal("prijs"));
                artikel.setVoorraad(resultSet.getInt("voorraad"));
                artikelen.add(artikel);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            log.warn("Exception catched in selectArtikelen");
        }
        
        return artikelen;
    }
    
    @Override
    public boolean updateArtikel(Artikel artikel) {
        
        int updateExecuted = 0;
        PreparedStatement preparedStatement;
        
        try (Connection connection = HikariCPDataSource.getConnection()) {
            log.debug("Database connected through updateArtikel");
            
            preparedStatement = connection.prepareStatement(
                    "UPDATE artikel SET naam = ?, prijs = ?, voorraad = ? WHERE id = ?");
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
        
        try (Connection connection = HikariCPDataSource.getConnection()) {
            log.debug("Database connected through deleteArtikel-id");
            
            preparedStatement = connection.prepareStatement(
                    "DELETE FROM artikel WHERE id = ?");
            preparedStatement.setInt(1, id);
        
            updateExecuted = preparedStatement.executeUpdate();
            
        } catch (SQLException ex) {
            ex.printStackTrace();
            log.warn("Exception catched in deleteArtikel-id");
        }
        
        return updateExecuted != 0;
    }
    
    @Override
    public boolean deleteArtikel(String naam) {
        
        int updateExecuted = 0;
        PreparedStatement preparedStatement;
        
        try (Connection connection = HikariCPDataSource.getConnection()) {
            log.debug("Database connected through deleteArtikel-naam");
            
            preparedStatement = connection.prepareStatement(
                    "DELETE FROM artikel WHERE id = ?");
            preparedStatement.setString(1, naam);
        
            updateExecuted = preparedStatement.executeUpdate();
            
        } catch (SQLException ex) {
            ex.printStackTrace();
            log.warn("Exception catched in deleteArtikel-naam");
        }
        
        return updateExecuted != 0;
    }
}
