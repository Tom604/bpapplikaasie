package nl.workshop1.view;

/**
 *
 * @author Vosjes
 */
public class HoofdMenuView extends MenuView {
    
    /**
     * Het hoofdmenu van de applicatie.
     */
    @Override
    public void showMenu() {
        
        String selection = "";
        
        do {
            setViewName("Hoofdpagina\t");
            printHeader();
            
            /*
            TODO - W: Hier nog een switch + methodes maken (zie ArtikelMenuView) voor de
            verschillende accounts: admin, medewerker en klant. Onderstaande is voor admin (ziet alles).
            */
            System.out.println("1. Accounts\n2. Adressen\n3. Artikelen\n" +
                    "4. Bestellingen\n5. Klanten\n\n0. Uitloggen en terug " +
                    "naar het Startscherm\n");
            
            selection = getSelection();
            
            switch (selection) {
                case "0":   break;
                case "1":     MenuView accountMenuView = new AccountMenuView();
                            accountMenuView.showMenu(); break;
                case "2":     MenuView adresMenuView = new AdresMenuView();
                            adresMenuView.showMenu(); break;
                case "3":   MenuView artikelMenuView = new ArtikelMenuView();
                            artikelMenuView.showMenu(); break;
//                case "4":     MenuView bestellingMenuView = new BestellingMenuView();
//                            bestellingMenuView.showMenu(); break;
                case "5":   MenuView klantMenuView = new KlantMenuView();
                            klantMenuView.showMenu(); break;
                default:    System.out.println(MAINERROR);
            }
        } while (selection.equals("0") == false);
    }
    
    @Override
    public void setViewName(String viewName) {
        this.viewName = viewName;
    }
}
