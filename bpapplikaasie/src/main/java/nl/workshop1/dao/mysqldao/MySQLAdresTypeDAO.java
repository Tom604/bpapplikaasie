package nl.workshop1.dao.mysqldao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import nl.workshop1.dao.AdresTypeDAO;
import nl.workshop1.domain.AdresType;
import nl.workshop1.utility.DatabaseConnection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Vosjes
 */
public class MySQLAdresTypeDAO implements AdresTypeDAO {

    /*
    Alle ISUD methodes uit interface en evt. extra methodes specifiek voor MySQLAdresTypeDAO object
    */
    
    static Logger log = LoggerFactory.getLogger(MySQLAdresTypeDAO.class);
    
    @Override
    public boolean insertAdresType(AdresType adresType) {
        
        int updateExecuted = 0;
        PreparedStatement preparedStatement;
        
        try (Connection connection = DatabaseConnection.getConnection()) {
            log.debug("Database connected through insertAdresType");
            
            preparedStatement = connection.prepareStatement(
                    "insert into adrestype (id, type) values (?, ?)");
            preparedStatement.setString(1, String.valueOf(adresType.getId()));
            preparedStatement.setString(2, adresType.getType());
            
            updateExecuted = preparedStatement.executeUpdate();
            
        } catch (SQLException ex) {
            ex.printStackTrace();
            log.warn("Exception catched in insertAdresType");
        }
        
        return updateExecuted != 0;
    }
    
    @Override
    public AdresType selectAdresType(char id) {
        
        AdresType adresType1 = new AdresType();
        PreparedStatement preparedStatement;
        
        try (Connection connection = DatabaseConnection.getConnection()) {
            log.debug("Database connected through selectAdresType");
            
            preparedStatement = connection.prepareStatement(
                    "select id, type from adrestype where id = ?");
            preparedStatement.setString(1, String.valueOf(id));
        
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                adresType1.setId((resultSet.getString("id")).charAt(0));
                adresType1.setType(resultSet.getString("type"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            log.warn("Exception catched in selectAdresType");
        }
        
        return adresType1;
    }

    @Override
    public boolean updateAdresType(AdresType adresType) {
        
        int updateExecuted = 0;
        PreparedStatement preparedStatement;
        
        try (Connection connection = DatabaseConnection.getConnection()) {
            log.debug("Database connected through updateAdresType");
            
            preparedStatement = connection.prepareStatement(
                    "update adrestype set type = ? where id = ?");
            preparedStatement.setString(1, adresType.getType());
            preparedStatement.setString(2, String.valueOf(adresType.getId()));
        
            updateExecuted = preparedStatement.executeUpdate();
            
        } catch (SQLException ex) {
            ex.printStackTrace();
            log.warn("Exception catched in updateAdresType");
        }
        
        return updateExecuted != 0;
    }

    @Override
    public boolean deleteAdresType(char id) {
        
        int updateExecuted = 0;
        PreparedStatement preparedStatement;
        
        try (Connection connection = DatabaseConnection.getConnection()) {
            log.debug("Database connected through deleteAdresType");
            
            preparedStatement = connection.prepareStatement(
                    "delete from adrestype where id = ?");
            preparedStatement.setString(1, String.valueOf(id));
        
            updateExecuted = preparedStatement.executeUpdate();
            
        } catch (SQLException ex) {
            ex.printStackTrace();
            log.warn("Exception catched in deleteAdresType");
        }
        
        return updateExecuted != 0;
    }
}
