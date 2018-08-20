package nl.workshop1.domain;

import java.math.BigDecimal;
import java.util.Objects;

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
        return "Artikel{" + "id=" + id + ", naam=" + naam + ", prijs=" + prijs + ", voorraad=" + voorraad + '}';
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 29 * hash + this.id;
        hash = 29 * hash + Objects.hashCode(this.naam);
        hash = 29 * hash + Objects.hashCode(this.prijs);
        hash = 29 * hash + this.voorraad;
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
        final Artikel other = (Artikel) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.voorraad != other.voorraad) {
            return false;
        }
        if (!Objects.equals(this.naam, other.naam)) {
            return false;
        }
        if (!Objects.equals(this.prijs, other.prijs)) {
            return false;
        }
        return true;
    }
}
