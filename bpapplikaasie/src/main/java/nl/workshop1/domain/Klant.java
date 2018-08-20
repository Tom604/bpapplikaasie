package nl.workshop1.domain;

import java.util.Objects;

/**
 *
 * @author Vosjes
 */
public class Klant {
    
    private int id;
    private String voornaam;
    private String achternaam;
    private String tussenvoegsel;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getVoornaam() {
        return voornaam;
    }

    public void setVoornaam(String voornaam) {
        this.voornaam = voornaam;
    }

    public String getAchternaam() {
        return achternaam;
    }

    public void setAchternaam(String achternaam) {
        this.achternaam = achternaam;
    }

    public String getTussenvoegsel() {
        return tussenvoegsel;
    }

    public void setTussenvoegsel(String tussenvoegsel) {
        this.tussenvoegsel = tussenvoegsel;
    }
    
    @Override
    public String toString() {
        return "Klant{" + "id=" + id + ", voornaam=" + voornaam + ", achternaam="
                + achternaam + ", tussenvoegsel=" + tussenvoegsel + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + this.id;
        hash = 23 * hash + Objects.hashCode(this.voornaam);
        hash = 23 * hash + Objects.hashCode(this.achternaam);
        hash = 23 * hash + Objects.hashCode(this.tussenvoegsel);
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
        final Klant other = (Klant) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.voornaam, other.voornaam)) {
            return false;
        }
        if (!Objects.equals(this.achternaam, other.achternaam)) {
            return false;
        }
        if (!Objects.equals(this.tussenvoegsel, other.tussenvoegsel)) {
            return false;
        }
        return true;
    }
}
