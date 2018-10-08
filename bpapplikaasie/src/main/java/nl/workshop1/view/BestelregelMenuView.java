package nl.workshop1.view;

import java.math.BigDecimal;
import java.util.ArrayList;
import nl.workshop1.controller.ArtikelController;
import nl.workshop1.controller.BestellingController;
import nl.workshop1.controller.BestelregelController;
import nl.workshop1.domain.Artikel;
import nl.workshop1.domain.Bestelling;
import nl.workshop1.domain.Bestelregel;

/**
 *
 * @author Vosjes
 */
public class BestelregelMenuView extends MenuView {

    @Override
    public void showMenu() {
    }
    
    public void showMenuWithBestelling(Bestelling bestelling) {
        
        /*
        Hier wordt showUpdateBestellingMenu() van BestellingMenuView opgevangen
        */
        
        String selection = "";
        
        do {
            setViewName("Bestelling aanpassen");
            printHeader();

            System.out.println("Wilt u:\n");
            System.out.println("1. Bestelregel toevoegen\n2. Bestelregel aanpassen\n" +
                    "3. Bestelregel verwijderen\n\n0. Terug naar Bestellingpagina\n");
            selection = getSelection();
            switch (selection) {
                case "0":   break;
                case "1":   showInsertBestelregelMenu(bestelling); break;
                case "2":   showUpdateBestelregelMenu(bestelling); break;
                case "3":   showDeleteBestelregelMenu(bestelling); break;
                default:    System.out.println(MAINERROR);
            }
        } while (selection.equals("0") == false);
    }

    @Override
    public void setViewName(String viewName) {
        this.viewName = viewName;
    }
    
    private void printAttributes(Bestelregel bestelregel) {
        ArtikelController artikelController = new ArtikelController();
        bestelregel.setArtikel(artikelController.selectArtikel(bestelregel.getArtikel().getId()));
        System.out.printf("%-16s%-10.2f%-8d\n", bestelregel.getArtikel().getNaam(),
                bestelregel.getArtikel().getPrijs(), bestelregel.getAantal());
    }
    
    private void addToTotaalprijsBestelling(Bestelregel bestelregel, Bestelling bestelling) {
        BigDecimal x = bestelregel.getArtikel().getPrijs().multiply(new BigDecimal(bestelregel.getAantal()));
        bestelling.setTotaalprijs(bestelling.getTotaalprijs().add(x));
        bestelregel.setBestelling(bestelling);
    }
    
    private void subtractFromTotaalprijsBestelling(Bestelregel bestelregel, Bestelling bestelling) {
        BigDecimal x = bestelregel.getArtikel().getPrijs().multiply(new BigDecimal(bestelregel.getAantal()));
        bestelling.setTotaalprijs(bestelling.getTotaalprijs().subtract(x));
        bestelregel.setBestelling(bestelling);
    }
    
    private void addToArtikelVoorraad(Bestelregel bestelregel) {
        Artikel artikel = bestelregel.getArtikel();
        artikel.setVoorraad(artikel.getVoorraad() + bestelregel.getAantal());
        bestelregel.setArtikel(artikel);
    }
    
    private void subtractFromArtikelVoorraad(Bestelregel bestelregel) {
        Artikel artikel = bestelregel.getArtikel();
        artikel.setVoorraad(artikel.getVoorraad() - bestelregel.getAantal());
        bestelregel.setArtikel(artikel);
    }
    
    private void updateArtikelAndBestelling(Bestelregel bestelregel) {
        ArtikelController artikelController = new ArtikelController();
        artikelController.updateArtikel(bestelregel.getArtikel());
        BestellingController bestellingController = new BestellingController();
        bestellingController.updateBestelling(bestelregel.getBestelling());
    }
    
    private void showInsertBestelregelMenu(Bestelling bestelling) {
        
        setViewName("Bestelregel toevoegen");
        printHeader();
        
        System.out.println("Voeg een nieuwe bestelregel toe aan deze bestelling.");
        Bestelregel bestelregel = getNewBestelregel();
        addToTotaalprijsBestelling(bestelregel, bestelling);
                
        System.out.println("De opgegeven bestelregel:");
        printAttributes(bestelregel);
        System.out.println("\nIs dit correct?");
        System.out.println("1. Ja, opslaan.\n0. Nee, stoppen (niets opslaan).\n");
        switch (getSelection()) {
            case "0":   break;
            case "1":   BestelregelController bestelregelController = new BestelregelController();
                        bestelregelController.insertBestelregel(bestelregel);
                        updateArtikelAndBestelling(bestelregel);
                        System.out.println("Bestelregel toegevoegd.");
                        break;
            default:    System.out.println(MAINERROR);
        }
    }
    
    private Bestelregel getNewBestelregel() {

        Bestelregel bestelregel = new Bestelregel();
        
        ArtikelMenuView artikelMenuView = new ArtikelMenuView();
        Artikel artikel = artikelMenuView.showSelectArtikelMenu();
        
        System.out.println("\nKies het aantal dat u wilt bestellen.\n");
        int selection = Integer.parseInt(getSelection());
        if (selection == 0) {
        }
        else if (selection < 0 || selection > artikel.getVoorraad()) {
            System.out.println(MAINERROR);
        }
        else {
            bestelregel.setAantal(selection);
            bestelregel.setArtikel(artikel);
            subtractFromArtikelVoorraad(bestelregel);
        }
        return bestelregel;
    }
    
    private Bestelregel showSelectBestelregelMenu(int bestellingId) {
        
        setViewName("Bestelregel zoeken\t");
        printHeader();
        
        int id = printList(bestellingId);
        System.out.println("\n0. Terug");
        
        BestelregelController bestelregelController = new BestelregelController();
        Bestelregel bestelregel = new Bestelregel();
        
        System.out.println("\nSelecteer bestelregel.\n");
        int selection = Integer.parseInt(getSelection());
        if (selection == 0) {
        }
        else if (selection < 0 || selection > id) {
            System.out.println(MAINERROR);
        }
        else {
            bestelregel = bestelregelController.selectBestelregel(selection);
            System.out.println("De geselecteerde bestelregel:");
            printAttributes(bestelregel);
        }
        return bestelregel;
    }
    
    int printList(int bestellingId) {
        
        BestelregelController bestelregelController = new BestelregelController();
        ArrayList<Bestelregel> bestelregels = bestelregelController.selectBestelregels(bestellingId);
        
        System.out.printf("%3s%-16s%-10s%-8s\n", "", "Art.naam", "Art.prijs", "Aantal");
        for (Bestelregel e: bestelregels) {
            System.out.print(e.getId() + ". ");
            printAttributes(e);
        }
        return bestelregels.size();
    }
    
    private void showUpdateBestelregelMenu(Bestelling bestelling) {
        
        Bestelregel bestelregel = showSelectBestelregelMenu(bestelling.getId());
        
        setViewName("Bestelregel aanpassen\t");
        printHeader();
        
        System.out.println("Wat wilt u aanpassen?");
        System.out.println("1. Aantal\n\n0. Niets, terug naar Bestelling aanpassen\n");
        switch (getSelection()) {
            case "0":   break;
            case "1":   System.out.print("Nieuw aantal: ");
                        bestelregel.setAantal(SCANNER.nextInt());
                        addToTotaalprijsBestelling(bestelregel, bestelling);
                        showUpdatedBestelregelMenu(bestelregel);
                        break;
            default:    System.out.println(MAINERROR);
        }
    }
    
    private void showUpdatedBestelregelMenu(Bestelregel bestelregel) {
        
        subtractFromArtikelVoorraad(bestelregel);
        
        System.out.println("\nDe aangepaste bestelregel:");
        printAttributes(bestelregel);
        System.out.println("\nWilt u de aangepaste bestelregel opslaan?");
        System.out.println("1. Ja\n0. Nee\n");
        switch (getSelection()) {
            case "0":   break;
            case "1":   BestelregelController bestelregelController = new BestelregelController();
                        bestelregelController.updateBestelregel(bestelregel);
                        updateArtikelAndBestelling(bestelregel);
                        System.out.println("Bestelregel opgeslagen.");
                        break;
            default:    System.out.println(MAINERROR);
        }
    }
    
    private void showDeleteBestelregelMenu(Bestelling bestelling) {
        
        Bestelregel bestelregel = showSelectBestelregelMenu(bestelling.getId());
        int id = bestelregel.getId();
        
        setViewName("Bestelregel verwijderen");
        printHeader();
        
        System.out.println("Wilt u deze bestelregel verwijderen?");
        System.out.println("1. Ja\n0. Nee\n");
        switch (getSelection()) {
            case "0":   break;
            case "1":   subtractFromTotaalprijsBestelling(bestelregel, bestelling);
                        addToArtikelVoorraad(bestelregel);
                        updateArtikelAndBestelling(bestelregel);
                        BestelregelController bestelregelController = new BestelregelController();
                        bestelregelController.deleteBestelregel(id);
                        System.out.println("Bestelregel verwijderd.");
                        break;
            default:    System.out.println(MAINERROR);
        }
    }
}
