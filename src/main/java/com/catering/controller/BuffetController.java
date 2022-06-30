package com.catering.controller;

import com.catering.controller.validator.BuffetValidator;
import com.catering.model.Buffet;
import com.catering.model.Chef;
import com.catering.service.BuffetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class BuffetController {

    @Autowired
    private BuffetService buffetService;

    @Autowired
    private BuffetValidator buffetValidator;

    @RequestMapping(value = "/admin/buffet", method = RequestMethod.GET)
    public String addBuffet(Model model) {
        model.addAttribute("buffet", new Buffet());
        return "buffetForm";
    }

    @RequestMapping(value = "/buffet/{id}", method = RequestMethod.GET)
    public String getBuffet(@PathVariable("id") Long id, Model model) {
        model.addAttribute("buffet", this.buffetService.buffetPerId(id));
        return "buffet";
    }

    @RequestMapping(value = "/buffets", method = RequestMethod.GET)
    public String getBuffets(Model model) {
        model.addAttribute("buffets", this.buffetService.tutti());
        return "buffets";
    }

    @RequestMapping(value = "/admin/buffet", method = RequestMethod.POST)
    public String addBuffet(@ModelAttribute("buffet") Buffet buffet,
                            Model model, BindingResult bindingResult) {
        this.buffetValidator.validate(buffet, bindingResult);
        if (!bindingResult.hasErrors()) {
            this.buffetService.inserisci(buffet);
            model.addAttribute("buffets", this.buffetService.tutti());
            return "buffets";
        }
        return "buffetForm";
    }

    @RequestMapping(value = "/admin/buffetDelete", method = RequestMethod.POST)
    public String deleteBuffet(@ModelAttribute("buffet") Buffet buffet,
                             Model model) {
        this.buffetService.deleteBuffet(buffet);
        model.addAttribute("buffet", buffet);
        return "confermaDeleteBuffet";
    }
}
