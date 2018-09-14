package nl.workshop1.view;

import nl.workshop1.controller.LoginController;

/**
 *
 * @author Vosjes
 */
public class LoginMenuView extends MenuView {
    
    /**
     * Het menu waarmee de applicatie opent.
     */
    public void showStartMenu() {
        
        String selectie = "";
        
        do {
            setViewName("Startscherm");
            printHeader();
            
            System.out.println("1. Inloggen\n0. Afsluiten\n");
            
            /*
            Hier nog in MenuView een methode voor maken: getSelection() met String
            als return value: selectie = getSelection();?
            */
            System.out.print(MAINCHOICE);
            selectie = SCANNER.nextLine();
            System.out.println(MAINTOPBOTTOM);
            
            switch (selectie) {
                case "1":   showMenu(); break;
                case "0":   System.out.println(MAINGOODBYE); break;
                default:    System.out.println(MAINERROR); break;
            }
        } while (selectie.equals("0") == false);
    }
    
    /**
     * Het menu waarmee ingelogd kan worden in de applicatie.
     */
    @Override
    public void showMenu() {
        
        setViewName("Inlogscherm");
        printHeader();
        
        System.out.println("Log in met uw gebruikersnaam en wachtwoord.\n");
        System.out.print("Voer uw gebruikersnaam in (en druk dan op enter): ");
        String username = SCANNER.nextLine();
        System.out.print("Voer uw wachtwoord in (en druk dan op enter): ");
        String wachtwoord = SCANNER.nextLine();
        
        System.out.println(MAINTOPBOTTOM);
        
        // Roep de validateLogin() methode aan (LoginController)
        LoginController loginController = new LoginController();
        if (loginController.validateLogin(username, wachtwoord)) {
            HoofdMenuView hoofdMenuView = new HoofdMenuView();
            hoofdMenuView.showMenu();
        }
        // Bij gefaalde validatie
        else {
            System.out.println("U heeft een verkeerde gebruikersnaam en/of wachtwoord opgegeven.");
        }
    }
    
    @Override
    public void setViewName(String viewName) {
        this.viewName = viewName;
    }
}
