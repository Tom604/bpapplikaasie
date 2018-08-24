package nl.workshop1.domain;

import java.util.Objects;

/**
 *
 * @author Vosjes
 */
public class Adres {
    
    private int id;
    private String straatnaam;
    private int huisnummer;
    private String toevoeging;
    private String postcode;
    private String woonplaats;
    private String adrestype;
    private Klant klant;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStraatnaam() {
        return straatnaam;
    }

    public void setStraatnaam(String straatnaam) {
        this.straatnaam = straatnaam;
    }

    public int getHuisnummer() {
        return huisnummer;
    }

    public void setHuisnummer(int huisnummer) {
        this.huisnummer = huisnummer;
    }

    public String getToevoeging() {
        return toevoeging;
    }

    public void setToevoeging(String toevoeging) {
        this.toevoeging = toevoeging;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getWoonplaats() {
        return woonplaats;
    }

    public void setWoonplaats(String woonplaats) {
        this.woonplaats = woonplaats;
    }

    public String getAdrestype() {
        return adrestype;
    }

    public void setAdrestype(String adrestype) {
        this.adrestype = adrestype;
    }

    public Klant getKlant() {
        return klant;
    }

    public void setKlant(Klant klant) {
        this.klant = klant;
    }

    @Override
    public String toString() {
        return "Adres{" + "id=" + id + ", straatnaam=" + straatnaam + ", huisnummer="
                + huisnummer + ", toevoeging=" + toevoeging + ", postcode=" + postcode
                + ", woonplaats=" + woonplaats + ", adrestype=" + adrestype + ", klant=" + klant + '}';
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 71 * hash + this.id;
        hash = 71 * hash + Objects.hashCode(this.straatnaam);
        hash = 71 * hash + this.huisnummer;
        hash = 71 * hash + Objects.hashCode(this.toevoeging);
        hash = 71 * hash + Objects.hashCode(this.postcode);
        hash = 71 * hash + Objects.hashCode(this.woonplaats);
        hash = 71 * hash + Objects.hashCode(this.adrestype);
        hash = 71 * hash + Objects.hashCode(this.klant);
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
        final Adres other = (Adres) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.huisnummer != other.huisnummer) {
            return false;
        }
        if (!Objects.equals(this.straatnaam, other.straatnaam)) {
            return false;
        }
        if (!Objects.equals(this.toevoeging, other.toevoeging)) {
            return false;
        }
        if (!Objects.equals(this.postcode, other.postcode)) {
            return false;
        }
        if (!Objects.equals(this.woonplaats, other.woonplaats)) {
            return false;
        }
        if (!Objects.equals(this.adrestype, other.adrestype)) {
            return false;
        }
        if (!Objects.equals(this.klant, other.klant)) {
            return false;
        }
        return true;
    }
}
