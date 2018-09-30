package nl.workshop1.view;

import java.util.Scanner;
import nl.workshop1.controller.LoginController;

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
    
    public abstract void showMenu();
    public abstract void setViewName(String viewName);
}