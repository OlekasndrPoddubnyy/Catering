package com.catering.controller;

import com.catering.controller.validator.IngredienteValidator;
import com.catering.model.Ingrediente;
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
public class IngredienteController {

    @Autowired
    private IngredienteService ingredienteService;

    @Autowired
    private IngredienteValidator ingredienteValidator;

    @Autowired
    private PiattoService piattoService;

    @Autowired
    private ChefService chefService;

    @Autowired
    private BuffetService buffetService;

    @RequestMapping(value = "/admin/ingrediente", method = RequestMethod.GET)
    public String addIngrediente(Model model) {
        model.addAttribute("ingrediente", new Ingrediente());
        return "form/ingredienteForm";
    }

    @RequestMapping(value = "/ingrediente/{id}", method = RequestMethod.GET)
    public String getIngrediente(@PathVariable("id") Long id, Model model) {
        model.addAttribute("chef", this.ingredienteService.ingredientePerId(id));
        return "ingrediente";
    }

    @RequestMapping(value = "/ingredients", method = RequestMethod.GET)
    public String getIngredienti(Model model) {
        model.addAttribute("ingredients", this.ingredienteService.tutti());
        return "ingredients";
    }

    @RequestMapping(value = "/admin/ingrediente", method = RequestMethod.POST)
    public String addIngrediente(@ModelAttribute("ingrediente") Ingrediente ingrediente,
                            Model model, BindingResult bindingResult) {
        this.ingredienteValidator.validate(ingrediente, bindingResult);
        if (!bindingResult.hasErrors()) {
            this.ingredienteService.inserisci(ingrediente);
            model.addAttribute("buffets", this.buffetService.tutti());
            model.addAttribute("chefs", this.chefService.tutti());
            model.addAttribute("piatti", this.piattoService.tutti());
            model.addAttribute("ingredienti", this.ingredienteService.tutti());
            return "/admin/home";
        }
        return "form/ingredienteForm";
    }

    @GetMapping("ingrediente/delete/{id}")
    public String deleteById(@PathVariable("id") Long id, Model model) {
        this.piattoService.deleteIngredienteforAll(id);
        this.ingredienteService.deleteIngrediente(id);
        model.addAttribute("buffets", this.buffetService.tutti());
        model.addAttribute("chefs", this.chefService.tutti());
        model.addAttribute("piatti", this.piattoService.tutti());
        model.addAttribute("ingredienti", this.ingredienteService.tutti());
        return "admin/home";
    }


    @GetMapping("ingrediente/update/{id}")
    public String updateChef(@PathVariable("id") Long id, Model model) {
        model.addAttribute("ingrediente", this.ingredienteService.ingredientePerId(id));
        return "form/ingredienteFormUpdate";
    }

    @RequestMapping(value = "/admin/ingrediente/update", method = RequestMethod.POST)
    public String updateIngrediente(@ModelAttribute("ingrediente") Ingrediente ingrediente,
                             Model model, BindingResult bindingResult) {
        this.ingredienteValidator.validate2(ingrediente, bindingResult);
        if (!bindingResult.hasErrors()) {
            this.ingredienteService.inserisci(ingrediente);
            model.addAttribute("buffets", this.buffetService.tutti());
            model.addAttribute("chefs", this.chefService.tutti());
            model.addAttribute("piatti", this.piattoService.tutti());
            model.addAttribute("ingredienti", this.ingredienteService.tutti());
            return "admin/home";
        }
        model.addAttribute("ingrediente", ingrediente);
        return "form/ingredienteFormUpdate";
    }
}
