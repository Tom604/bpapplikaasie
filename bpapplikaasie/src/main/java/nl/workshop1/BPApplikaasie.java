package nl.workshop1;

import nl.workshop1.view.LoginMenuView;
import nl.workshop1.view.MenuView;

/**
 *
 * @author Vosjes
 */
public class BPApplikaasie {
    public static void main(String[] args) {
        
        //TODO Connect to database and check if connection is good (otherwise close app and log)
        
        //Start bpapplikaasie
       
        MenuView loginMenuView = new LoginMenuView();
        loginMenuView.showMenu();
        
    }
}
