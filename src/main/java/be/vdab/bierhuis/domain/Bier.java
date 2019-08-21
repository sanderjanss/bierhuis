package be.vdab.bierhuis.domain;

import java.math.BigDecimal;

public class Bier {
    private final long id;
    private final String naam;
    private final long brouwerid;
    private final long soortid;
    private final BigDecimal alcohol;
    private final BigDecimal prijs;
    private final long besteld;

    public Bier(long id, String naam, long brouwerid, long soortid, BigDecimal alcohol, BigDecimal prijs, long besteld) {
        this.id = id;
        this.naam = naam;
        this.brouwerid = brouwerid;
        this.soortid = soortid;
        this.alcohol = alcohol;
        this.prijs = prijs;
        this.besteld = besteld;
    }

    public long getId() {
        return id;
    }

    public String getNaam() {
        return naam;
    }

    public long getBrouwerid() {
        return brouwerid;
    }

    public long getSoortid() {
        return soortid;
    }

    public BigDecimal getAlcohol() {
        return alcohol;
    }

    public BigDecimal getPrijs() {
        return prijs;
    }

    public long getBesteld() {
        return besteld;
    }
}
