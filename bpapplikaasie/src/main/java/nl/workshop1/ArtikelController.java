package nl.workshop1;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.xml.parsers.ParserConfigurationException;
import nl.workshop1.domain.Artikel;
import nl.workshop1.utility.ConnectToDatabase;
import org.xml.sax.SAXException;

/**
 *
 * @author Vosjes
 */
public class ArtikelController {
    public static void main(String[] args) {

        try {
        //Open the database connection
        Connection conn = ConnectToDatabase.connectWithXml();
        System.out.println("Database connected");
        
        //01 Set data from the database to an Artikel object
        Artikel artikel1 = fillArtikel(conn);

        //02 Print data from an Artikel object (artikel1)
        System.out.println(artikel1.getId() + "\t" + artikel1.getNaam() + "\t" + 
                artikel1.getPrijs() + "\t" + artikel1.getVoorraad());
        
        //Close the connection
        conn.close();
        } catch (SQLException | ParserConfigurationException | SAXException |
                IOException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
    }
    
    public static Artikel fillArtikel(Connection conn) throws SQLException {

        //New Artikel object
        Artikel fillArt1 = new Artikel();
                //Moet het werk in deze methode wel door een Artikel object gedaan worden?
        
        //Create artikel1 statement
        Statement statement = conn.createStatement();

        //Execute artikel1 statement + select id to get data from
        ResultSet resultSet = statement.executeQuery("select id, naam, prijs, "
                + "voorraad from artikel where id = 1");

        //Fill Artikel object with data from database
        if (resultSet.next()) {
            fillArt1.setId(resultSet.getInt("id"));
            fillArt1.setNaam(resultSet.getString("naam"));
            fillArt1.setPrijs(resultSet.getBigDecimal("prijs"));
            fillArt1.setVoorraad(resultSet.getInt("voorraad"));
        }
        
        return fillArt1;
    }
}
