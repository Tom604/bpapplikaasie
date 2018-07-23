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
    public boolean insertArtikel(Connection connection, String naam, BigDecimal prijs,
            int voorraad) throws SQLException {
        
        preparedStatement = connection.prepareStatement(
                "insert into artikel (naam, prijs, voorraad) values (?, ?, ?)");
        
        preparedStatement.setString(1, naam);
        preparedStatement.setBigDecimal(2, prijs);
        preparedStatement.setInt(3, voorraad);
        
        return preparedStatement.executeUpdate() != 0;
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
    public boolean updateArtikel(Connection connection, String kolomNaam,
            String nieuweWaarde, String naam) throws SQLException {
        
        preparedStatement = connection.prepareStatement(
                "update artikel set ? = ? where naam = ?");
        
        preparedStatement.setString(1, kolomNaam);
        preparedStatement.setString(2, nieuweWaarde);
        preparedStatement.setString(3, naam);
        
        return preparedStatement.executeUpdate() != 0;
    }
    
    public void updateArtikel(Connection connection, String kolomNaam,
            BigDecimal nieuweWaarde, String naam) throws SQLException {
        
        updateArtikel(connection, kolomNaam, String.valueOf(nieuweWaarde), naam);
    }
    
    public void updateArtikel(Connection connection, String kolomNaam,
            int nieuweWaarde, String naam) throws SQLException {
        
        updateArtikel(connection, kolomNaam, String.valueOf(nieuweWaarde), naam);
    }
    
    @Override
    public boolean deleteArtikel(Connection connection, String naam) throws SQLException{
        
        preparedStatement = connection.prepareStatement(
                "delete from artikel where naam = ?");
        
        preparedStatement.setString(1, naam);
        
        return preparedStatement.executeUpdate() != 0;
    }
}
