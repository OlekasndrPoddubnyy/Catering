package com.catering.controller;

import com.catering.controller.validator.PiattoValidator;
import com.catering.model.Piatto;
import com.catering.service.PiattoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class PiattoController {

    @Autowired
    private PiattoService piattoService;

    @Autowired
    private PiattoValidator piattoValidator;

    @RequestMapping(value = "/admin/piatto", method = RequestMethod.GET)
    public String addPiatto(Model model) {
        model.addAttribute("piatto", new Piatto());
        return "piattoForm";
    }

    @RequestMapping(value = "/piatto/{id}", method = RequestMethod.GET)
    public String getPiatto(@PathVariable("id") Long id, Model model) {
        model.addAttribute("chef", this.piattoService.piattoPerId(id));
        return "piatto";
    }

    @RequestMapping(value = "/piatti", method = RequestMethod.GET)
    public String getPiatti(Model model) {
        model.addAttribute("piatti", this.piattoService.tutti());
        return "piatti";
    }

    @RequestMapping(value = "/admin/piatto", method = RequestMethod.POST)
    public String addPiatto(@ModelAttribute("piatto") Piatto piatto,
                          Model model, BindingResult bindingResult) {
        this.piattoValidator.validate(piatto, bindingResult);
        if (!bindingResult.hasErrors()) {
            this.piattoService.inserisci(piatto);
            model.addAttribute("piatto", this.piattoService.tutti());
            return "piatti";
        }
        return "piattoForm";
    }

    @RequestMapping(value = "/admin/piattoDelete", method = RequestMethod.POST)
    public String deletePiatto(@ModelAttribute("piatto") Piatto piatto,
                               Model model) {
        this.piattoService.deletePiatto(piatto);
        model.addAttribute("piatto", piatto);
        return "confermaDeletePiatto";
    }
}
