package nl.workshop1.view;

import java.math.BigDecimal;
import java.util.ArrayList;
import nl.workshop1.controller.ArtikelController;
import nl.workshop1.domain.Artikel;

/**
 *
 * @author Vosjes
 */
public class ArtikelMenuView extends MenuView {
    
    @Override
    public void showMenu() {
        
        String selection = "";
        
        do {
            setViewName("Artikelen\t");
            printHeader();
            
            System.out.println("1. Artikel toevoegen\n2. Artikel zoeken\n3. Artikel " +
                "aanpassen\n4. Artikel verwijderen\n\n0. Terug naar Hoofdpagina\n");
            selection = getSelection();
            switch (selection) {
                case "0":   break;
                case "1":   showInsertArtikeltMenu(); break;
                case "2":   showSelectArtikelMenu(); break;
                case "3":   showUpdateArtikelMenu(); break;
                case "4":   showDeleteArtikelMenu(); break;
                default:    System.out.println(MAINERROR);
            }
        } while (selection.equals("0") == false);
    }
    
    @Override
    public void setViewName(String viewName) {
        this.viewName = viewName;
    }
    
    private void showInsertArtikeltMenu() {
        
        setViewName("Artikel toevoegen");
        printHeader();

        Artikel artikel = new Artikel();
        
        System.out.println("Voeg een nieuw artikel toe aan de database.\n");
        System.out.print("Voer de naam van het artikel in (en druk dan op enter): ");
        artikel.setNaam(SCANNER.next());
        System.out.print("Voer de prijs van het artikel in (en druk dan op enter): ");
        artikel.setPrijs(SCANNER.nextBigDecimal());
        System.out.print("Voer de voorraad voor het artikel in (en druk dan op enter): ");
        artikel.setVoorraad(SCANNER.nextInt());

        System.out.println("\nHet opgegeven artikel:\n" + artikel.toString());
        System.out.println("\nIs dit correct?");
        System.out.println("1. Ja, opslaan.\n0. Nee, stoppen (niets opslaan).\n");
        
        String selection = getSelection();
        switch (selection) {
            case "0":   break;
            case "1":   ArtikelController artikelController = new ArtikelController();
                        artikelController.insertArtikel(artikel);
                        System.out.println("Artikel toegevoegd.");
                        break;
            default:    System.out.println(MAINERROR);
        }
    }
    
    Artikel showSelectArtikelMenu() {

        setViewName("Artikel zoeken\t");
        printHeader();
        
        int id = printList();
        System.out.println("\n0. Terug");
        
        ArtikelController artikelController = new ArtikelController();
        Artikel artikel = new Artikel();
        
        System.out.println("Selecteer artikel.\n");
        int selection = Integer.parseInt(getSelection());
        if (selection == 0) {
        }
        else if (selection < 0 || selection > id) {
            System.out.println(MAINERROR);
        }
        else {
            artikel = artikelController.selectArtikel(selection);
            System.out.println("Het geselecteerde artikel:");
            System.out.println(artikel.toString());
        }
        
        return artikel;
    }
    
    private int printList() {

        ArtikelController artikelController = new ArtikelController();
        ArrayList<Artikel> artikelen = artikelController.selectArtikelen();
        
        for (Artikel e: artikelen) {
            System.out.println(e.getId() + ". " + e.toString());
        }
        return artikelen.size();
    }
    
    void showUpdateArtikelMenu() {
        
        Artikel artikel = showSelectArtikelMenu();
        setViewName("Artikel aanpassen");
        printHeader();
           
        System.out.println("Wat wilt u aanpassen?");
        System.out.println("1. Naam\n2. Prijs\n3. Voorraad\n" +
                "0. Niets, terug naar artikelpagina\n");
        String selection = getSelection();
        switch (selection) {
            case "0":   break;
            case "1":   System.out.print("Nieuwe naam: ");
                        String nieuweNaam = SCANNER.next();
                        artikel.setNaam(nieuweNaam);
                        showUpdatedArtikelMenu(artikel);
                        break;
            case "2":   System.out.print("Nieuwe prijs: ");
                        BigDecimal nieuwePrijs = SCANNER.nextBigDecimal();
                        artikel.setPrijs(nieuwePrijs);
                        showUpdatedArtikelMenu(artikel);
                        break;
            case "3":   System.out.print("Nieuwe voorraad: ");
                        int nieuweVoorraad = SCANNER.nextInt();
                        artikel.setVoorraad(nieuweVoorraad);
                        showUpdatedArtikelMenu(artikel);
                        break;
            default:    System.out.println(MAINERROR);
        }
    }
    
    private void showUpdatedArtikelMenu(Artikel artikel) {
        
        System.out.println("\nHet aangepaste artikel:\n\nNaam:\t\t" + artikel.getNaam() +
                        "\nPrijs:\t\t" + artikel.getPrijs() + "\nVoorraad:\t" + artikel.getVoorraad());
                
        System.out.println("\nWilt u het aangepaste artikel opslaan?");
        System.out.println("1. Ja\n0. Nee\n");
            
        String selection = getSelection();
        switch (selection) {
            case "0":   break;
            case "1":   ArtikelController artikelController = new ArtikelController();
                        artikelController.updateArtikel(artikel);
                        System.out.println("Artikel opgeslagen.");
                        break;
            default:    System.out.println(MAINERROR);
        }
    }
    
    private void showDeleteArtikelMenu() {
        
        Artikel artikel = showSelectArtikelMenu();
        int id = artikel.getId();
        
        setViewName("Artikel verwijderen");
        printHeader();
        
        System.out.println("\nWilt u dit artikel verwijderen?");
        System.out.println("1. Ja\n0. Nee\n");
            
        String selection = getSelection();
        switch (selection) {
            case "0":   break;
            case "1":   ArtikelController artikelController = new ArtikelController();
                        artikelController.deleteArtikel(id);
                        System.out.println("Artikel verwijderd.");
                        break;
            default:    System.out.println(MAINERROR);
        }
    }
}
