package be.vdab.bierhuis.services;

import be.vdab.bierhuis.domain.Bier;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface BierService {
    long findAantal();
    List<Bier> findAll();
    Optional<Bier> findById(long id);
    List<Bier> findByBrouwerId(long brouwerid);
    void update(long id, BigDecimal aantal);
}
