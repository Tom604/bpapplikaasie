package nl.workshop1.view;

import java.util.ArrayList;
import nl.workshop1.controller.KlantController;
import nl.workshop1.domain.Klant;

/**
 *
 * @author Vosjes
 */
public class KlantMenuView extends MenuView {

    @Override
    public void showMenu() {
        
        String selection = "";
        
        do {
            setViewName("Klanten\t\t");
            printHeader();
            
            System.out.println("1. Klantgegevens toevoegen\n2. Klantgegevens zoeken\n3. Klantgegevens " +
                "aanpassen\n4. Klantgegevens verwijderen\n\n0. Terug naar Hoofdpagina\n");
            selection = getSelection();
            switch (selection) {
                case "0":   break;
                case "1":   showInsertKlantMenu(); break;
                case "2":   showSelectKlantMenu(); break;
                case "3":   showUpdateKlantMenu(); break;
                case "4":   showDeleteKlantMenu(); break;
                default:    System.out.println(MAINERROR);
            }
        } while (selection.equals("0") == false);
    }

    @Override
    public void setViewName(String viewName) {
        this.viewName = viewName;
    }

    private void showInsertKlantMenu() {
        
        setViewName("Klanten toevoegen");
        printHeader();
        
        System.out.println("Voeg een nieuwe klant toe aan de database.\n");
        System.out.print("Voer de voornaam van de klant in (en druk dan op enter): ");
        String voornaam = SCANNER.next();
        System.out.print("Voer de achternaam van de klant in (en druk dan op enter): ");
        String achternaam = SCANNER.next();
        System.out.print("Voer het tussenvoegsel van de klant in (en druk dan op enter): ");
        String tussenvoegsel = SCANNER.next();
            
        System.out.println("\nDe opgegeven klant:\nVoornaam:\t\t" + voornaam +
                "\nAchternaam:\t\t" + achternaam + "\nTussenvoegsel:\t\t" + tussenvoegsel);
        System.out.println("\nIs dit correct?");
        System.out.println("1. Ja, opslaan.\n0. Nee, stoppen (niets opslaan).\n");
        
        String selection = getSelection();
        switch (selection) {
            case "0":   break;
            case "1":   KlantController klantController = new KlantController();
                        klantController.insertKlant(voornaam, achternaam, tussenvoegsel);
                        System.out.println("Klant toegevoegd.");
                        break;
            default:    System.out.println(MAINERROR);
        }
    }
    
    Klant showSelectKlantMenu() {
        
        setViewName("Klanten zoeken\t");
        printHeader();
        
        int id = printList();
        System.out.println("\n0. Terug");
        
        KlantController klantController = new KlantController();
        Klant klant = new Klant();
        
        System.out.println("\nSelecteer klant.\n");
        int selection = Integer.parseInt(getSelection());
        if (selection == 0) {
        }
        else if (selection < 0 || selection > id) {
            System.out.println(MAINERROR);
        }
        else {
            klant = klantController.selectKlant(selection);
            System.out.println("De geselecteerde klant:");
            System.out.println(klant.toString());
        }
            
        return klant;
    }
    
    private int printList() {

        KlantController klantController = new KlantController();
        ArrayList<Klant> klanten = klantController.selectKlanten();
        
        for (Klant e: klanten) {
            System.out.println(e.getId() + ". " + e.toString());
        }
        return klanten.size();
    }

    private void showUpdateKlantMenu() {
        
        Klant klant = showSelectKlantMenu();
        setViewName("Klanten aanpassen\t");
        printHeader();
        
        System.out.println("Wat wilt u aanpassen?");
        System.out.println("1. Voornaam\n2. Achternaam\n3. Tussenvoegsel\n\n" +
                "0. Niets, terug naar Klantenpagina\n");
        String selection = getSelection();
        switch (selection) {
            case "0":   break;
            case "1":   System.out.print("Nieuwe voornaam: ");
                        String nieuweVoornaam = SCANNER.next();
                        showUpdatedKlantMenu(nieuweVoornaam, klant.getAchternaam(), klant.getTussenvoegsel());
                        break;
            case "2":   System.out.print("Nieuwe achternaam: ");
                        String nieuweAchternaam = SCANNER.next();
                        showUpdatedKlantMenu(klant.getVoornaam(), nieuweAchternaam, klant.getTussenvoegsel());
                        break;
            case "3":   System.out.print("Nieuwe tussenvoegsel: ");
                        String nieuwTussenvoegsel = SCANNER.next();
                        showUpdatedKlantMenu(klant.getVoornaam(), klant.getAchternaam(), nieuwTussenvoegsel);
                        break;
            default:    System.out.println(MAINERROR);
        }
    }

    private void showUpdatedKlantMenu(String voornaam, String achternaam, String tussenvoegsel) {
        
        System.out.println("\nDe aangepaste klant:");
        System.out.println("Voornaam:\t\t" + voornaam + "\nAchternaam:\t\t" + achternaam +
                "\nTussenvoegsel:\t" + tussenvoegsel);
        
        System.out.println("\nWilt u de aangepaste klant opslaan?");
        System.out.println("1. Ja\n0. Nee\n");
            
        String selection = getSelection();
        switch (selection) {
            case "0":   break;
            case "1":   KlantController klantController = new KlantController();
                        klantController.updateKlant(voornaam, achternaam, tussenvoegsel);
                        System.out.println("Klant opgeslagen.");
                        break;
            default:    System.out.println(MAINERROR);
        }
    }
    
    private void showDeleteKlantMenu() {
        
        Klant klant = showSelectKlantMenu();
        int id = klant.getId();
        
        setViewName("Klant verwijderen");
        printHeader();
        
        System.out.println("Wilt u deze klant verwijderen?");
        System.out.println("1. Ja\n0. Nee\n");
            
        String selection = getSelection();
        switch (selection) {
            case "0":   break;
            case "1":   KlantController klantController = new KlantController();
                        klantController.deleteKlant(id);
                        System.out.println("Klant verwijderd.");
                        break;
            default:    System.out.println(MAINERROR);
        }
    }
}
