package be.vdab.bierhuis.repositories;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

@RunWith(SpringRunner.class)
@JdbcTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import(JdbcBrouwerRepository.class)
@Sql("/insertBrouwers.sql")
public class BrouwerRepositoryTest extends AbstractTransactionalJUnit4SpringContextTests {
    private static final String BROUWERS = "brouwers";

    @Autowired
    private JdbcBrouwerRepository brouwerRepository;

    @Test
    public void findAll() {
        assertThat(brouwerRepository.findAll())
                .hasSize(super.countRowsInTable(BROUWERS))
                .extracting(brouwer->brouwer.getId()).isSorted();
    }

    private long idVanTestBrouwer(){
        return super.jdbcTemplate.queryForObject("select id from brouwers where naam ='test'", Long.class);
    }

    @Test
    public void findById(){
        assertThat(brouwerRepository.findById(idVanTestBrouwer()).get().getNaam()).isEqualTo("test");
    }
}
