package nl.workshop1.controller;

import java.util.ArrayList;
import nl.workshop1.dao.AdresDAO;
import nl.workshop1.dao.DAOFactory;
import nl.workshop1.domain.Adres;
import nl.workshop1.domain.Klant;

/**
 *
 * @author Vosjes
 */
public class AdresController {
    
    public boolean insertAdres(String straatnaam, int huisnummer, String toevoeging,
            String postcode, String woonplaats, String adrestype, Klant klant) {
        AdresDAO adresDAO = DAOFactory.getDAOFactory().getAdresDAO();
        Adres adres = new Adres();
        adres.setStraatnaam(straatnaam);
        adres.setHuisnummer(huisnummer);
        adres.setToevoeging(toevoeging);
        adres.setPostcode(postcode);
        adres.setWoonplaats(woonplaats);
        adres.setAdrestype(adrestype);
        adres.setKlant(klant);
        return adresDAO.insertAdres(adres);
    }
    
    public Adres selectAdres(int id) {
        AdresDAO adresDAO = DAOFactory.getDAOFactory().getAdresDAO();
        return adresDAO.selectAdres(id);
    }
    
    public ArrayList<Adres> selectAdressen() {
        AdresDAO adresDAO = DAOFactory.getDAOFactory().getAdresDAO();
        return adresDAO.selectAdressen();
    }
    
    public boolean updateAdres(String straatnaam, int huisnummer, String toevoeging,
            String postcode, String woonplaats, String adrestype, Klant klant) {
        AdresDAO adresDAO = DAOFactory.getDAOFactory().getAdresDAO();
        Adres adres = new Adres();
        adres.setStraatnaam(straatnaam);
        adres.setHuisnummer(huisnummer);
        adres.setToevoeging(toevoeging);
        adres.setPostcode(postcode);
        adres.setWoonplaats(woonplaats);
        adres.setAdrestype(adrestype);
        adres.setKlant(klant);
        return adresDAO.updateAdres(adres);
    }
    
    public boolean deleteAdres(int id) {
        AdresDAO adresDAO = DAOFactory.getDAOFactory().getAdresDAO();
        return adresDAO.deleteAdres(id);
    }
}
