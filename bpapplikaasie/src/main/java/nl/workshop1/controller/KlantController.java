package nl.workshop1.controller;

import nl.workshop1.dao.DAOFactory;
import nl.workshop1.dao.KlantDAO;
import nl.workshop1.domain.Klant;

/**
 *
 * @author Vosjes
 */
public class KlantController {
    
    public boolean insertKlant(String voornaam, String achternaam, String tussenvoegsel) {
        KlantDAO klantDAO = DAOFactory.getDAOFactory().getKlantDAO();
        Klant klant = new Klant();
        klant.setVoornaam(voornaam);
        klant.setAchternaam(achternaam);
        klant.setTussenvoegsel(tussenvoegsel);
        return klantDAO.insertKlant(klant);
    }
    
    public Klant selectKlant(int id) {
        KlantDAO klantDAO = DAOFactory.getDAOFactory().getKlantDAO();
        Klant klant = klantDAO.selectKlant(id);
        return klant;
    }
    
    public boolean updateKlant(String voornaam, String achternaam, String tussenvoegsel) {
        KlantDAO klantDAO = DAOFactory.getDAOFactory().getKlantDAO();
        Klant klant = new Klant();
        klant.setVoornaam(voornaam);
        klant.setAchternaam(achternaam);
        klant.setTussenvoegsel(tussenvoegsel);
        return klantDAO.updateKlant(klant);
    }
    
    public boolean deleteKlant(int id) {
        KlantDAO klantDAO = DAOFactory.getDAOFactory().getKlantDAO();
        return klantDAO.deleteKlant(id);
    }
}
