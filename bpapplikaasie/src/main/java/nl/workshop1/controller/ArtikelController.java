package nl.workshop1.controller;

import java.math.BigDecimal;
import nl.workshop1.dao.ArtikelDAO;
import nl.workshop1.dao.DAOFactory;
import nl.workshop1.domain.Artikel;

/**
 *
 * @author Vosjes
 */
public class ArtikelController {

    private String naam;
    private BigDecimal prijs;
    private int voorraad;

    public String getNaam() {
        return naam;
    }

    public BigDecimal getPrijs() {
        return prijs;
    }

    public int getVoorraad() {
        return voorraad;
    }
    
    /*
    TODO - M: Namen van onderstaande methodes veranderen
    */
    
    public boolean insertedArtikel(String naam, BigDecimal prijs, int voorraad) {
        ArtikelDAO artikelDAO = DAOFactory.getDAOFactory().getArtikelDAO();
        Artikel artikel = new Artikel();
        artikel.setNaam(naam);
        artikel.setPrijs(prijs);
        artikel.setVoorraad(voorraad);
        return artikelDAO.insertArtikel(artikel);
    }
    
    public void setSelectedArtikel(String naam) {
        ArtikelDAO artikelDAO = DAOFactory.getDAOFactory().getArtikelDAO();
        Artikel artikel = artikelDAO.selectArtikel(naam);
        this.naam = artikel.getNaam();
        this.prijs = artikel.getPrijs();
        this.voorraad = artikel.getVoorraad();
    }
    
    public boolean updateArtikel(String naam, BigDecimal prijs, int voorraad) {
        ArtikelDAO artikelDAO = DAOFactory.getDAOFactory().getArtikelDAO();
        Artikel artikel = new Artikel();
        artikel.setNaam(naam);
        artikel.setPrijs(prijs);
        artikel.setVoorraad(voorraad);
        return artikelDAO.updateArtikel(artikel);
    }
    
    public boolean deleteArtikel(String naam) {
        ArtikelDAO artikelDAO = DAOFactory.getDAOFactory().getArtikelDAO();
        return artikelDAO.deleteArtikel(naam);
    }
}
