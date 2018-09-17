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
        
        String selection = "";
        
        do {
            setViewName("Startscherm\t");
            printHeader();
            
            System.out.println("1. Inloggen\n0. Afsluiten\n");
            selection = getSelection();
            switch (selection) {
                case "0":   System.out.println(MAINGOODBYE); break;
                case "1":   showMenu(); break;
                default:    System.out.println(MAINERROR);
            }
        } while (selection.equals("0") == false);
    }
    
    /**
     * Het menu waarmee ingelogd kan worden in de applicatie.
     */
    @Override
    public void showMenu() {
        
        setViewName("Inlogscherm\t");
        printHeader();
        
        System.out.println("Log in met uw gebruikersnaam en wachtwoord.\n");
        System.out.print("Voer uw gebruikersnaam in (en druk dan op enter): ");
        String username = SCANNER.next();
        System.out.print("Voer uw wachtwoord in (en druk dan op enter): ");
        String wachtwoord = SCANNER.next();
        
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
