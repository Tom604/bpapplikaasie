package nl.workshop1.view;

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
                case "1":   insertKlantMenu(); break;
                case "2":   selectKlantMenu(); break;
                case "3":   updateKlantMenu(); break;
                case "4":   deleteKlantMenu(); break;
                default:    System.out.println(MAINERROR);
            }
        } while (selection.equals("0") == false);
    }

    @Override
    public void setViewName(String viewName) {
        this.viewName = viewName;
    }

    private void insertKlantMenu() {
        
        setViewName("Klanten toevoegen");
        printHeader();
        
        System.out.println("Voeg een nieuw artikel toe aan de database.\n");
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
            default:    System.out.println(MAINERROR);
        }
    }

    private Klant selectKlantMenu() {
        
        setViewName("Klanten zoeken\t");
        printHeader();
        
        KlantController klantController = new KlantController();
        Klant klant = new Klant();
        
        // TODO - S: Niet altijd tussenvoegsel weergeven (is soms null).
        // TODO?: Lijst weergeven in aparte methode?
        for (int x = 1, y = 1; x <= 3 ; x++, y++) {
            klant = klantController.selectKlant(x);
            System.out.println(y + ". " + klant.getVoornaam() + " " +
                    klant.getTussenvoegsel() + " " + klant.getAchternaam());
        }
            
        System.out.println("\nSelecteer klant.\n");
        String selection = getSelection();
        switch (selection) {
            case "0":   break;
            case "1":   klant = klantController.selectKlant(1); break;
            case "2":   klant = klantController.selectKlant(2); break;
            case "3":   klant = klantController.selectKlant(3); break;
            default:    System.out.println(MAINERROR);
        }
        
        // TODO - C: Niet altijd tussenvoegsel weergeven (is soms null)?
        System.out.println("De geselecteerde klant:");
        System.out.println("Naam:\t\t" + klant.getVoornaam() + 
                "\nAchternaam:\t" + klant.getAchternaam() +
                "\nTussenvoegsel:\t" + klant.getTussenvoegsel());
        
        System.out.println(MAINTOPBOTTOM);
        
        return klant;
    }

    private void updateKlantMenu() {
        
        Klant klant = selectKlantMenu();
        setViewName("Klanten zoeken\t");
        printHeader();
        
        System.out.println("Wat wilt u aanpassen?");
        System.out.println("1. Voornaam\n2. Achternaam\n3. Tussenvoegsel\n" +
                "0. Niets, terug naar artikelpagina\n");
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
    
    private void deleteKlantMenu() {
        
        Klant klant = selectKlantMenu();
        int id = klant.getId();
        
        setViewName("Klant verwijderen");
        printHeader();
        
        System.out.println("\nWilt u deze klant verwijderen?");
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
