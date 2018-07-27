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
    private Klant klant;
    private AdresType adresType;

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

    public Klant getKlant() {
        return klant;
    }

    public void setKlant(Klant klant) {
        this.klant = klant;
    }

    public AdresType getAdresType() {
        return adresType;
    }

    public void setAdresType(AdresType adresType) {
        this.adresType = adresType;
    }

    @Override
    public String toString() {
        return "Adres{" + "id=" + id + ", straatnaam=" + straatnaam + ", huisnummer="
                + huisnummer + ", toevoeging=" + toevoeging + ", postcode=" + postcode
                + ", woonplaats=" + woonplaats + ", klant=" + klant + ", adresType="
                + adresType + '}';
    }
}
