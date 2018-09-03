package nl.workshop1.dao.mysqldao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import nl.workshop1.dao.BestelregelDAO;
import nl.workshop1.domain.Artikel;
import nl.workshop1.domain.Bestelling;
import nl.workshop1.domain.Bestelregel;
import nl.workshop1.utility.DatabaseConnection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Vosjes
 */
public class MySQLBestelregelDAO implements BestelregelDAO {

    static Logger log = LoggerFactory.getLogger(MySQLBestelregelDAO.class);
    
    /*
    Alle ISUD methodes uit interface en evt. extra methodes specifiek voor MySQLBestelregelDAO object
    */
    
    @Override
    public boolean insertBestelregel(Bestelregel bestelregel) {
        
        int updateExecuted = 0;
        PreparedStatement preparedStatement;
        
        try (Connection connection = DatabaseConnection.getConnection()) {
            log.debug("Database connected through insertBestelregel");
            
            preparedStatement = connection.prepareStatement(
                    "INSERT INTO bestelregel (aantal, bestelling_id, artikel_id) " +
                    "VALUES (?, (SELECT id FROM bestelling WHERE id = ?), " +
                    "(SELECT id FROM artikel WHERE id = ?))");
            preparedStatement.setInt(1, bestelregel.getAantal());
            preparedStatement.setInt(2, bestelregel.getBestelling().getId());
            preparedStatement.setInt(3, bestelregel.getArtikel().getId());
            
            updateExecuted = preparedStatement.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
            log.warn("Exception catched in insertBestelregel");
        }
        
        return updateExecuted != 0;
    }

    @Override
    public Bestelregel selectBestelregel(int id) {
        
        Bestelling bestelling = new Bestelling();
        Artikel artikel = new Artikel();
        Bestelregel bestelregel = new Bestelregel();
        PreparedStatement preparedStatement;
        
        try (Connection connection = DatabaseConnection.getConnection()) {
            log.debug("Database connected through selectBestelregel");
            
            preparedStatement = connection.prepareStatement(
                    "SELECT id, aantal, bestelling_id, artikel_id " +
                    "FROM bestelregel WHERE id = ?");
            preparedStatement.setInt(1, id);
        
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                bestelregel.setId(resultSet.getInt("id"));
                bestelregel.setAantal(resultSet.getInt("aantal"));
                bestelling.setId(resultSet.getInt("bestelling_id"));
                bestelregel.setBestelling(bestelling);
                artikel.setId(resultSet.getInt("artikel_id"));
                bestelregel.setArtikel(artikel);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            log.warn("Exception catched in selectBestelregel");
        }
        
        return bestelregel;
    }

    @Override
    public boolean updateBestelregel(Bestelregel bestelregel) {
        
        int updateExecuted = 0;
        PreparedStatement preparedStatement;
        
        try (Connection connection = DatabaseConnection.getConnection()) {
            log.debug("Database connected through updateBestelregel");
            
            preparedStatement = connection.prepareStatement(
                    "UPDATE bestelregel SET aantal = ?, bestelling_id = ?, " +
                    "artikel_id = ? WHERE id = ?");
            preparedStatement.setInt(1, bestelregel.getAantal());
            preparedStatement.setInt(2, bestelregel.getBestelling().getId());
            preparedStatement.setInt(3, bestelregel.getArtikel().getId());
            preparedStatement.setInt(4, bestelregel.getId());
        
            updateExecuted = preparedStatement.executeUpdate();
            
        } catch (SQLException ex) {
            ex.printStackTrace();
            log.warn("Exception catched in updateBestelregel");
        }
        
        return updateExecuted != 0;
    }

    @Override
    public boolean deleteBestelregel(int id) {
        
        int updateExecuted = 0;
        PreparedStatement preparedStatement;
        
        try (Connection connection = DatabaseConnection.getConnection()) {
            log.debug("Database connected through deleteBestelregel");
            
            preparedStatement = connection.prepareStatement(
                    "DELETE FROM bestelregel WHERE id = ?");
            preparedStatement.setInt(1, id);
        
            updateExecuted = preparedStatement.executeUpdate();
            
        } catch (SQLException ex) {
            ex.printStackTrace();
            log.warn("Exception catched in deleteBestelregel");
        }
        
        return updateExecuted != 0;
    }
}
