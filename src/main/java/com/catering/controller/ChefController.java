package com.catering.controller;

import com.catering.controller.validator.ChefValidator;
import com.catering.model.Chef;
import com.catering.service.BuffetService;
import com.catering.service.ChefService;
import com.catering.service.IngredienteService;
import com.catering.service.PiattoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class ChefController {

    @Autowired
    private ChefService chefService;

    @Autowired
    private ChefValidator chefValidator;

    @Autowired
    private BuffetService buffetService;

    @Autowired
    private PiattoService piattoService;

    @Autowired
    private IngredienteService ingredienteService;

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
            model.addAttribute("buffets", this.buffetService.tutti());
            model.addAttribute("chefs", this.chefService.tutti());
            model.addAttribute("piatti", this.piattoService.tutti());
            model.addAttribute("ingredienti", this.ingredienteService.tutti());
            return "admin/home";
        }
        return "chefForm";
    }

    @GetMapping("chef/delete/{id}")
    public String deleteById(@PathVariable("id") Long id, Model model) {
        this.chefService.deleteChef(id);
        model.addAttribute("buffets", this.buffetService.tutti());
        model.addAttribute("chefs", this.chefService.tutti());
        model.addAttribute("piatti", this.piattoService.tutti());
        model.addAttribute("ingredienti", this.ingredienteService.tutti());
        return "admin/home";
    }


    @GetMapping("chef/update/{id}")
    public String updateChef(@PathVariable("id") Long id, Model model) {
        model.addAttribute("chef", this.chefService.chefPerId(id));
        return "chefFormUpdate";
    }

    @RequestMapping(value = "/admin/chef/update", method = RequestMethod.POST)
    public String updateChef(@ModelAttribute("chef") Chef chef,
                          Model model, BindingResult bindingResult) {
        this.chefValidator.validate2(chef, bindingResult);
        if (!bindingResult.hasErrors()) {
            this.chefService.inserisci(chef);
            model.addAttribute("buffets", this.buffetService.tutti());
            model.addAttribute("chefs", this.chefService.tutti());
            model.addAttribute("piatti", this.piattoService.tutti());
            model.addAttribute("ingredienti", this.ingredienteService.tutti());
            return "admin/home";
        }
        model.addAttribute("chef", chef);
        return "chefFormUpdate";
    }
}
