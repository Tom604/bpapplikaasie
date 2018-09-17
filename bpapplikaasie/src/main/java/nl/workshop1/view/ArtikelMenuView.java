package nl.workshop1.view;

import java.math.BigDecimal;
import java.util.NoSuchElementException;
import nl.workshop1.controller.ArtikelController;
import nl.workshop1.controller.LoginController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Vosjes
 */
public class ArtikelMenuView extends MenuView {
    
    static Logger log = LoggerFactory.getLogger(ArtikelMenuView.class);
    
    @Override
    public void showMenu() {
        
        String type = LoginController.accounttype;
        String selection = "";
        
        do {
            setViewName("Artikelen\t");
            printHeader();
            
            switch (type) {
                case "admin":       selection = showAdminMenu(); break;
                case "medewerker":  selection = showMedewerkerMenu(); break;
                default:            System.out.println("Onjuiste input.");
            }
            
            /*
            Constante maken voor de loop continuation condition van de while in MenuView?:
            protected final boolean LOOPCONTCOND = selection.equals("0") == false
            */
        } while (selection.equals("0") == false);
    }
    
    /*
    Admin en medewerkermenu van MenuView overerven (abstract)? Gebruik ik in meerdere klassen.
    In dit geval is er geen klantmenu, maar die zou ik wel in MenuView zetten, dus die zou ik
    dan ook moeten implementeren (is dit handig - iig niet voor LoginMenuView)?
    */
    private String showAdminMenu() {
        System.out.println("1. Artikel toevoegen\n2. Artikel zoeken\n3. Artikel " +
                "aanpassen\n4. Artikel verwijderen\n\n0. Terug naar Hoofdpagina\n");
        String selection = getSelection();
        switch (selection) {
            case "0":   break;
            case "1":   showArtikelInsertMenu(); break;
            case "2":   showArtikelSelectMenu(); break;
            case "3":   showArtikelUpdateMenu(); break;
            case "4":   showArtikelDeleteMenu(); break;
            default:    System.out.println(MAINERROR);
        }
        return selection;
    }
    
    private String showMedewerkerMenu() {
        System.out.println("1. Artikel zoeken\n2. Artikel aanpassen\n" +
                "3. Artikel verwijderen\n\n0. Terug naar Hoofdpagina\n");
        String selection = getSelection();
        switch (selection) {
            case "0":   break;
            case "1":   showArtikelSelectMenu(); break;
            case "2":   showArtikelUpdateMenu(); break;
            case "3":   showArtikelDeleteMenu(); break;
            default:    System.out.println(MAINERROR);
        }
        return selection;
    }
    
    private void showArtikelInsertMenu() {
        
        setViewName("Artikel toevoegen");
        printHeader();

        try {
            System.out.println("Voeg een nieuw artikel toe aan de database.\n");
            System.out.print("Voer de naam van het artikel in (en druk dan op enter): ");
            String naam = SCANNER.next();
            System.out.print("Voer de prijs van het artikel in (en druk dan op enter): ");
            BigDecimal prijs = SCANNER.nextBigDecimal();
            System.out.print("Voer de voorraad voor het artikel in (en druk dan op enter): ");
            int voorraad = SCANNER.nextInt();

            System.out.println("\nHet opgegeven artikel:\nNaam:\t\t" + naam +
                    "\nPrijs:\t\t" + prijs + "\nVoorraad:\t" + voorraad);
            System.out.println("\nIs dit correct?");
            System.out.println("1. Ja, opslaan.\n0. Nee, stoppen (niets opslaan).\n");
            
            String selection = getSelection();
            switch (selection) {
                case "0":   break;
                case "1":   ArtikelController artikelController = new ArtikelController();
                            if (artikelController.insertedArtikel(naam, prijs, voorraad)) {
                                System.out.println("Artikel toegevoegd.");
                            }
                            else {
                                System.out.println("Artikel kon niet worden toegevoegd, probeer opnieuw.");
                            }
                            break;
                default:    System.out.println(MAINERROR);
            }
        } catch (NumberFormatException | NoSuchElementException | IllegalStateException ex) {
            log.warn("Exception catched in showArtikelInsertMenu() method");
            System.out.println(MAINTOPBOTTOM);
            System.out.println(MAINERROR);
            SCANNER.next();
        }
    }
    
    private ArtikelController showArtikelSelectMenu() {

        setViewName("Artikel zoeken\t");
        printHeader();
        
        System.out.println("Zoek een artikel uit de database.");
        System.out.print("Voer de naam van het artikel in (en druk dan op enter): ");
        String naam = SCANNER.next();
            
        ArtikelController artikelController = new ArtikelController();
        artikelController.setSelectedArtikel(naam);
            
        System.out.println("\nHet geselecteerde artikel:");
        System.out.println("Naam:\t\t" + artikelController.getNaam() + 
                "\nPrijs:\t\t" + artikelController.getPrijs() +
                "\nVoorraad:\t" + artikelController.getVoorraad());

        System.out.println(MAINTOPBOTTOM);
        
        return artikelController;
    }
    
    private void showArtikelUpdateMenu() {
        
        /*
        Is deze methode goed zo? Wellicht wil ik hem anders indelen.
        */
        try {
            ArtikelController artikelController = showArtikelSelectMenu();
            String naam = artikelController.getNaam();
            BigDecimal prijs = artikelController.getPrijs();
            int voorraad = artikelController.getVoorraad();
            
            setViewName("Artikel aanpassen");
            printHeader();
            
            System.out.println("Wat wilt u aanpassen?");
            System.out.println("1. Naam\n2. Prijs\n3. Voorraad\n" +
                    "0. Niets, terug naar artikelpagina\n");
            
            String selection = getSelection(); // Geeft extra TOPBOTTOM regel
            switch (selection) {
                case "0":   break;
                case "1":   System.out.print("Nieuwe naam: ");
                            String nieuweNaam = SCANNER.next();
                            showUpdatedArtikelMenu(nieuweNaam, prijs, voorraad);
                            break;
                case "2":   System.out.print("Nieuwe prijs: ");
                            BigDecimal nieuwePrijs = SCANNER.nextBigDecimal();
                            showUpdatedArtikelMenu(naam, nieuwePrijs, voorraad);
                            break;
                case "3":   System.out.print("Nieuwe voorraad: ");
                            int nieuweVoorraad = SCANNER.nextInt();
                            showUpdatedArtikelMenu(naam, prijs, nieuweVoorraad);
                            break;
                default:    System.out.println(MAINERROR);
            }
        } catch (NumberFormatException | NoSuchElementException | IllegalStateException ex) {
            log.warn("Exception catched in showArtikelUpdateMenu() method");
            System.out.println(MAINTOPBOTTOM);
            System.out.println(MAINERROR);
            SCANNER.next();
        }
    }
    
    private void showUpdatedArtikelMenu(String naam, BigDecimal prijs, int voorraad) {
        
        System.out.println("\nHet aangepaste artikel:\n\nNaam:\t\t" + naam +
                        "\nPrijs:\t\t" + prijs + "\nVoorraad:\t" + voorraad);
                
        System.out.println("\nWilt u het aangepaste artikel opslaan?");
        System.out.println("1. Ja\n0. Nee\n");
            
        String selection = getSelection();
        switch (selection) {
            case "0":   break;
            case "1":   ArtikelController artikelController = new ArtikelController();
                        artikelController.updateArtikel(naam, prijs, voorraad);
                        System.out.println("Artikel opgeslagen.");
                        break;
            default:    System.out.println(MAINERROR);
        }
    }
    
    private void showArtikelDeleteMenu() {
        
        ArtikelController artikelController = showArtikelSelectMenu();
        String naam = artikelController.getNaam();
        
        setViewName("Artikel verwijderen");
        printHeader();
        
        System.out.println("\nWilt u dit artikel verwijderen?");
        System.out.println("1. Ja\n0. Nee\n");
            
        String selection = getSelection();
        switch (selection) {
            case "0":   break;
            case "1":   artikelController.deleteArtikel(naam);
                        System.out.println("Artikel verwijderd.");
                        break;
            default:    System.out.println(MAINERROR);
        }
    }
    
    @Override
    public void setViewName(String viewName) {
        this.viewName = viewName;
    }
}
