package be.vdab.bierhuis.domain;

import org.junit.Before;
import org.junit.Test;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

public class BestelbonTest {
    private static final String TEST = "test";
    private Validator validator;

    @Before
    public void before() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void naamOk() {
        assertThat(validator.validateValue(
                Bestelbon.class, "naam", "test")).isEmpty();
    }

    @Test
    public void naamMagNietNullZijn() {
        assertThat(validator.validateValue(Bestelbon.class, "naam", null)).isNotEmpty();
    }

    @Test
    public void naamMoetMinstens1tekenbevatten() {
        assertThat(validator.validateValue(Bestelbon.class,"naam", "")).isNotEmpty();
    }
    @Test
    public void straatOk() {
        assertThat(validator.validateValue(
                Bestelbon.class, "straat", "test")).isEmpty();
    }

    @Test
    public void straatMagNietNullZijn() {
        assertThat(validator.validateValue(Bestelbon.class, "straat", null)).isNotEmpty();
    }
    @Test
    public void straatMoetMinstens1tekenbevatten() {
        assertThat(validator.validateValue(Bestelbon.class,"straat", "")).isNotEmpty();
    }

    @Test
    public void huisNrOk() {
        assertThat(validator.validateValue(
                Bestelbon.class, "huisNr", "nummer 5 bus 2")).isEmpty();
    }

    @Test
    public void huisNrMagNietNullZijn() {
        assertThat(validator.validateValue(Bestelbon.class, "huisNr", null)).isNotEmpty();
    }
    @Test
    public void huisNrMoetMinstens1tekenbevatten() {
        assertThat(validator.validateValue(Bestelbon.class,"huisNr", "")).isNotEmpty();
    }

    @Test
    public void postcodeOk() {
        assertThat(validator.validateValue(
                Bestelbon.class, "postcode", 3000)).isEmpty();
    }

    @Test
    public void postcodeMagNietNullZijn() {
        assertThat(validator.validateValue(Bestelbon.class, "postcode", null)).isNotEmpty();
    }

    @Test
    public void postcodeMoetGroterZijnDan1000kleinerDan9999() {
        assertThat(validator.validateValue(Bestelbon.class, "postcode", 999)).isNotEmpty();
    }

    @Test
    public void postcodeMoetGroterZijnDan1000kleinerDan9999versie2() {
        assertThat(validator.validateValue(Bestelbon.class, "postcode", 10000)).isNotEmpty();
    }

    @Test
    public void gemeenteOk() {
        assertThat(validator.validateValue(
                Bestelbon.class, "gemeente", "test")).isEmpty();
    }

    @Test
    public void gemeenteMagNietNullZijn() {
        assertThat(validator.validateValue(Bestelbon.class, "gemeente", null)).isNotEmpty();
    }

    @Test
    public void gemeenteMoetMinstens1tekenbevatten() {
        assertThat(validator.validateValue(Bestelbon.class,"gemeente", "")).isNotEmpty();
    }


}
