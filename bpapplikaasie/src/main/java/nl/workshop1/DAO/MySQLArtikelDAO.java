package nl.workshop1.DAO;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.xml.parsers.ParserConfigurationException;
import nl.workshop1.domain.Artikel;
import nl.workshop1.utility.DatabaseConnection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.SAXException;

/**
 *
 * @author Vosjes
 */
public class MySQLArtikelDAO extends MySQLDAOFactory implements ArtikelDAO {
    
    /*
    Alle ISUD methodes uit interface en evt. extra methodes specifiek voor MySQLArtikelDAO object
    */
    
    private PreparedStatement preparedStatement;
    //Connection connection;
    static Logger log = LoggerFactory.getLogger(MySQLArtikelDAO.class);
    
    @Override
    public boolean insertArtikel(String naam, BigDecimal prijs, int voorraad) {
        
        int temp = 0;
        
        try (Connection connection = DatabaseConnection.getConnection()) {
            log.debug("Database connected through insertArtikel");
            
            preparedStatement = connection.prepareStatement(
                    "insert into artikel (naam, prijs, voorraad) values (?, ?, ?)");
            preparedStatement.setString(1, naam);
            preparedStatement.setBigDecimal(2, prijs);
            preparedStatement.setInt(3, voorraad);
            
            temp = preparedStatement.executeUpdate();
            
        } catch (ParserConfigurationException | SAXException | IOException |
                ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
            log.warn("Exception catched in insertArtikel");
        }
        
        return temp != 0;
    }
    
    @Override
    public Artikel selectArtikel(int id) {

        Artikel artikel1 = new Artikel();
        
        try (Connection connection = DatabaseConnection.getConnection()) {
            log.debug("Database connected through selectArtikel");
            
            preparedStatement = connection.prepareStatement(
                    "select id, naam, prijs, voorraad from artikel where id = ?");
            preparedStatement.setInt(1, id);
        
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                artikel1.setId(resultSet.getInt("id"));
                artikel1.setNaam(resultSet.getString("naam"));
                artikel1.setPrijs(resultSet.getBigDecimal("prijs"));
                artikel1.setVoorraad(resultSet.getInt("voorraad"));
            }
        } catch (ParserConfigurationException | SAXException | IOException |
                ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
            log.warn("Exception catched in selectArtikel");
        }
        
        return artikel1;
    }
    
    @Override
    public boolean updateArtikel(int id, String naam, BigDecimal prijs, int voorraad) {
        
        int temp = 0;
        
        try (Connection connection = DatabaseConnection.getConnection()) {
            log.debug("Database connected through updateArtikel");
            
            preparedStatement = connection.prepareStatement(
                    "update artikel set naam = ?, prijs = ?, voorraad = ? where id = ?");
        
            preparedStatement.setString(1, naam);
            preparedStatement.setBigDecimal(2, prijs);
            preparedStatement.setInt(3, voorraad);
            preparedStatement.setInt(4, id);
        
            temp = preparedStatement.executeUpdate();
            
        } catch (ParserConfigurationException | SAXException | IOException |
                ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
            log.warn("Exception catched in updateArtikel");
        }
        
        return temp != 0;
    }
    
    @Override
    public boolean deleteArtikel(int id) {
        
        int temp = 0;
        
        try (Connection connection = DatabaseConnection.getConnection()) {
            log.debug("Database connected through deleteArtikel");
            
            preparedStatement = connection.prepareStatement(
                "delete from artikel where id = ?");
            preparedStatement.setInt(1, id);
        
            temp = preparedStatement.executeUpdate();
            
        } catch (ParserConfigurationException | SAXException | IOException |
                ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
            log.warn("Exception catched in deleteArtikel");
        }
        
        return temp != 0;
    }
}
