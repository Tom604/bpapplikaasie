package nl.workshop1.view;

import nl.workshop1.controller.LoginController;

/**
 *
 * @author Vosjes
 */
public class HoofdMenuView extends MenuView {
    
    private final String LOGINHEADER = "Welkom bij Boer Piets kazen";
    
    /**
     * Het menu waarmee de applicatie opent.
     */
    public void startMenu() {
        
        int selectie = 0;
        
        do {
            System.out.println("\n" + MAINTOPBOTTOM + "\n");
            System.out.println(LOGINHEADER + "\tStartscherm\n");
            System.out.println("1. Inloggen\n0. Afsluiten\n");
            selectie = getIntInput(MAINCHOICE, 1);
            
            switch (selectie) {
                case 1: System.out.println("\n" + MAINTOPBOTTOM + "\n");
                        loginMenu(); break;
                case 0: System.out.println("\n" + MAINTOPBOTTOM + "\n");
                        System.out.println("Tot ziens bij Boer Piets kazen\n");
                        System.exit(0);
            }
        } while (selectie != 0);
    }
    
    /**
     * Het menu waarmee ingelogd kan worden in de applicatie.
     */
    public void loginMenu() {
        
        int selectie = 0;
        
        do {
            System.out.println("\n" + MAINTOPBOTTOM + "\n");
            System.out.println(LOGINHEADER + "\tInlogscherm\n");
            System.out.println("Log in met uw gebruikersnaam en wachtwoord.\n");

            // Roep de get...Input methode aan (MenuView)
            String username = getStringInput("Voer uw gebruikersnaam in (en druk dan op enter): ");
            String wachtwoord = getStringInput("Voer uw wachtwoord in (en druk dan op enter): ");
            System.out.println("\n" + MAINTOPBOTTOM + "\n");
        
            // Roep de compareData methode aan (LoginController)
            LoginController loginController = new LoginController();
            boolean comparison = loginController.compareData(username, wachtwoord);
        
            // Doorgaan, opnieuw of stoppen?
            if (comparison == true) {
                showMenu(username);
                selectie = 1;
                break;
            }
            
            // Bij gefaalde comparison
            System.out.println("\n" + MAINTOPBOTTOM + "\n");
            selectie = getIntInput("U heeft een verkeerde gebruikersnaam en/of " +
                    "wachtwoord opgegeven\n\nWilt u:\n1. Opnieuw proberen" +
                    "\n0. Afsluiten\n\n" + MAINCHOICE, 1);
            System.out.println("\n" + MAINTOPBOTTOM + "\n");
                
            if (selectie == 0) {
                System.out.println("\n" + MAINTOPBOTTOM + "\n");
                System.out.println("Tot ziens bij Boer Piets kazen\n");
                System.exit(0);
            }
        } while (selectie != 0);
    }
    
    /**
     * De implementatie van de showMenu() methode uit de MenuView klasse waarmee
     * de applicatie begint na inloggen.
     * @param loginNaam De loginnaam die vanuit de loginMenu() methode in de
     * HoofdMenuView klasse naar de andere views doorgegeven wordt.
     */
    @Override
    public void showMenu(String loginNaam) {
        
        int selectie = 0;
        
        do {
            System.out.println("\n" + MAINTOPBOTTOM + "\n");
            System.out.println(MAINHEADER1 + "\t\tHoofdpagina" + "\t" +
                    MAINHEADER2 + loginNaam + "\n");
            System.out.println("1. Accounts\n2. Adressen\n3. Artikelen\n" +
                    "4. Bestellingen\n5. Klanten\n\n0. Uitloggen en terug " +
                    "naar het Startscherm\n");
            selectie = getIntInput(MAINCHOICE, 5);
            System.out.println("\n" + MAINTOPBOTTOM + "\n");
            
            switch (selectie) {
//                case 1: MenuView accountMenuView = new AccountMenuView();
//                        accountMenuView.showMenu(loginNaam); break;
//                case 2: MenuView adresMenuView = new AdresMenuView();
//                        adresMenuView.showMenu(loginNaam); break;
                case 3: MenuView artikelMenuView = new ArtikelMenuView();
                        artikelMenuView.showMenu(loginNaam); break;
//                case 4: MenuView bestellingMenuView = new BestellingMenuView();
//                        bestellingMenuView.showMenu(loginNaam); break;
//                case 5: MenuView klantMenuView = new KlantMenuView();
//                        klantMenuView.showMenu(loginNaam);
            }
        } while (selectie != 0);
        
        startMenu();
    }
}
