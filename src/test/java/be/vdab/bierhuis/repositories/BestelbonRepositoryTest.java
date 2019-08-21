package be.vdab.bierhuis.repositories;

import be.vdab.bierhuis.domain.Bestelbon;
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
@Import(JdbcBestelbonRepository.class)
@Sql("/insertBestelbon.sql")
public class BestelbonRepositoryTest extends AbstractTransactionalJUnit4SpringContextTests {
    private static final String BESTELBON = "bestelbonnen";
    @Autowired
    private JdbcBestelbonRepository repository;

    @Test
    public void create() {
        long id = repository.create(new Bestelbon(0, "test", "test", "1", 1111, "wilrijk"));
        assertThat(id).isPositive();
        assertThat(super.countRowsInTableWhere(BESTELBON, "id=" + id)).isOne();
    }
}
