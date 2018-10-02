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
        
        setViewName("Adres toevoegen");
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
        
        Adres adres = new Adres();

        System.out.println("Voeg een nieuw adres toe voor deze klant.\n");
        System.out.print("Voer de straatnaam voor het adres in (en druk dan op enter): ");
        adres.setStraatnaam(SCANNER.next());
        System.out.print("Voer het huisnummer voor het adres in (en druk dan op enter): ");
        adres.setHuisnummer(SCANNER.nextInt());
        System.out.print("Voer de toevoeging voor het adres in (en druk dan op enter): ");
        adres.setToevoeging(SCANNER.next());
        
        System.out.print("Voer de postcode voor het adres in (en druk dan op enter): ");
        adres.setPostcode(SCANNER.next());
        
        
        System.out.print("Voer de woonplaats voor het adres in (en druk dan op enter): ");
        adres.setWoonplaats(SCANNER.next());
        adres.setAdrestype(getAdrestype());
        adres.setKlant(klant);

        System.out.println("\nHet opgegeven adres:\n" + adres.toString());
        System.out.println("\nIs dit correct?");
        System.out.println("1. Ja, opslaan.\n0. Nee, opnieuw invoeren.\n");
        switch (getSelection()) {
            case "0":   break;
            case "1":   AdresController adresController = new AdresController();
                        adresController.insertAdres(adres);
                        System.out.println("Adres toegevoegd.");
                        break;
            default:    System.out.println(MAINERROR);
        }
    }
    
    private String getAdrestype() {
        
        String adrestype = "";
        System.out.println("\nSelecteer adrestype:");
        System.out.println("1. Factuur\n2. Post\n3. Woon\n");
        switch (getSelection()) {
            case "1":   adrestype = "factuur"; break;
            case "2":   adrestype = "post"; break;
            case "3":   adrestype = "woon";
        }
        return adrestype;
    }
    
    private Adres showSelectAdresMenu() {
        
        setViewName("Adres zoeken\t");
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
        setViewName("Adres aanpassen\t");
        printHeader();
        
        System.out.println("Wat wilt u aanpassen?");
        System.out.println("1. Straatnaam\n2. Huisnummer\n3. Toevoeging\n4. Postcode\n" +
                "5. Woonplaats\n6. Adrestype\n\n0. Niets, terug naar Adressenpagina\n");
        switch (getSelection()) {
            case "0":   break;
            case "1":   System.out.print("Nieuwe straatnaam: ");
                        adres.setStraatnaam(SCANNER.next());
                        showUpdatedAdresMenu(adres);
                        break;
            case "2":   System.out.print("Nieuw huisnummer: ");
                        adres.setHuisnummer(SCANNER.nextInt());
                        showUpdatedAdresMenu(adres);
                        break;
            case "3":   System.out.print("Nieuwe toevoeging: ");
                        adres.setToevoeging(SCANNER.next());
                        showUpdatedAdresMenu(adres);
                        break;
            case "4":   System.out.print("Nieuwe postcode: ");
                        adres.setPostcode(SCANNER.next());
                        showUpdatedAdresMenu(adres);
                        break;
            case "5":   System.out.print("Nieuwe woonplaats: ");
                        adres.setWoonplaats(SCANNER.next());
                        showUpdatedAdresMenu(adres);
                        break;
            case "6":   System.out.print("Nieuw adrestype: ");
                        adres.setAdrestype(getAdrestype());
                        showUpdatedAdresMenu(adres);
                        break;
            default:    System.out.println(MAINERROR);
        }
    }

    private void showUpdatedAdresMenu(Adres adres) {
        
        System.out.println("\nHet aangepaste adres:\n" + adres.toString());

        System.out.println("\nWilt u het aangepaste adres opslaan?");
        System.out.println("1. Ja\n0. Nee\n");
        switch (getSelection()) {
            case "0":   break;
            case "1":   AdresController adresController = new AdresController();
                        adresController.updateAdres(adres);
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
        switch (getSelection()) {
            case "0":   break;
            case "1":   AdresController adresController = new AdresController();
                        adresController.deleteAdres(id);
                        System.out.println("Adres verwijderd.");
                        break;
            default:    System.out.println(MAINERROR);
        }
    }
}
