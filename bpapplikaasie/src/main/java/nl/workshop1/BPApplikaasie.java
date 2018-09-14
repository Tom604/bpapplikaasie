package nl.workshop1;

import nl.workshop1.view.LoginMenuView;

/**
 *
 * @author Vosjes
 */
public class BPApplikaasie {
    public static void main(String[] args) {
        
        //TODO Connect to database and check if connection is good (otherwise close app and log)
        
        //Start bpapplikaasie
       
        LoginMenuView loginMenuView = new LoginMenuView();
        loginMenuView.showStartMenu();
        
    }
}
