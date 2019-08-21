package be.vdab.bierhuis.controllers;

import be.vdab.bierhuis.services.BestelBonService;
import be.vdab.bierhuis.services.BestellijnService;
import be.vdab.bierhuis.services.BierService;
import be.vdab.bierhuis.sessions.Mandje;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class MandjeControllerTest {
    private MandjeController controller;

    @Mock
    Mandje mandje;
    @Mock
    BierService bierService;
    @Mock
    BestellijnService bestellijnService;
    @Mock
    BestelBonService bestelBonService;

    @Before
    public void before(){
        controller = new MandjeController(mandje, bierService, bestelBonService, bestellijnService);
    }

    @Test
    public void mandjeGebruiktDeThymeLeafPaginaMandje(){
        assertThat(controller.toonMandje().getViewName()).isEqualTo("mandje");
    }
}
