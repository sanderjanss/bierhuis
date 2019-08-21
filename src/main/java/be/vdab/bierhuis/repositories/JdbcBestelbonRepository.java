package be.vdab.bierhuis.repositories;

import be.vdab.bierhuis.domain.Bestelbon;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class JdbcBestelbonRepository implements BestelbonRepository {


    private final JdbcTemplate template;
    private final SimpleJdbcInsert insert;

    public JdbcBestelbonRepository(JdbcTemplate template) {
        this.template = template;
        this.insert = new SimpleJdbcInsert(template);
        insert.withTableName("bestelbonnen");
        insert.usingGeneratedKeyColumns("id");
    }

    @Override
    public long create(Bestelbon bon) {
        Map<String, Object> kolomWaarden = new HashMap<>();
        kolomWaarden.put("naam", bon.getNaam());
        kolomWaarden.put("straat", bon.getStraat());
        kolomWaarden.put("huisNr", bon.getHuisNr());
        kolomWaarden.put("postcode", bon.getPostcode());
        kolomWaarden.put("gemeente", bon.getGemeente());
        Number id = insert.executeAndReturnKey(kolomWaarden);
        return id.longValue();
    }
}
