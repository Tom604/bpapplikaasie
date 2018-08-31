package nl.workshop1.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

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

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + this.id;
        hash = 31 * hash + Objects.hashCode(this.totaalprijs);
        hash = 31 * hash + Objects.hashCode(this.datumTijd);
        hash = 31 * hash + Objects.hashCode(this.klant);
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
        final Bestelling other = (Bestelling) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.totaalprijs, other.totaalprijs)) {
            return false;
        }
        if (!Objects.equals(this.datumTijd, other.datumTijd)) {
            return false;
        }
        if (!Objects.equals(this.klant, other.klant)) {
            return false;
        }
        return true;
    }
}
