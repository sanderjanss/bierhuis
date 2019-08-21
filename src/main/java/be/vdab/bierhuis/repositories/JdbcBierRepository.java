package be.vdab.bierhuis.repositories;

import be.vdab.bierhuis.domain.Bier;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public class JdbcBierRepository implements BierRepository {
    private JdbcTemplate template;

    public JdbcBierRepository(JdbcTemplate template) {
        this.template = template;
    }
    private final RowMapper<Bier> bierMapper =
            (result, rowNum)-> new Bier(result.getLong("id"),
                    result.getString("naam"),
                    result.getLong("brouwerid"),
                    result.getLong("soortid"),
                    result.getBigDecimal("alcohol"),
                    result.getBigDecimal("prijs"),
                    result.getLong("besteld"));

    @Override
    public long findAantal() {
        String sql = "select count(*) from bieren";
        return template.queryForObject(sql, Long.class);
    }

    @Override
    public List<Bier> findAll() {
        String sql = "select id, naam, brouwerid, soortid, alcohol, prijs, besteld from bieren";
        return template.query(sql, bierMapper);
    }

    @Override
    public Optional<Bier> findById(long id) {
        try {
            String sql = "select id, naam, brouwerid, soortid, alcohol, prijs, besteld from bieren where id=?";
            return Optional.of(template.queryForObject(sql, bierMapper, id));
        } catch (IncorrectResultSizeDataAccessException ex){
            return Optional.empty();
        }
    }

    @Override
    public List<Bier> findByBrouwerId(long brouwerid) {
        String sql = "select id, naam, brouwerid, soortid, alcohol, prijs, besteld from bieren where brouwerid=?";
        return template.query(sql, bierMapper, brouwerid);
    }

    @Override
    public void update(long id, BigDecimal aantal){
        String sql = "update bieren set besteld= besteld + ? where id=?";
        template.update(sql, aantal, id);
    }


}
