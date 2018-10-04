package nl.workshop1.controller;

import java.util.ArrayList;
import nl.workshop1.dao.BestellingDAO;
import nl.workshop1.dao.DAOFactory;
import nl.workshop1.domain.Bestelling;

/**
 *
 * @author Vosjes
 */
public class BestellingController {
    
    public Bestelling insertBestelling(Bestelling bestelling) {
        BestellingDAO bestellingDAO = DAOFactory.getDAOFactory().getBestellingDAO();
        return bestellingDAO.insertBestelling(bestelling);
    }
    
    public Bestelling selectBestelling(int id) {
        BestellingDAO bestellingDAO = DAOFactory.getDAOFactory().getBestellingDAO();
        return bestellingDAO.selectBestelling(id);
    }
    
    public ArrayList<Bestelling> selectBestellingen() {
        BestellingDAO bestellingDAO = DAOFactory.getDAOFactory().getBestellingDAO();
        return bestellingDAO.selectBestellingen();
    }
    
    public boolean updateBestelling(Bestelling bestelling) {
        BestellingDAO bestellingDAO = DAOFactory.getDAOFactory().getBestellingDAO();
        return bestellingDAO.updateBestelling(bestelling);
    }
    
    public boolean deleteBestelling(int id) {
        BestellingDAO bestellingDAO = DAOFactory.getDAOFactory().getBestellingDAO();
        return bestellingDAO.deleteBestelling(id);
    }
}
