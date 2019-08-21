package be.vdab.bierhuis.controllers;

import be.vdab.bierhuis.form.AantalBakkenForm;
import be.vdab.bierhuis.services.BierService;
import be.vdab.bierhuis.sessions.Mandje;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.math.BigDecimal;

@Controller
@RequestMapping("bieren")
public class BierController {
    private final BierService bierService;
    private final Mandje mandje;

    public BierController(BierService bierService, Mandje mandje) {
        this.bierService = bierService;
        this.mandje = mandje;
    }

    @GetMapping("{id}")
    public ModelAndView bierPaginaForm(@PathVariable long id) {
        ModelAndView modelAndView = new ModelAndView("bieren");
        bierService.findById(id).ifPresent(bier -> {
            modelAndView.addObject(bier);
            modelAndView.addObject(new AantalBakkenForm(BigDecimal.ZERO));
        });
        return modelAndView;
    }

    @PostMapping("voegtoe")
    public ModelAndView voegtoe(long id, @Valid AantalBakkenForm form, Errors errors){
        if(errors.hasErrors()){
            ModelAndView modelAndView = new ModelAndView("bieren");
            bierService.findById(id).ifPresent(modelAndView::addObject);
            return modelAndView;
        }
        mandje.voegToe(id, form.getAantal());
        return new ModelAndView("redirect:/mandje");
    }

}
