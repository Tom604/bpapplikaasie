package nl.workshop1.controller;

import java.util.ArrayList;
import nl.workshop1.dao.AdresDAO;
import nl.workshop1.dao.DAOFactory;
import nl.workshop1.domain.Adres;

/**
 *
 * @author Vosjes
 */
public class AdresController {
    
    public boolean insertAdres(Adres adres) {
        AdresDAO adresDAO = DAOFactory.getDAOFactory().getAdresDAO();
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
    
    public boolean updateAdres(Adres adres) {
        AdresDAO adresDAO = DAOFactory.getDAOFactory().getAdresDAO();
        return adresDAO.updateAdres(adres);
    }
    
    public boolean deleteAdres(int id) {
        AdresDAO adresDAO = DAOFactory.getDAOFactory().getAdresDAO();
        return adresDAO.deleteAdres(id);
    }
}
