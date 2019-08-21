package be.vdab.bierhuis.domain;

import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.*;

public class Bestelbon {

    private final long id;
    @NotEmpty
    private final String naam;
    @NotEmpty
    private final String straat;
    @NotEmpty
    private final String huisNr;
    @NotNull
    @Range(min = 1000, max = 9999)
    private final int postcode;
    @NotEmpty
    private final String gemeente;

    public Bestelbon(long id, String naam, String straat, String huisNr, int postcode, String gemeente) {
        this.id = id;
        this.naam = naam;
        this.straat = straat;
        this.huisNr = huisNr;
        this.postcode = postcode;
        this.gemeente = gemeente;
    }

    public long getId() {
        return id;
    }

    public String getNaam() {
        return naam;
    }

    public String getStraat() {
        return straat;
    }

    public String getHuisNr() {
        return huisNr;
    }

    public int getPostcode() {
        return postcode;
    }

    public String getGemeente() {
        return gemeente;
    }
}
