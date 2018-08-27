package nl.workshop1.domain;

import java.util.Objects;

/**
 *
 * @author Vosjes
 */
public class Account {
    
    private int id;
    private String username;
    private String wachtwoord;
    private String accounttype;
    private Klant klant;

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

    public String getAccounttype() {
        return accounttype;
    }

    public void setAccounttype(String accounttype) {
        this.accounttype = accounttype;
    }
    
    public Klant getKlant() {
        return klant;
    }

    public void setKlant(Klant klant) {
        this.klant = klant;
    }

    @Override
    public String toString() {
        return "Account{" + "id=" + id + ", username=" + username + ", wachtwoord="
                + wachtwoord + ", accounttype=" + accounttype + ", klant=" + klant + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + this.id;
        hash = 37 * hash + Objects.hashCode(this.username);
        hash = 37 * hash + Objects.hashCode(this.wachtwoord);
        hash = 37 * hash + Objects.hashCode(this.accounttype);
        hash = 37 * hash + Objects.hashCode(this.klant);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Account other = (Account) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.username, other.username)) {
            return false;
        }
        if (!Objects.equals(this.wachtwoord, other.wachtwoord)) {
            return false;
        }
        if (!Objects.equals(this.accounttype, other.accounttype)) {
            return false;
        }
        if (!Objects.equals(this.klant, other.klant)) {
            return false;
        }
        return true;
    }
}
