package nl.workshop1.domain;

/**
 *
 * @author Vosjes
 */
public class BestelRegel {
    
    private int id;
    private Bestelling bestelling;
    private Artikel artikel;
    private int aantal;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getAantal() {
        return aantal;
    }

    public void setAantal(int aantal) {
        this.aantal = aantal;
    }

    @Override
    public String toString() {
        return "BestelRegel{" + "id=" + id + ", bestelling=" + bestelling
                + ", artikel=" + artikel + ", aantal=" + aantal + '}';
    }
}
