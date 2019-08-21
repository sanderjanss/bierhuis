package be.vdab.bierhuis.form;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class AantalBakkenForm {

    @NotNull
    @Min(2)
    private BigDecimal aantal;


    public AantalBakkenForm(BigDecimal aantal) {
        this.aantal = aantal;
    }

    public BigDecimal getAantal() {
        return aantal;
    }

}
