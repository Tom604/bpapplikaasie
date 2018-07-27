package nl.workshop1.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 *
 * @author Vosjes
 */
public class Bestelling {
    
    private int id;
    private BigDecimal totaalprijs;
    private LocalDateTime datumTijd;
    private Klant klant;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public BigDecimal getTotaalprijs() {
        return totaalprijs;
    }

    public void setTotaalprijs(BigDecimal totaalprijs) {
        this.totaalprijs = totaalprijs;
    }

    public LocalDateTime getDatumTijd() {
        return datumTijd;
    }

    public void setDatumTijd(LocalDateTime datumTijd) {
        this.datumTijd = datumTijd;
    }
    
    public Klant getKlant() {
        return klant;
    }

    public void setKlant(Klant klant) {
        this.klant = klant;
    }

    @Override
    public String toString() {
        return "Bestelling{" + "id=" + id + ", totaalprijs=" + totaalprijs
                + ", datumTijd=" + datumTijd + ", klant=" + klant + '}';
    }
}
