package be.vdab.bierhuis.services;

import be.vdab.bierhuis.domain.Brouwer;
import be.vdab.bierhuis.repositories.BrouwerRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED)
public class DefaultBrouwerService implements BrouwerService {
    private final BrouwerRepository brouwerRepository;

    public DefaultBrouwerService(BrouwerRepository brouwerRepository) {
        this.brouwerRepository = brouwerRepository;
    }

    @Override
    public List<Brouwer> findAll() {
        return brouwerRepository.findAll();
    }

    @Override
    public Optional<Brouwer> findById(long id) {
        return brouwerRepository.findById(id);
    }
}
