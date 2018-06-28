package nl.workshop1.domain;

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
    private int klantId;
    private char adresTypeId;

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

    public int getKlantId() {
        return klantId;
    }

    public void setKlantId(int klantId) {
        this.klantId = klantId;
    }

    public char getAdresTypeId() {
        return adresTypeId;
    }

    public void setAdresTypeId(char adresTypeId) {
        this.adresTypeId = adresTypeId;
    }
    
}
