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
        
        String selectie = "";
        
        do {
            setViewName("Hoofdpagina");
            printHeader();
            
            System.out.println("1. Accounts\n2. Adressen\n3. Artikelen\n" +
                    "4. Bestellingen\n5. Klanten\n\n0. Uitloggen en terug " +
                    "naar het Startscherm\n");
            
            System.out.print(MAINCHOICE);
            selectie = SCANNER.nextLine();
            System.out.println(MAINTOPBOTTOM);
            
            switch (selectie) {
                case "0":   break;
//                case 1:     MenuView accountMenuView = new AccountMenuView();
//                            accountMenuView.showMenu(loginNaam); break;
//                case 2:     MenuView adresMenuView = new AdresMenuView();
//                            adresMenuView.showMenu(loginNaam); break;
                case "3":   MenuView artikelMenuView = new ArtikelMenuView();
                            artikelMenuView.showMenu(); break;
//                case 4:     MenuView bestellingMenuView = new BestellingMenuView();
//                            bestellingMenuView.showMenu(loginNaam); break;
//                case 5:     MenuView klantMenuView = new KlantMenuView();
//                            klantMenuView.showMenu(loginNaam);
                default:    System.out.println(MAINERROR);
            }
        } while (selectie.equals("0") == false);
    }
    
    @Override
    public void setViewName(String viewName) {
        this.viewName = viewName;
    }
}
