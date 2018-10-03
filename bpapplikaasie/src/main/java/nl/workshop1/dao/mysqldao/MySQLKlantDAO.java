package nl.workshop1.dao.mysqldao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import nl.workshop1.dao.KlantDAO;
import nl.workshop1.domain.Klant;
import nl.workshop1.utility.DatabaseConnection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Vosjes
 */
public class MySQLKlantDAO implements KlantDAO {

    static Logger log = LoggerFactory.getLogger(MySQLKlantDAO.class);
    
    /*
    Alle ISUD methodes uit interface en evt. extra methodes specifiek voor MySQLKlantDAO object
    */
    
    @Override
    public boolean insertKlant(Klant klant) {
        
        int updateExecuted = 0;
        PreparedStatement preparedStatement;
        
        try (Connection connection = DatabaseConnection.getConnection()) {
            log.debug("Database connected through insertKlant");
            
            preparedStatement = connection.prepareStatement(
                    "INSERT INTO klant (voornaam, achternaam, tussenvoegsel) VALUES (?, ?, ?)");
            preparedStatement.setString(1, klant.getVoornaam());
            preparedStatement.setString(2, klant.getAchternaam());
            preparedStatement.setString(3, klant.getTussenvoegsel());
            
            updateExecuted = preparedStatement.executeUpdate();
            
        } catch (SQLException ex) {
            ex.printStackTrace();
            log.warn("Exception catched in insertKlant");
        }
        
        return updateExecuted != 0;
    }

    @Override
    public Klant selectKlant(int id) {
        
        Klant klant = new Klant();
        PreparedStatement preparedStatement;
        
        try (Connection connection = DatabaseConnection.getConnection()) {
            log.debug("Database connected through selectKlant");
            
            preparedStatement = connection.prepareStatement(
                    "SELECT id, voornaam, achternaam, tussenvoegsel FROM klant WHERE id = ?");
            preparedStatement.setInt(1, id);
        
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                klant.setId(resultSet.getInt("id"));
                klant.setVoornaam(resultSet.getString("voornaam"));
                klant.setAchternaam(resultSet.getString("achternaam"));
                klant.setTussenvoegsel(resultSet.getString("tussenvoegsel"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            log.warn("Exception catched in selectKlant");
        }
        
        return klant;
    }
    
    @Override
    public Klant insertAndSelectKlant(Klant klant) {
        
        PreparedStatement preparedStatement;
        
        try (Connection connection = DatabaseConnection.getConnection()) {
            log.debug("Database connected through selectLastKlantAdded");
            
            preparedStatement = connection.prepareStatement(
                    "INSERT INTO klant (voornaam, achternaam, tussenvoegsel) VALUES (?, ?, ?)",
                    PreparedStatement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, klant.getVoornaam());
            preparedStatement.setString(2, klant.getAchternaam());
            preparedStatement.setString(3, klant.getTussenvoegsel());
            
            preparedStatement.executeUpdate();
            
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                klant = selectKlant(resultSet.getInt(1));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            log.warn("Exception catched in selectLastKlantAdded");
        }
        
        return klant;
    }
    
    @Override
    public ArrayList<Klant> selectKlanten() {
        
        ArrayList<Klant> klanten = new ArrayList<>();
        Klant klant;
        
        try (Connection connection = DatabaseConnection.getConnection()) {
            log.debug("Database connected through selectKlanten");
            
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(
                    "SELECT id, voornaam, achternaam, tussenvoegsel FROM klant");
            
            while (resultSet.next()) {
                klant = new Klant();
                klant.setId(resultSet.getInt("id"));
                klant.setVoornaam(resultSet.getString("voornaam"));
                klant.setAchternaam(resultSet.getString("achternaam"));
                klant.setTussenvoegsel(resultSet.getString("tussenvoegsel"));
                klanten.add(klant);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            log.warn("Exception catched in selectKlanten");
        }
        
        return klanten;
    }

    @Override
    public boolean updateKlant(Klant klant) {
        
        int updateExecuted = 0;
        PreparedStatement preparedStatement;
        
        try (Connection connection = DatabaseConnection.getConnection()) {
            log.debug("Database connected through updateKlant");
            
            preparedStatement = connection.prepareStatement(
                    "UPDATE klant SET voornaam = ?, achternaam = ?, tussenvoegsel = ? WHERE id = ?");
            preparedStatement.setString(1, klant.getVoornaam());
            preparedStatement.setString(2, klant.getAchternaam());
            preparedStatement.setString(3, klant.getTussenvoegsel());
            preparedStatement.setInt(4, klant.getId());
        
            updateExecuted = preparedStatement.executeUpdate();
            
        } catch (SQLException ex) {
            ex.printStackTrace();
            log.warn("Exception catched in updateKlant");
        }
        
        return updateExecuted != 0;
    }

    @Override
    public boolean deleteKlant(int id) {
        
        int updateExecuted = 0;
        PreparedStatement preparedStatement;
        
        try (Connection connection = DatabaseConnection.getConnection()) {
            log.debug("Database connected through deleteKlant");
            
            preparedStatement = connection.prepareStatement(
                    "DELETE FROM klant WHERE id = ?");
            preparedStatement.setInt(1, id);
        
            updateExecuted = preparedStatement.executeUpdate();
            
        } catch (SQLException ex) {
            ex.printStackTrace();
            log.warn("Exception catched in deleteKlant");
        }
        
        return updateExecuted != 0;
    }
}
