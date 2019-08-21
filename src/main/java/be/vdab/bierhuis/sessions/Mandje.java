package be.vdab.bierhuis.sessions;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.Map;

@Component
@SessionScope
public class Mandje implements Serializable {
    private final static long serialVersionUID = 1L;
    private final Map<Long, BigDecimal > artikelsEnAantal = new LinkedHashMap<>();

    public void voegToe(long id, BigDecimal aantal){
        if (artikelsEnAantal.containsKey(id)){
            artikelsEnAantal.put(id, artikelsEnAantal.get(id).add(aantal));
        } else {
            artikelsEnAantal.put(id, aantal);
        }
    }

    public Map<Long, BigDecimal> getArtikelsEnAantal() {
        return artikelsEnAantal;
    }

    public void mandjeLeegMaken(){
        artikelsEnAantal.clear();
    }

    public boolean isGevuld(){
        return ! artikelsEnAantal.isEmpty();
    }

}
