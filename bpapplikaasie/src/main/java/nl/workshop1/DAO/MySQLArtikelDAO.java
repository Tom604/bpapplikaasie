package nl.workshop1.DAO;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import nl.workshop1.domain.Artikel;

/**
 *
 * @author Vosjes
 */
public class MySQLArtikelDAO extends MySQLDAOFactory implements ArtikelDAO {
    
    /*
    Alle ISUD methodes uit interface en evt. extra methodes specifiek voor MySQLArtikelDAO object
    */
    
    private PreparedStatement preparedStatement;
    
    @Override
    public void insertArtikel(Connection connection, String naam, BigDecimal prijs,
            int voorraad) throws SQLException {
        
        preparedStatement = connection.prepareStatement(
                "insert into artikel (naam, prijs, voorraad) values (?, ?, ?)");
        
        preparedStatement.setString(1, naam);
        preparedStatement.setBigDecimal(2, prijs);
        preparedStatement.setInt(3, voorraad);
        
        preparedStatement.executeUpdate();
    }
    
    @Override
    public Artikel selectArtikel(Connection connection, String naam) throws SQLException {

        Artikel artikel1 = new Artikel();
        
        preparedStatement = connection.prepareStatement(
                "select id, naam, prijs, voorraad from artikel where naam = ?");
        
        preparedStatement.setString(1, naam);
        
        ResultSet resultSet = preparedStatement.executeQuery();
        
        if (resultSet.next()) {
            artikel1.setId(resultSet.getInt("id"));
            artikel1.setNaam(resultSet.getString("naam"));
            artikel1.setPrijs(resultSet.getBigDecimal("prijs"));
            artikel1.setVoorraad(resultSet.getInt("voorraad"));
        }
        
        return artikel1;
        
    }
    
    @Override
    public boolean updateArtikel() {
        
    }
    
    @Override
    public boolean deleteArtikel() {
        
    }
    
    @Override
    public Artikel findArtikel() {
        
    }
}
