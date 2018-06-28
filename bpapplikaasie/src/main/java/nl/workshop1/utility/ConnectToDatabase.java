package nl.workshop1.utility;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

/**
 *
 * @author Vosjes
 */
public class ConnectToDatabase {

    private static String driver;
    private static String url;
    private static String username;
    private static String password;
    
    public static Connection connectWithXml() throws ParserConfigurationException, SAXException,
            IOException, ClassNotFoundException, SQLException {
        
        //Create Document object (with Builder and BuilderFactory)
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	DocumentBuilder docBuilder = dbFactory.newDocumentBuilder();
        Document doc = docBuilder.parse(new File("mysql_data.xml"));
        doc.getDocumentElement().normalize();
        
        //Read data from xml through the Document object
        driver = doc.getElementsByTagName("jdbc_driver").item(0).getTextContent();
        url = doc.getElementsByTagName("jdbc_url").item(0).getTextContent();
        username = doc.getElementsByTagName("jdbc_username").item(0).getTextContent();
        password = doc.getElementsByTagName("jdbc_password").item(0).getTextContent();
        
        Class.forName(driver);
        System.out.println("Driver loaded");
        
        return DriverManager.getConnection(url, username, password);
    }
}
