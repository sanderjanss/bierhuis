package be.vdab.bierhuis.domain;

import java.math.BigDecimal;

public class Bestelbonlijn {
    private final long bestelbonid;
    private final long bierid;
    private final BigDecimal aantal;
    private final BigDecimal prijs;

    public Bestelbonlijn(long bestelbonid, long bierid, BigDecimal aantal, BigDecimal prijs) {
        this.bestelbonid = bestelbonid;
        this.bierid = bierid;
        this.aantal = aantal;
        this.prijs = prijs;
    }

    public long getBestelbonid() {
        return bestelbonid;
    }

    public long getBierid() {
        return bierid;
    }

    public BigDecimal getAantal() {
        return aantal;
    }

    public BigDecimal getPrijs() {
        return prijs;
    }
}
