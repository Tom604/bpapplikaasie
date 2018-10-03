package nl.workshop1.view;

import java.util.Scanner;
import nl.workshop1.controller.LoginController;
import nl.workshop1.utility.InputValidator;

/**
 *
 * @author Vosjes
 */
public abstract class MenuView {
    
    protected final String MAINTOPBOTTOM = "\n*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*--*\n";
    protected final String MAINHEADER = "Boer Piets kazen\t";
    protected final String MAINCHOICE = "Maak uw keuze (voer het getal in en druk dan op enter): ";
    protected final String MAINERROR = "Uw invoer is onjuist. Probeer het opnieuw.";
    protected final String MAINGOODBYE = "Tot ziens bij Boer Piets kazen\n";
    protected final Scanner SCANNER = new Scanner(System.in);
    protected String viewName;
    
    /**
     * Deze methode print de standaard headers voor de verschillende views
     */
    protected void printHeader() {
        if (viewName.equals("Startscherm\t") || viewName.equals("Inlogscherm\t")) {
            System.out.println(MAINTOPBOTTOM);
            System.out.println(MAINHEADER + viewName + "\tNiet ingelogd\n");
        }
        else {
            System.out.println(MAINTOPBOTTOM);
            System.out.println(MAINHEADER + viewName + "\tIngelogd als " +
                    LoginController.loginnaam + "\n");
        }
    }
    
    protected String getSelection() {
        System.out.print(MAINCHOICE);
        String selection = SCANNER.next();
        System.out.println(MAINTOPBOTTOM);
        return selection;
    }
    
    protected String getInputWithValidation(String type) {
        
        InputValidator inputValidator = new InputValidator();
        boolean b = true;
        String input = "";
        
        do {
            switch (type) {
                case "postcode":    System.out.print("Voer de postcode voor het adres in" +
                                            "(en druk dan op enter): ");
                                    b = inputValidator.validatePostcode(input = SCANNER.nextLine());
                                    break;
                case "wachtwoord":  System.out.print("Voer het wachtwoord voor het account in " +
                                            "(en druk dan op enter): ");
                                    b = inputValidator.validateWachtwoord(input = SCANNER.next());
                                    break;
            }
            if (b == false) {
                System.out.println(MAINERROR);
            }
        } while (b == false);
        
        return input;
    }
    
    public abstract void showMenu();
    public abstract void setViewName(String viewName);
}