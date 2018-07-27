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
    private AccountType accountType;

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

    public AccountType getAccountType() {
        return accountType;
    }

    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }

    @Override
    public String toString() {
        return "Account{" + "id=" + id + ", username=" + username + ", wachtwoord="
                + wachtwoord + ", klant=" + klant + ", accountType=" + accountType + '}';
    }
}
