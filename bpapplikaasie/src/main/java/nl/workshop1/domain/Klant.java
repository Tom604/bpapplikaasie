package nl.workshop1.domain;

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
        if (tussenvoegsel != null) {
            return "Klant: " + voornaam + " " + tussenvoegsel + " " + achternaam;
        }
        
        return "Klant: " + voornaam + " " + achternaam;
    }
}
