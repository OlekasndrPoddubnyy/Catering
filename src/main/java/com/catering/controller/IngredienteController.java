package com.catering.controller;

import com.catering.controller.validator.IngredienteValidator;
import com.catering.model.Chef;
import com.catering.model.Ingrediente;
import com.catering.service.IngredienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class IngredienteController {

    @Autowired
    private IngredienteService ingredienteService;

    @Autowired
    private IngredienteValidator ingredienteValidator;

    @RequestMapping(value = "/admin/ingrediente", method = RequestMethod.GET)
    public String addIngrediente(Model model) {
        model.addAttribute("ingrediente", new Ingrediente());
        return "ingredienteForm";
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
    public String addIngrediente(@ModelAttribute("ingrediente") Ingrediente chef,
                            Model model, BindingResult bindingResult) {
        this.ingredienteValidator.validate(chef, bindingResult);
        if (!bindingResult.hasErrors()) {
            this.ingredienteService.inserisci(chef);
            model.addAttribute("ingrediente", this.ingredienteService.tutti());
            return "ingredients";
        }
        return "ingredienteForm";
    }

    @RequestMapping(value = "/admin/ingredienteDelete", method = RequestMethod.POST)
    public String deleteIngrediente(@ModelAttribute("ingrediente") Ingrediente ingrediente,
                               Model model) {
        this.ingredienteService.deleteIngrediente(ingrediente);
        model.addAttribute("ingrediente", ingrediente);
        return "confermaDeleteIngrediente";
    }
}
