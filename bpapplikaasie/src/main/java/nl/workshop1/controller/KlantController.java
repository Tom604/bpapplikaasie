package nl.workshop1.controller;

import java.util.ArrayList;
import nl.workshop1.dao.DAOFactory;
import nl.workshop1.dao.KlantDAO;
import nl.workshop1.domain.Klant;

/**
 *
 * @author Vosjes
 */
public class KlantController {
    
    public boolean insertKlant(Klant klant) {
        KlantDAO klantDAO = DAOFactory.getDAOFactory().getKlantDAO();
        return klantDAO.insertKlant(klant);
    }
    
    public Klant selectKlant(int id) {
        KlantDAO klantDAO = DAOFactory.getDAOFactory().getKlantDAO();
        return klantDAO.selectKlant(id);
    }
    
    public Klant insertAndSelectKlant(Klant klant) {
        
        /*
        Hier wordt de klant gezet en met id uit de db gehaald
        */
        KlantDAO klantDAO = DAOFactory.getDAOFactory().getKlantDAO();
        return klantDAO.insertAndSelectKlant(klant);
    }
    
    public ArrayList<Klant> selectKlanten() {
        KlantDAO klantDAO = DAOFactory.getDAOFactory().getKlantDAO();
        return klantDAO.selectKlanten();
    }
    
    public boolean updateKlant(Klant klant) {
        KlantDAO klantDAO = DAOFactory.getDAOFactory().getKlantDAO();
        return klantDAO.updateKlant(klant);
    }
    
    public boolean deleteKlant(int id) {
        KlantDAO klantDAO = DAOFactory.getDAOFactory().getKlantDAO();
        return klantDAO.deleteKlant(id);
    }
}
