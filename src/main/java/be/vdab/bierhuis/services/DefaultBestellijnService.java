package be.vdab.bierhuis.services;

import be.vdab.bierhuis.domain.Bestelbonlijn;
import be.vdab.bierhuis.repositories.BestellijnRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED)
public class DefaultBestellijnService implements BestellijnService {
    private final BestellijnRepository bestellijnRepository;

    public DefaultBestellijnService(BestellijnRepository bestellijnRepository) {
        this.bestellijnRepository = bestellijnRepository;
    }


    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED)
    @Override
    public void create(Bestelbonlijn bestelbonlijn) {
         bestellijnRepository.create(bestelbonlijn);
    }


}
