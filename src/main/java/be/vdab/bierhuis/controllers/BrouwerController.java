package be.vdab.bierhuis.controllers;

import be.vdab.bierhuis.services.BierService;
import be.vdab.bierhuis.services.BrouwerService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/brouwers")
public class BrouwerController {
    private final BrouwerService brouwerService;
    private final BierService bierService;

    public BrouwerController(BrouwerService brouwerService, BierService bierService) {
        this.brouwerService = brouwerService;
        this.bierService = bierService;
    }

    @GetMapping
    public ModelAndView toonBrouwers(){
        return new ModelAndView("brouwers", "brouwers", brouwerService.findAll());

    }

    @GetMapping("{id}")
    public ModelAndView toonBrouwerPagina(@PathVariable long id){
        ModelAndView modelAndView = new ModelAndView("brouwer");
        brouwerService.findById(id).ifPresent(brouwer -> {
            modelAndView.addObject(brouwer);
            modelAndView.addObject("bierenPerBrouwer", bierService.findByBrouwerId(id));
        });
        return modelAndView;
    }
}
