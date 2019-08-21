package be.vdab.bierhuis.services;

import be.vdab.bierhuis.domain.Bier;
import be.vdab.bierhuis.repositories.BierRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED)
public class DefaultBierService implements BierService{
    private final BierRepository bierRepository;

    public DefaultBierService(BierRepository bierRepository) {
        this.bierRepository = bierRepository;
    }

    @Override
    public long findAantal() {
        return bierRepository.findAantal();
    }

    @Override
    public List<Bier> findAll() {
        return bierRepository.findAll();
    }

    @Override
    public Optional<Bier> findById(long id) {
        return bierRepository.findById(id);
    }

    @Override
    public List<Bier> findByBrouwerId(long brouwerid) {
        return bierRepository.findByBrouwerId(brouwerid);
    }


    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED)
    @Override
    public void update(long id, BigDecimal aantal) {
        bierRepository.update(id, aantal);
    }
}
