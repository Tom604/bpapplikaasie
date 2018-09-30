package nl.workshop1.view;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import nl.workshop1.controller.BestellingController;
import nl.workshop1.domain.Bestelling;
import nl.workshop1.domain.Klant;

/**
 *
 * @author Vosjes
 */
public class BestellingMenuView extends MenuView {
    
    @Override
    public void showMenu() {
        
        String selection = "";
        
        do {
            setViewName("Bestellingen\t");
            printHeader();
            
            System.out.println("1. Bestelling toevoegen\n2. Bestelling zoeken\n3. Bestelling " +
                "aanpassen\n4. Bestelling verwijderen\n\n0. Terug naar Hoofdpagina\n");
            selection = getSelection();
            switch (selection) {
                case "0":   break;
                case "1":   showInsertBestellingMenu(); break;
                case "2":   showSelectBestellingMenu(); break;
                case "3":   showUpdateBestellingMenu(); break;
                case "4":   showDeleteBestellingMenu(); break;
                default:    System.out.println(MAINERROR);
            }
        } while (selection.equals("0") == false);
    }

    @Override
    public void setViewName(String viewName) {
        this.viewName = viewName;
    }
    
    private void showInsertBestellingMenu() {
        
        Bestelling bestelling = getInitializedBestelling();
        
        setViewName("Bestelling toevoegen");
        printHeader();
        
        System.out.println("Voeg een nieuwe bestelling toe voor deze klant.\n");
        System.out.println("1. Ja\n0. Nee, terug naar Bestellingenpagina\n");
        switch (getSelection()) {
            case "0":   break;
            case "1":   BestellingController bestellingController = new BestellingController();
                        bestellingController.insertBestelling(bestelling);
                        System.out.println("Lege bestelling toegevoegd.");
                        break;
            default:    System.out.println(MAINERROR);
        }
    }
    
    private Bestelling getInitializedBestelling() {
        
        KlantMenuView klantMenuView = new KlantMenuView();
        Klant klant = klantMenuView.showSelectKlantMenu();
        Bestelling bestelling = new Bestelling();
        bestelling.setKlant(klant);
        bestelling.setTotaalprijs(new BigDecimal("0")); // Initiële waarde voor totaalprijs
        bestelling.setDatumTijd(LocalDateTime.now());
        return bestelling;
    }
    
    Bestelling showSelectBestellingMenu(){
        
        setViewName("Bestelling zoeken");
        printHeader();
        
        int id = printList();
        System.out.println("\n0. Terug\n");
        
        BestellingController bestellingController = new BestellingController();
        Bestelling bestelling = new Bestelling();
        
        System.out.println("Selecteer bestelling.\n");
        int selection = Integer.parseInt(getSelection());
        if (selection == 0) {
        }
        else if (selection < 0 || selection > id) {
            System.out.println(MAINERROR);
        }
        else {
            bestelling = bestellingController.selectBestelling(selection);
            System.out.println("De geselecteerde bestelling:");
            System.out.println(bestelling.toString());
            BestelregelMenuView bestelregelMenuView = new BestelregelMenuView();
            bestelregelMenuView.printList(selection);
            /*
            TODO - S: Ook nog artikelnaam printen (print nu alleen artikel id)
            */
        }
        return bestelling;
    }
    
    private int printList() {

        BestellingController bestellingController = new BestellingController();
        ArrayList<Bestelling> bestellingen = bestellingController.selectBestellingen();
        
        for (Bestelling e: bestellingen) {
            System.out.println(e.getId() + ". " + e.toString());
        }
        return bestellingen.size();
    }
    
    private void showUpdateBestellingMenu() {
        BestelregelMenuView bestelregelMenuView = new BestelregelMenuView();
        bestelregelMenuView.showMenu();
    }
    
    private void showDeleteBestellingMenu() {
        
        Bestelling bestelling = showSelectBestellingMenu();
        int id = bestelling.getId();
        
        setViewName("Bestelling verwijderen");
        printHeader();
        
        System.out.println("Wilt u deze bestelling verwijderen?");
        System.out.println("1. Ja\n0. Nee\n");
            
        String selection = getSelection();
        switch (selection) {
            case "0":   break;
            case "1":   BestellingController bestellingController = new BestellingController();
                        bestellingController.deleteBestelling(id);
                        System.out.println("Bestelling verwijderd.");
                        break;
            default:    System.out.println(MAINERROR);
        }
    }
}
