package be.vdab.bierhuis.form;

import org.junit.Before;
import org.junit.Test;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

public class AantalBakkenFormTest {
    private Validator validator;
    @Before
    public void before(){
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }
    @Test
    public void aantalOk() {
        assertThat(validator.validateValue(AantalBakkenForm.class, "aantal", BigDecimal.valueOf(2)))
                .isEmpty();
    }
    @Test
    public void aantalMoetVerschillenVanNull() {
        assertThat(validator.validateValue(AantalBakkenForm.class, "aantal", null))
                .isNotEmpty();
    }
    @Test
    public void aantalMoetMinstens2bakkenBevatten() {
        assertThat(validator.validateValue(AantalBakkenForm.class, "aantal", BigDecimal.ONE))
                .isNotEmpty();
    }
}
