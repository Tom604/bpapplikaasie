package nl.workshop1.domain;

import java.util.Objects;

/**
 *
 * @author Vosjes
 */
public class Bestelregel {
    
    private int id;
    private int aantal;
    private Bestelling bestelling;
    private Artikel artikel;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAantal() {
        return aantal;
    }

    public void setAantal(int aantal) {
        this.aantal = aantal;
    }
    
    public Bestelling getBestelling() {
        return bestelling;
    }

    public void setBestelling(Bestelling bestelling) {
        this.bestelling = bestelling;
    }

    public Artikel getArtikel() {
        return artikel;
    }

    public void setArtikel(Artikel artikel) {
        this.artikel = artikel;
    }

    @Override
    public String toString() {
        return "BestelRegel{" + "id=" + id + ", bestelling=" + bestelling
                + ", artikel=" + artikel + ", aantal=" + aantal + '}';
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 17 * hash + this.id;
        hash = 17 * hash + Objects.hashCode(this.bestelling);
        hash = 17 * hash + Objects.hashCode(this.artikel);
        hash = 17 * hash + this.aantal;
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
        final Bestelregel other = (Bestelregel) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.aantal != other.aantal) {
            return false;
        }
        if (!Objects.equals(this.bestelling, other.bestelling)) {
            return false;
        }
        if (!Objects.equals(this.artikel, other.artikel)) {
            return false;
        }
        return true;
    }
}
