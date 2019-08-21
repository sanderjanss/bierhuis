package be.vdab.bierhuis.repositories;

import be.vdab.bierhuis.domain.Brouwer;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class JdbcBrouwerRepository implements BrouwerRepository {
    private JdbcTemplate template;

    public JdbcBrouwerRepository(JdbcTemplate template) {
        this.template = template;
    }
    private final RowMapper<Brouwer> brouwerMapper =
            (result, rowNum)-> new Brouwer(result.getLong("id"),
                    result.getString("naam"),
                    result.getString("straat"),
                    result.getString("huisNr"),
                    result.getInt("postcode"),
                    result.getString("gemeente"),
                    result.getLong("omzet"));

    @Override
    public List<Brouwer> findAll() {
        String sql = "select id, naam, straat, huisNr, postcode, gemeente, omzet from brouwers";
        return template.query(sql, brouwerMapper);
    }

    @Override
    public Optional<Brouwer> findById(long id) {
        try {
            String sql = "select id, naam, straat, huisNr, postcode, gemeente, omzet from brouwers where id=?";
            return Optional.of(template.queryForObject(sql, brouwerMapper, id));
        } catch (IncorrectResultSizeDataAccessException ex){
            return Optional.empty();
        }
    }
}
