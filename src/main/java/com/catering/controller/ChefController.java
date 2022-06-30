package com.catering.controller;

import com.catering.controller.validator.ChefValidator;
import com.catering.model.Chef;
import com.catering.service.ChefService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ChefController {

    @Autowired
    private ChefService chefService;

    @Autowired
    private ChefValidator chefValidator;

    @RequestMapping(value = "/admin/chef", method = RequestMethod.GET)
    public String addChef(Model model) {
        model.addAttribute("chef", new Chef());
        return "chefForm";
    }

    @RequestMapping(value = "/chef/{id}", method = RequestMethod.GET)
    public String getChef(@PathVariable("id") Long id, Model model) {
        model.addAttribute("chef", this.chefService.chefPerId(id));
        return "chef";
    }

    @RequestMapping(value = "/chefs", method = RequestMethod.GET)
    public String getChefs(Model model) {
        model.addAttribute("chefs", this.chefService.tutti());
        return "chefs";
    }

    @RequestMapping(value = "/admin/chef", method = RequestMethod.POST)
    public String addChef(@ModelAttribute("chef") Chef chef,
                            Model model, BindingResult bindingResult) {
        this.chefValidator.validate(chef, bindingResult);
        if (!bindingResult.hasErrors()) {
            this.chefService.inserisci(chef);
            model.addAttribute("chef", this.chefService.tutti());
            return "chefs";
        }
        return "chefForm";
    }

    @RequestMapping(value = "/admin/chefDelete", method = RequestMethod.POST)
    public String deleteChef(@ModelAttribute("chef") Chef chef,
                               Model model) {
        this.chefService.deleteChef(chef);
        model.addAttribute("chef", chef);
        return "confermaDeleteChef";
    }
}
