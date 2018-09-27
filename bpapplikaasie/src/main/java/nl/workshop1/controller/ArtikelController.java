package nl.workshop1.controller;

import java.util.ArrayList;
import nl.workshop1.dao.ArtikelDAO;
import nl.workshop1.dao.DAOFactory;
import nl.workshop1.domain.Artikel;

/**
 *
 * @author Vosjes
 */
public class ArtikelController {

    public boolean insertArtikel(Artikel artikel) {
        ArtikelDAO artikelDAO = DAOFactory.getDAOFactory().getArtikelDAO();
        return artikelDAO.insertArtikel(artikel);
    }
    
    public Artikel selectArtikel(int id) {
        ArtikelDAO artikelDAO = DAOFactory.getDAOFactory().getArtikelDAO();
        return artikelDAO.selectArtikel(id);
    }
    
    public ArrayList<Artikel> selectArtikelen() {
        ArtikelDAO artikelDAO = DAOFactory.getDAOFactory().getArtikelDAO();
        return artikelDAO.selectArtikelen();
    }
    
    public boolean updateArtikel(Artikel artikel) {
        ArtikelDAO artikelDAO = DAOFactory.getDAOFactory().getArtikelDAO();
        return artikelDAO.updateArtikel(artikel);
    }
    
    public boolean deleteArtikel(int id) {
        ArtikelDAO artikelDAO = DAOFactory.getDAOFactory().getArtikelDAO();
        return artikelDAO.deleteArtikel(id);
    }
}
