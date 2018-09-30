package nl.workshop1.controller;

import java.util.ArrayList;
import nl.workshop1.dao.BestelregelDAO;
import nl.workshop1.dao.DAOFactory;
import nl.workshop1.domain.Bestelregel;

/**
 *
 * @author Vosjes
 */
public class BestelregelController {
    
    public boolean insertBestelregel(Bestelregel bestelregel) {
        BestelregelDAO bestelregelDAO = DAOFactory.getDAOFactory().getBestelregelDAO();
        return bestelregelDAO.insertBestelregel(bestelregel);
    }
    
    public Bestelregel selectBestelregel(int id) {
        BestelregelDAO bestelregelDAO = DAOFactory.getDAOFactory().getBestelregelDAO();
        return bestelregelDAO.selectBestelregel(id);
    }
    
    public ArrayList<Bestelregel> selectBestelregels() {
        BestelregelDAO bestelregelDAO = DAOFactory.getDAOFactory().getBestelregelDAO();
        return bestelregelDAO.selectBestelregels();
    }
    
    public ArrayList<Bestelregel> selectBestelregels(int bestellingId) {
        BestelregelDAO bestelregelDAO = DAOFactory.getDAOFactory().getBestelregelDAO();
        return bestelregelDAO.selectBestelregels(bestellingId);
    }
    
    public boolean updateBestelregel(Bestelregel bestelregel) {
        BestelregelDAO bestelregelDAO = DAOFactory.getDAOFactory().getBestelregelDAO();
        return bestelregelDAO.updateBestelregel(bestelregel);
    }
    
    public boolean deleteBestelregel(int id) {
        BestelregelDAO bestelregelDAO = DAOFactory.getDAOFactory().getBestelregelDAO();
        return bestelregelDAO.deleteBestelregel(id);
    }
}
