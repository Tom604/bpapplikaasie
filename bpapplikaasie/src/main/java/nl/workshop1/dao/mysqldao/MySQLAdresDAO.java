package nl.workshop1.dao.mysqldao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import nl.workshop1.dao.AdresDAO;
import nl.workshop1.domain.Adres;
import nl.workshop1.domain.Klant;
import nl.workshop1.utility.HikariCPDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Vosjes
 */
public class MySQLAdresDAO implements AdresDAO {

    static Logger log = LoggerFactory.getLogger(MySQLAdresDAO.class);
    
    /*
    Alle ISUD methodes uit interface en evt. extra methodes specifiek voor MySQLAdresDAO object
    */
    
    @Override
    public boolean insertAdres(Adres adres) {
        
        int updateExecuted = 0;
        PreparedStatement preparedStatement;
        
        try (Connection connection = HikariCPDataSource.getConnection()) {
            log.debug("Database connected through insertAdres");
            
            preparedStatement = connection.prepareStatement(
                    "INSERT INTO adres (straatnaam, huisnummer, toevoeging, postcode, " +
                    "woonplaats, adrestype, klant_id) VALUES (?, ?, ?, ?, ?, ?, " +
                    "(SELECT id FROM klant WHERE id = ?))");
            preparedStatement.setString(1, adres.getStraatnaam());
            preparedStatement.setInt(2, adres.getHuisnummer());
            preparedStatement.setString(3, adres.getToevoeging());
            preparedStatement.setString(4, adres.getPostcode());
            preparedStatement.setString(5, adres.getWoonplaats());
            preparedStatement.setString(6, adres.getAdrestype());
            preparedStatement.setInt(7, adres.getKlant().getId());
            
            updateExecuted = preparedStatement.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
            log.warn("Exception catched in insertAdres");
        }
        
        return updateExecuted != 0;
    }

    @Override
    public Adres selectAdres(int id) {
        
        Klant klant = new Klant();
        Adres adres = new Adres();
        PreparedStatement preparedStatement;
        
        try (Connection connection = HikariCPDataSource.getConnection()) {
            log.debug("Database connected through selectAdres-id");
            
            preparedStatement = connection.prepareStatement(
                    "SELECT id, straatnaam, huisnummer, toevoeging, postcode, " +
                    "woonplaats, adrestype, klant_id FROM adres WHERE id = ?");
            preparedStatement.setInt(1, id);
        
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                adres.setId(resultSet.getInt("id"));
                adres.setStraatnaam(resultSet.getString("straatnaam"));
                adres.setHuisnummer(resultSet.getInt("huisnummer"));
                adres.setToevoeging(resultSet.getString("toevoeging"));
                adres.setPostcode(resultSet.getString("postcode"));
                adres.setWoonplaats(resultSet.getString("woonplaats"));
                adres.setAdrestype(resultSet.getString("adrestype"));
                klant.setId(resultSet.getInt("klant_id"));
                adres.setKlant(klant);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            log.warn("Exception catched in selectAdres-id");
        }
        
        return adres;
    }
    
    @Override
    public ArrayList<Adres> selectAdressen() {
        
        ArrayList<Adres> adressen = new ArrayList<>();
        Klant klant;
        Adres adres;
        
        try (Connection connection = HikariCPDataSource.getConnection()) {
            log.debug("Database connected through selectAdressen");
            
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(
                    "SELECT id, straatnaam, huisnummer, toevoeging, postcode, " +
                    "woonplaats, adrestype, klant_id FROM adres");
        
            while (resultSet.next()) {
                adres = new Adres();
                klant = new Klant();
                adres.setId(resultSet.getInt("id"));
                adres.setStraatnaam(resultSet.getString("straatnaam"));
                adres.setHuisnummer(resultSet.getInt("huisnummer"));
                adres.setToevoeging(resultSet.getString("toevoeging"));
                adres.setPostcode(resultSet.getString("postcode"));
                adres.setWoonplaats(resultSet.getString("woonplaats"));
                adres.setAdrestype(resultSet.getString("adrestype"));
                klant.setId(resultSet.getInt("klant_id"));
                adres.setKlant(klant);
                adressen.add(adres);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            log.warn("Exception catched in selectAdressen");
        }
        
        return adressen;
    }
    
    @Override
    public ArrayList<Adres> selectAdressen(int klantId) {
        
        ArrayList<Adres> adressen = new ArrayList<>();
        Klant klant;
        Adres adres;
        PreparedStatement preparedStatement;
        
        try (Connection connection = HikariCPDataSource.getConnection()) {
            log.debug("Database connected through selectAdressen-id");
            
            preparedStatement = connection.prepareStatement(
                    "SELECT id, straatnaam, huisnummer, toevoeging, postcode, " +
                    "woonplaats, adrestype, klant_id FROM adres WHERE klant_id = ?");
            preparedStatement.setInt(1, klantId);
            
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                adres = new Adres();
                klant = new Klant();
                adres.setId(resultSet.getInt("id"));
                adres.setStraatnaam(resultSet.getString("straatnaam"));
                adres.setHuisnummer(resultSet.getInt("huisnummer"));
                adres.setToevoeging(resultSet.getString("toevoeging"));
                adres.setPostcode(resultSet.getString("postcode"));
                adres.setWoonplaats(resultSet.getString("woonplaats"));
                adres.setAdrestype(resultSet.getString("adrestype"));
                klant.setId(resultSet.getInt("klant_id"));
                adres.setKlant(klant);
                adressen.add(adres);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            log.warn("Exception catched in selectAdressen-id");
        }
        
        return adressen;
    }

    @Override
    public boolean updateAdres(Adres adres) {
        
        int updateExecuted = 0;
        PreparedStatement preparedStatement;
        
        try (Connection connection = HikariCPDataSource.getConnection()) {
            log.debug("Database connected through updateAdres");
            
            preparedStatement = connection.prepareStatement(
                    "UPDATE adres SET straatnaam = ?, huisnummer = ?, toevoeging = ?, " +
                    "postcode = ?, woonplaats = ?, adrestype = ?, klant_id = ? WHERE id = ?");
            preparedStatement.setString(1, adres.getStraatnaam());
            preparedStatement.setInt(2, adres.getHuisnummer());
            preparedStatement.setString(3, adres.getToevoeging());
            preparedStatement.setString(4, adres.getPostcode());
            preparedStatement.setString(5, adres.getWoonplaats());
            preparedStatement.setString(6, adres.getAdrestype());
            preparedStatement.setInt(7, adres.getKlant().getId());
            preparedStatement.setInt(8, adres.getId());
        
            updateExecuted = preparedStatement.executeUpdate();
            
        } catch (SQLException ex) {
            ex.printStackTrace();
            log.warn("Exception catched in updateAdres");
        }
        
        return updateExecuted != 0;
    }

    @Override
    public boolean deleteAdres(int id) {
        
        int updateExecuted = 0;
        PreparedStatement preparedStatement;
        
        try (Connection connection = HikariCPDataSource.getConnection()) {
            log.debug("Database connected through deleteAdres-id");
            
            preparedStatement = connection.prepareStatement(
                    "DELETE FROM adres WHERE id = ?");
            preparedStatement.setInt(1, id);
        
            updateExecuted = preparedStatement.executeUpdate();
            
        } catch (SQLException ex) {
            ex.printStackTrace();
            log.warn("Exception catched in deleteAdres-id");
        }
        
        return updateExecuted != 0;
    }
    
}
