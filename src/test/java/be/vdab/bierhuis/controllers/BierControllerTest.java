package be.vdab.bierhuis.controllers;

import be.vdab.bierhuis.domain.Bier;
import be.vdab.bierhuis.services.BierService;
import be.vdab.bierhuis.sessions.Mandje;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class BierControllerTest {
    private BierController controller;

    @Mock
    private BierService bierService;
    @Mock
    private Mandje mandje;
    @Before
    public void before(){
        when(bierService.findById(1))
                .thenReturn(Optional.of(new Bier(1, "Test", 1, 1, BigDecimal.ZERO, BigDecimal.ZERO, 0)));
        controller = new BierController(bierService, mandje);
    }

    @Test
    public void bierGebruiktDeThymeLeafPaginabieren(){
        assertThat(controller.bierPaginaForm(1).getViewName()).isEqualTo("bieren");
    }

    @Test
    public void bierGeeftGevondenBierDoorAanDeThymeleafPagina(){
        Bier bier = (Bier) controller.bierPaginaForm(1).getModel().get("bier");
        assertThat(bier.getId()).isEqualTo(1);
    }
    @Test
    public void bierGeeftOnbestaandeBierNietDoorAanDeThymeleafPagina() {
        assertThat(controller.bierPaginaForm(-1).getModel()).doesNotContainKeys("bier");
    }
}
