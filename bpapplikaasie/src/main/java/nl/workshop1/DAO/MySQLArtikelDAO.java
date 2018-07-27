package nl.workshop1.DAO;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;
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
    
    private int updateExecuted;
    private PreparedStatement preparedStatement;
    static Logger log = LoggerFactory.getLogger(MySQLArtikelDAO.class);
    
    @Override
    public boolean insertArtikel(Artikel artikel) {
        
        try (Connection connection = DatabaseConnection.getConnection()) {
            log.debug("Database connected through insertArtikel");
            
            preparedStatement = connection.prepareStatement(
                    "insert into bpapplikaasie.artikel (naam, prijs, voorraad) values (?, ?, ?)");
            preparedStatement.setString(1, artikel.getNaam());
            preparedStatement.setBigDecimal(2, artikel.getPrijs());
            preparedStatement.setInt(3, artikel.getVoorraad());
            
            updateExecuted = preparedStatement.executeUpdate();
            
        } catch (ParserConfigurationException | SAXException | IOException |
                ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
            log.warn("Exception catched in insertArtikel");
        }
        
        return updateExecuted != 0;
    }
    
    @Override
    public Artikel selectArtikel(int id) {

        Artikel artikel = new Artikel();
        
        try (Connection connection = DatabaseConnection.getConnection()) {
            log.debug("Database connected through selectArtikel");
            
            preparedStatement = connection.prepareStatement(
                    "select id, naam, prijs, voorraad from bpapplikaasie.artikel where id = ?");
            preparedStatement.setInt(1, id);
        
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                artikel.setId(resultSet.getInt("id"));
                artikel.setNaam(resultSet.getString("naam"));
                artikel.setPrijs(resultSet.getBigDecimal("prijs"));
                artikel.setVoorraad(resultSet.getInt("voorraad"));
            }
        } catch (ParserConfigurationException | SAXException | IOException |
                ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
            log.warn("Exception catched in selectArtikel");
        }
        
        return artikel;
    }
    
    @Override
    public boolean updateArtikel(Artikel artikel) {
        
        try (Connection connection = DatabaseConnection.getConnection()) {
            log.debug("Database connected through updateArtikel");
            
            preparedStatement = connection.prepareStatement(
                    "update bpapplikaasie.artikel set naam = ?, prijs = ?, voorraad = ? where id = ?");
            preparedStatement.setString(1, artikel.getNaam());
            preparedStatement.setBigDecimal(2, artikel.getPrijs());
            preparedStatement.setInt(3, artikel.getVoorraad());
            preparedStatement.setInt(4, artikel.getId());
        
            updateExecuted = preparedStatement.executeUpdate();
            
        } catch (ParserConfigurationException | SAXException | IOException |
                ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
            log.warn("Exception catched in updateArtikel");
        }
        
        return updateExecuted != 0;
    }
    
    @Override
    public boolean deleteArtikel(int id) {
        
        try (Connection connection = DatabaseConnection.getConnection()) {
            log.debug("Database connected through deleteArtikel");
            
            preparedStatement = connection.prepareStatement(
                    "delete from bpapplikaasie.artikel where id = ?");
            preparedStatement.setInt(1, id);
        
            updateExecuted = preparedStatement.executeUpdate();
            
        } catch (ParserConfigurationException | SAXException | IOException |
                ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
            log.warn("Exception catched in deleteArtikel");
        }
        
        return updateExecuted != 0;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 13 * hash + this.updateExecuted;
        hash = 13 * hash + Objects.hashCode(this.preparedStatement);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final MySQLArtikelDAO other = (MySQLArtikelDAO) obj;
        if (this.updateExecuted != other.updateExecuted) {
            return false;
        }
        if (!Objects.equals(this.preparedStatement, other.preparedStatement)) {
            return false;
        }
        return true;
    }
}
