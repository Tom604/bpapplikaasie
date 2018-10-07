package nl.workshop1.dao.mysqldao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import nl.workshop1.dao.BestellingDAO;
import nl.workshop1.domain.Bestelling;
import nl.workshop1.domain.Klant;
import nl.workshop1.utility.HikariCPDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Vosjes
 */
public class MySQLBestellingDAO implements BestellingDAO {

    static Logger log = LoggerFactory.getLogger(MySQLBestellingDAO.class);
    
    /*
    Alle ISUD methodes uit interface en evt. extra methodes specifiek voor MySQLBestellingDAO object
    */
    
    @Override
    public Bestelling insertBestelling(Bestelling bestelling) {
        
        PreparedStatement preparedStatement;
        
        try (Connection connection = HikariCPDataSource.getConnection()) {
            log.debug("Database connected through insertBestelling");
            
            preparedStatement = connection.prepareStatement(
                    "INSERT INTO bestelling (totaalprijs, datum_tijd, klant_id) " +
                    "VALUES (?, ?, (SELECT id FROM klant WHERE id = ?))",
                    PreparedStatement.RETURN_GENERATED_KEYS);
            preparedStatement.setBigDecimal(1, bestelling.getTotaalprijs());
            preparedStatement.setTimestamp(2, Timestamp.valueOf(bestelling.getDatumTijd()));
            preparedStatement.setInt(3, bestelling.getKlant().getId());
            
            preparedStatement.executeUpdate();
            
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                bestelling.setId(resultSet.getInt(1));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            log.warn("Exception catched in insertBestelling");
        }
        
        return bestelling;
    }

    @Override
    public Bestelling selectBestelling(int id) {
        
        Klant klant = new Klant();
        Bestelling bestelling = new Bestelling();
        PreparedStatement preparedStatement;
        
        try (Connection connection = HikariCPDataSource.getConnection()) {
            log.debug("Database connected through selectBestelling-id");
            
            preparedStatement = connection.prepareStatement(
                    "SELECT id, totaalprijs, datum_tijd, klant_id " +
                    "FROM bestelling WHERE id = ?");
            preparedStatement.setInt(1, id);
        
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                bestelling.setId(resultSet.getInt("id"));
                bestelling.setTotaalprijs(resultSet.getBigDecimal("totaalprijs"));
                Timestamp timestamp = resultSet.getTimestamp("datum_tijd");
                bestelling.setDatumTijd(timestamp.toLocalDateTime());
                klant.setId(resultSet.getInt("klant_id"));
                bestelling.setKlant(klant);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            log.warn("Exception catched in selectBestelling-id");
        }
        
        return bestelling;
    }
    
    @Override
    public ArrayList<Bestelling> selectBestellingen() {
        
        ArrayList<Bestelling> bestellingen = new ArrayList<>();
        Klant klant;
        Bestelling bestelling;
        
        try (Connection connection = HikariCPDataSource.getConnection()) {
            log.debug("Database connected through selectBestellingen");
            
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(
                    "SELECT id, totaalprijs, datum_tijd, klant_id FROM bestelling");
        
            while (resultSet.next()) {
                klant = new Klant();
                bestelling = new Bestelling();
                bestelling.setId(resultSet.getInt("id"));
                bestelling.setTotaalprijs(resultSet.getBigDecimal("totaalprijs"));
                Timestamp timestamp = resultSet.getTimestamp("datum_tijd");
                bestelling.setDatumTijd(timestamp.toLocalDateTime());
                klant.setId(resultSet.getInt("klant_id"));
                bestelling.setKlant(klant);
                bestellingen.add(bestelling);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            log.warn("Exception catched in selectBestellingen");
        }
        
        return bestellingen;
    }

    @Override
    public boolean updateBestelling(Bestelling bestelling) {
        
        int updateExecuted = 0;
        PreparedStatement preparedStatement;
        
        try (Connection connection = HikariCPDataSource.getConnection()) {
            log.debug("Database connected through updateBestelling");
            
            preparedStatement = connection.prepareStatement(
                    "UPDATE bestelling SET totaalprijs = ?, datum_tijd = ?, " +
                    "klant_id = ? WHERE id = ?");
            preparedStatement.setBigDecimal(1, bestelling.getTotaalprijs());
            preparedStatement.setTimestamp(2, Timestamp.valueOf(bestelling.getDatumTijd()));
            preparedStatement.setInt(3, bestelling.getKlant().getId());
            preparedStatement.setInt(4, bestelling.getId());
        
            updateExecuted = preparedStatement.executeUpdate();
            
        } catch (SQLException ex) {
            ex.printStackTrace();
            log.warn("Exception catched in updateBestelling");
        }
        
        return updateExecuted != 0;
    }

    @Override
    public boolean deleteBestelling(int id) {
        
        int updateExecuted = 0;
        PreparedStatement preparedStatement;
        
        try (Connection connection = HikariCPDataSource.getConnection()) {
            log.debug("Database connected through deleteBestelling-id");
            
            preparedStatement = connection.prepareStatement(
                    "DELETE FROM bestelling WHERE id = ?");
            preparedStatement.setInt(1, id);
        
            updateExecuted = preparedStatement.executeUpdate();
            
        } catch (SQLException ex) {
            ex.printStackTrace();
            log.warn("Exception catched in deleteBestelling-id");
        }
        
        return updateExecuted != 0;
    }
}
