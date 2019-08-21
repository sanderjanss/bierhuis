package be.vdab.bierhuis.controllers;

import be.vdab.bierhuis.domain.Bestelbon;
import be.vdab.bierhuis.domain.Bestelbonlijn;
import be.vdab.bierhuis.domain.Bier;
import be.vdab.bierhuis.services.BestelBonService;
import be.vdab.bierhuis.services.BestellijnService;
import be.vdab.bierhuis.services.BierService;
import be.vdab.bierhuis.sessions.Mandje;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/mandje")
public class MandjeController {
    private final Mandje mandje;
    private final BierService bierService;
    private final BestelBonService bestelBonService;
    private final BestellijnService bestellijnService;

    public MandjeController(Mandje mandje, BierService bierService, BestelBonService bestelBonService, BestellijnService bestellijnService) {
        this.mandje = mandje;
        this.bierService = bierService;
        this.bestelBonService = bestelBonService;
        this.bestellijnService = bestellijnService;
    }

    public Map<Bier, BigDecimal> inWinkelMand() {
        List<Bier> alleBieren = bierService.findAll();
        Map<Bier, BigDecimal> winkelmand = new LinkedHashMap<>();
        alleBieren.forEach(bier -> {
            if (mandje.getArtikelsEnAantal().containsKey(bier.getId())) {
                winkelmand.put(bier, mandje.getArtikelsEnAantal().get(bier.getId()));
            }

        });
        return winkelmand;
    }


    @GetMapping
    public ModelAndView toonMandje() {
        ModelAndView modelAndView = new ModelAndView("mandje");

        modelAndView.addObject("winkelmand", inWinkelMand());
        modelAndView.addObject(new Bestelbon(0L, null, null, null, 0, null));

        return modelAndView;

    }


    @PostMapping("/toevoegen")
    public ModelAndView toevoegen(@Valid Bestelbon form, Errors errors, RedirectAttributes redirect) {
        if (errors.hasErrors()) {
            return new ModelAndView("mandje").addObject("winkelmand", inWinkelMand());
        }

            long id = bestelBonService.create(form);

            inWinkelMand().forEach(((bier, bigDecimal) -> bierService.update(bier.getId(), bigDecimal)));

            inWinkelMand().forEach((bier, bigDecimal) -> bestellijnService
                    .create(new Bestelbonlijn(id, bier.getId(), bigDecimal, bier.getPrijs())));

            mandje.mandjeLeegMaken();

            redirect.addAttribute("bestelbon", id);
            return new ModelAndView("redirect:/mandje/bevestiging");

    }

    @GetMapping("/bevestiging")
    public ModelAndView bevestigingPagina() {
        ModelAndView modelAndView = new ModelAndView("bevestiging");
        return modelAndView;
    }
}
