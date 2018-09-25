package nl.workshop1.view;

import java.util.ArrayList;
import nl.workshop1.controller.AdresController;
import nl.workshop1.domain.Adres;
import nl.workshop1.domain.Klant;

/**
 *
 * @author Vosjes
 */
public class AdresMenuView extends MenuView {
    
    /*
    TODO - M: toString() methode van Adres nog aanpassen, om null waarde op te
    vangen voor toevoeging.
    */
    
    @Override
    public void showMenu() {
        
        String selection = "";
        
        do {
            setViewName("Adressen\t");
            printHeader();
            
            System.out.println("1. Adres toevoegen\n2. Adres zoeken\n3. Adres " +
                "aanpassen\n4. Adres verwijderen\n\n0. Terug naar Hoofdpagina\n");
            selection = getSelection();
            switch (selection) {
                case "0":   break;
                case "1":   showInsertAdresMenu(); break;
                case "2":   showSelectAdresMenu(); break;
                case "3":   showUpdateAdresMenu(); break;
                case "4":   showDeleteAdresMenu(); break;
                default:    System.out.println(MAINERROR);
            }
        } while (selection.equals("0") == false);
    }

    @Override
    public void setViewName(String viewName) {
        this.viewName = viewName;
    }
    
    private void showInsertAdresMenu() {
        
        KlantMenuView klantMenuView = new KlantMenuView();
        Klant klant = klantMenuView.showSelectKlantMenu();
        
        setViewName("Adressen toevoegen");
        printHeader();
        
        /*
        TODO - S: Laat lijst van adressen voor deze klant zien. Aparte methode in DAO
        maken die klant_id ontvangt: selectAdressen(klant.getId()).
        Zie code hieronder. In aparte methode - private void showAdressenforKlant(int id),
        aangeroepen met showAdressenforKlant(klant.getId()).
        */
//        AdresController adresController = new AdresController();
//        ArrayList<Adres> adressen = new ArrayList<>();
//        adressen = adresController.selectAdressen(id);
//        for (Adres e: adressen) {
//            System.out.println(e.toString())
//        }
        
        System.out.println("Voeg een nieuw adres toe voor deze klant.\n");
        System.out.print("Voer de straatnaam voor het adres in (en druk dan op enter): ");
        String straatnaam = SCANNER.next();
        System.out.print("Voer het huisnummer voor het adres in (en druk dan op enter): ");
        int huisnummer = SCANNER.nextInt();
        System.out.print("Voer de toevoeging voor het adres in (en druk dan op enter): ");
        String toevoeging = SCANNER.next();
        System.out.print("Voer de postcode voor het adres in (en druk dan op enter): ");
        String postcode = SCANNER.next();
        System.out.print("Voer de woonplaats voor het adres in (en druk dan op enter): ");
        String woonplaats = SCANNER.next();
        String adrestype = getAdrestype();

        System.out.println("\nHet opgegeven adres:\nStraatnaam:\t\t" + straatnaam +
                "\nHuisnummer:\t\t" + huisnummer + "\nToevoeging:\t\t" + toevoeging +
                "\nPostcode:\t\t" + postcode + "\nWoonplaats:\t\t" + woonplaats +
                "\nAdrestype:\t\t" + adrestype);
        System.out.println("\nIs dit correct?");
        System.out.println("1. Ja, opslaan.\n0. Nee, opnieuw invoeren.\n");

        String selection = getSelection();
        switch (selection) {
            case "0":   break;
            case "1":   AdresController adresController = new AdresController();
                        adresController.insertAdres(straatnaam, huisnummer, toevoeging,
                                postcode, woonplaats, adrestype, klant);
                        System.out.println("Adres toegevoegd.");
                        break;
            default:    System.out.println(MAINERROR);
        }
    }
    
    private String getAdrestype() {
        
        String adrestype = "";
        System.out.println("\nSelecteer adrestype:");
        System.out.println("1. Factuur\n2. Post\n3. Woon\n");
        String selection = getSelection();
        switch (selection) {
            case "1":   adrestype = "factuur"; break;
            case "2":   adrestype = "post"; break;
            case "3":   adrestype = "woon";
        }
        return adrestype;
    }
    
    private Adres showSelectAdresMenu() {
        
        setViewName("Adressen zoeken\t");
        printHeader();
        
        int id = printList();
        System.out.println("\n0. Terug");
        
        AdresController adresController = new AdresController();
        Adres adres = new Adres();
        
        System.out.println("\nSelecteer adres.\n");
        int selection = Integer.parseInt(getSelection());
        if (selection == 0) {
        }
        else if (selection < 0 || selection > id) {
            System.out.println(MAINERROR);
        }
        else {
            adres = adresController.selectAdres(selection);
            System.out.println("Het geselecteerde adres:");
            System.out.println(adres.toString());
        }
            
        return adres;
    }
    
    private int printList() {

        AdresController adresController = new AdresController();
        ArrayList<Adres> adressen = adresController.selectAdressen();
        
        for (Adres e: adressen) {
            System.out.println(e.getId() + ". " + e.toString());
        }
        return adressen.size();
    }
    
    private void showUpdateAdresMenu() {
        
        Adres adres = showSelectAdresMenu();
        setViewName("Adressen aanpassen\t");
        printHeader();
        
        System.out.println("Wat wilt u aanpassen?");
        System.out.println("1. Straatnaam\n2. Huisnummer\n3. Toevoeging\n" +
                "4. Postcode\n5. Woonplaats\n6. Adrestype\n\n" +
                "0. Niets, terug naar Adressenpagina\n");
        String selection = getSelection();
        switch (selection) {
            case "0":   break;
            case "1":   System.out.print("Nieuwe straatnaam: ");
                        String nieuweStraatnaam = SCANNER.next();
                        showUpdatedAdresMenu(nieuweStraatnaam, adres.getHuisnummer(), adres.getToevoeging(),
                                adres.getPostcode(), adres.getWoonplaats(), adres.getAdrestype(), adres.getKlant());
                        break;
            case "2":   System.out.print("Nieuw huisnummer: ");
                        int nieuwHuisnummer = SCANNER.nextInt();
                        showUpdatedAdresMenu(adres.getStraatnaam(), nieuwHuisnummer, adres.getToevoeging(),
                                adres.getPostcode(), adres.getWoonplaats(), adres.getAdrestype(), adres.getKlant());
                        break;
            case "3":   System.out.print("Nieuwe toevoeging: ");
                        String nieuweToevoeging = SCANNER.next();
                        showUpdatedAdresMenu(adres.getStraatnaam(), adres.getHuisnummer(), nieuweToevoeging,
                                adres.getPostcode(), adres.getWoonplaats(), adres.getAdrestype(), adres.getKlant());
                        break;
            case "4":   System.out.print("Nieuwe postcode: ");
                        String nieuwePostcode = SCANNER.next();
                        showUpdatedAdresMenu(adres.getStraatnaam(), adres.getHuisnummer(), adres.getToevoeging(),
                                nieuwePostcode, adres.getWoonplaats(), adres.getAdrestype(), adres.getKlant());
                        break;
            case "5":   System.out.print("Nieuwe woonplaats: ");
                        String nieuweWoonplaats = SCANNER.next();
                        showUpdatedAdresMenu(adres.getStraatnaam(), adres.getHuisnummer(), adres.getToevoeging(),
                                adres.getPostcode(), nieuweWoonplaats, adres.getAdrestype(), adres.getKlant());
                        break;
            case "6":   System.out.print("Nieuw adrestype: ");
                        String nieuwAdrestype = SCANNER.next();
                        showUpdatedAdresMenu(adres.getStraatnaam(), adres.getHuisnummer(), adres.getToevoeging(),
                                adres.getPostcode(), adres.getWoonplaats(), nieuwAdrestype, adres.getKlant());
                        break;
            default:    System.out.println(MAINERROR);
        }
    }

    private void showUpdatedAdresMenu(String straatnaam, int huisnummer, String toevoeging, String postcode,
                                 String woonplaats, String adrestype, Klant klant) {
        
        System.out.println("\nHet aangepaste adres:");
        System.out.println("Straatnaam:\t\t" + straatnaam + "\nHuisnummer:\t\t" + huisnummer +
                "\nToevoeging:\t\t" + toevoeging + "\nPostcode:\t\t" + postcode +
                 "\nWoonplaats:\t\t" + woonplaats + "\nAdrestype:\t\t" + adrestype);
        
        System.out.println("\nWilt u het aangepaste adres opslaan?");
        System.out.println("1. Ja\n0. Nee\n");
            
        String selection = getSelection();
        switch (selection) {
            case "0":   break;
            case "1":   AdresController adresController = new AdresController();
                        adresController.updateAdres(straatnaam, huisnummer, toevoeging, postcode,
                                woonplaats, adrestype, klant);
                        System.out.println("Adres opgeslagen.");
                        break;
            default:    System.out.println(MAINERROR);
        }
    }
    
    private void showDeleteAdresMenu() {
        
        Adres adres = showSelectAdresMenu();
        int id = adres.getId();
        
        setViewName("Adres verwijderen");
        printHeader();
        
        System.out.println("Wilt u dit adres verwijderen?");
        System.out.println("1. Ja\n0. Nee\n");
            
        String selection = getSelection();
        switch (selection) {
            case "0":   break;
            case "1":   AdresController adresController = new AdresController();
                        adresController.deleteAdres(id);
                        System.out.println("Adres verwijderd.");
                        break;
            default:    System.out.println(MAINERROR);
        }
    }
}
