package nl.workshop1.domain;

import java.math.BigDecimal;

/**
 *
 * @author Vosjes
 */
public class Artikel {
    
    private int id;
    private String naam;
    private BigDecimal prijs;
    private int voorraad;
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public BigDecimal getPrijs() {
        return prijs;
    }

    public void setPrijs(BigDecimal prijs) {
        this.prijs = prijs;
    }

    public int getVoorraad() {
        return voorraad;
    }

    public void setVoorraad(int voorraad) {
        this.voorraad = voorraad;
    }
    
    @Override
    public String toString() {
        return "Artikel: " + naam + "\nPrijs: €" + prijs + "\nVoorraad: " + voorraad;
    }
}
