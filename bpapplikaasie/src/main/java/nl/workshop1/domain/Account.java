package nl.workshop1.domain;

/**
 *
 * @author Vosjes
 */
public class Account {
    
    private int id;
    private String username;
    private String wachtwoord;
    private Klant klant;
    private char accountTypeId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public Klant getKlant() {
        return klant;
    }

    public void setKlant(Klant klant) {
        this.klant = klant;
    }

    public char getAccountTypeId() {
        return accountTypeId;
    }

    public void setAccountTypeId(char accountTypeId) {
        this.accountTypeId = accountTypeId;
    }
}
