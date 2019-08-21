package be.vdab.bierhuis.repositories;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@JdbcTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import(JdbcBierRepository.class)
@Sql("/insertBieren.sql")
public class BierRepositoryTest extends AbstractTransactionalJUnit4SpringContextTests {
    private static final String BIEREN = "bieren";

    @Autowired
    private JdbcBierRepository bierRepository;

    @Test
    public void findAantal(){
        assertThat(bierRepository.findAantal()).isEqualTo(super.countRowsInTable(BIEREN));
    }

    @Test
    public void findAll(){
        assertThat(bierRepository.findAll()).hasSize(super.countRowsInTable(BIEREN))
                .extracting(bier->bier.getId()).isSorted();
    }

    private long idVanTestBier(){
        return super.jdbcTemplate.queryForObject("select id from bieren where naam ='test'", Long.class);
    }

    @Test
    public void findById(){
       assertThat(bierRepository.findById(idVanTestBier()).get().getNaam()).isEqualTo("test");
    }
}
