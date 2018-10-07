package nl.workshop1.dao.mysqldao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import nl.workshop1.dao.BestelregelDAO;
import nl.workshop1.domain.Artikel;
import nl.workshop1.domain.Bestelling;
import nl.workshop1.domain.Bestelregel;
import nl.workshop1.utility.HikariCPDataSource;
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
        
        try (Connection connection = HikariCPDataSource.getConnection()) {
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
        
        try (Connection connection = HikariCPDataSource.getConnection()) {
            log.debug("Database connected through selectBestelregel-id");
            
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
            log.warn("Exception catched in selectBestelregel-id");
        }
        
        return bestelregel;
    }
    
    @Override
    public ArrayList<Bestelregel> selectBestelregels() {
        
        ArrayList<Bestelregel> bestelregels = new ArrayList<>();
        Bestelling bestelling;
        Artikel artikel;
        Bestelregel bestelregel;
        
        try (Connection connection = HikariCPDataSource.getConnection()) {
            log.debug("Database connected through selectBestelregels");
            
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(
                    "SELECT id, aantal, bestelling_id, artikel_id FROM bestelregel");
            
            while (resultSet.next()) {
                bestelling = new Bestelling();
                artikel = new Artikel();
                bestelregel = new Bestelregel();
                bestelregel.setId(resultSet.getInt("id"));
                bestelregel.setAantal(resultSet.getInt("aantal"));
                bestelling.setId(resultSet.getInt("bestelling_id"));
                bestelregel.setBestelling(bestelling);
                artikel.setId(resultSet.getInt("artikel_id"));
                bestelregel.setArtikel(artikel);
                bestelregels.add(bestelregel);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            log.warn("Exception catched in selectBestelregels");
        }
        
        return bestelregels;
    }
    
    @Override
    public ArrayList<Bestelregel> selectBestelregels(int bestellingId) {
        
        ArrayList<Bestelregel> bestelregels = new ArrayList<>();
        Bestelling bestelling;
        Artikel artikel;
        Bestelregel bestelregel;
        PreparedStatement preparedStatement;
        
        try (Connection connection = HikariCPDataSource.getConnection()) {
            log.debug("Database connected through selectBestelregels-id");
            
            preparedStatement = connection.prepareStatement(
                    "SELECT id, aantal, bestelling_id, artikel_id " +
                    "FROM bestelregel WHERE bestelling_id = ?");
            preparedStatement.setInt(1, bestellingId);
        
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                bestelling = new Bestelling();
                artikel = new Artikel();
                bestelregel = new Bestelregel();
                bestelregel.setId(resultSet.getInt("id"));
                bestelregel.setAantal(resultSet.getInt("aantal"));
                bestelling.setId(resultSet.getInt("bestelling_id"));
                bestelregel.setBestelling(bestelling);
                artikel.setId(resultSet.getInt("artikel_id"));
                bestelregel.setArtikel(artikel);
                bestelregels.add(bestelregel);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            log.warn("Exception catched in selectBestelregels-id");
        }
        
        return bestelregels;
    }

    @Override
    public boolean updateBestelregel(Bestelregel bestelregel) {
        
        int updateExecuted = 0;
        PreparedStatement preparedStatement;
        
        try (Connection connection = HikariCPDataSource.getConnection()) {
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
        
        try (Connection connection = HikariCPDataSource.getConnection()) {
            log.debug("Database connected through deleteBestelregel-id");
            
            preparedStatement = connection.prepareStatement(
                    "DELETE FROM bestelregel WHERE id = ?");
            preparedStatement.setInt(1, id);
        
            updateExecuted = preparedStatement.executeUpdate();
            
        } catch (SQLException ex) {
            ex.printStackTrace();
            log.warn("Exception catched in deleteBestelregel-id");
        }
        
        return updateExecuted != 0;
    }
}
