package nl.workshop1.domain;

import java.math.BigDecimal;

/**
 *
 * @author Vosjes
 */
public class Bestelling {
    
    private int id;
    private BigDecimal totaalprijs;
    private int klantId;

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

    public int getKlantId() {
        return klantId;
    }

    public void setKlantId(int klantId) {
        this.klantId = klantId;
    }
}
