package nl.workshop1.view;

/**
 *
 * @author Vosjes
 */
public class LoginMenuView extends MenuView {
    
    private String username;
    private String wachtwoord;
    
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getWachtwoord() {
        return wachtwoord;
    }

    public void setWachtwoord(String wachtwoord) {
        this.wachtwoord = wachtwoord;
    }

    @Override
    public void showMenu() {
        
        System.out.println("\n" + MAINTOPBOTTOM + "\n");
        System.out.println("Welkom bij Boer Piets kazen\t\t\tInlogscherm\n");
        System.out.println("Log in met uw gebruikersnaam en wachtwoord.\n");

        // Roep de scanner methode aan
        setUsername(getStringInput("Voer uw gebruikersnaam in (en druk dan op enter): "));
        setWachtwoord(getStringInput("Voer uw wachtwoord in (en druk dan op enter): "));

        System.out.println("\n" + MAINTOPBOTTOM + "\n");
        
        /*
        TODO M - Naar HoofdMenuView (ervan uitgaande dat gebruikersnaam en
        wachtwoord in orde zijn).
        TODO M - Via LoginController data via Account DAO halen en beschikbaar
        maken voor de verdere duur van de applicatie.
        TODO S - Als gebruikersnaam en wachtwoord niet goed zijn, foutmelding
        opwerpen en opnieuw om gebruikersnaam en wachtwoord vragen.
        */
        
    }
}
