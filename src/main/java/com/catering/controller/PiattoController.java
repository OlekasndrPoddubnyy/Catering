package com.catering.controller;

import com.catering.controller.validator.IngredienteValidator;
import com.catering.controller.validator.PiattoValidator;
import com.catering.model.Ingrediente;
import com.catering.model.Piatto;
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
public class PiattoController {

    @Autowired
    private PiattoService piattoService;

    @Autowired
    private PiattoValidator piattoValidator;

    @Autowired
    private BuffetService buffetService;

    @Autowired
    private IngredienteService ingredienteService;

    @Autowired
    private ChefService chefService;

    @Autowired
    private IngredienteValidator ingredienteValidator;

    @RequestMapping(value = "/admin/piatto", method = RequestMethod.GET)
    public String addPiatto(Model model) {
        model.addAttribute("piatto", new Piatto());
        return "form/piattoForm";
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
            model.addAttribute("buffets", this.buffetService.tutti());
            model.addAttribute("chefs", this.chefService.tutti());
            model.addAttribute("piatti", this.piattoService.tutti());
            model.addAttribute("ingredienti", this.ingredienteService.tutti());
            return "admin/home";
        }
        return "form/piattoForm";
    }

    @GetMapping("piatto/delete/{id}")
    public String deleteById(@PathVariable("id") Long id, Model model) {
        this.buffetService.deletePiattoforAll(id);
        this.piattoService.deletePiatto(id);
        model.addAttribute("buffets", this.buffetService.tutti());
        model.addAttribute("chefs", this.chefService.tutti());
        model.addAttribute("piatti", this.piattoService.tutti());
        model.addAttribute("ingredienti", this.ingredienteService.tutti());
        return "admin/home";
    }


    @GetMapping("piatto/update/{id}")
    public String updatePiatto(@PathVariable("id") Long id, Model model) {
        model.addAttribute("piatto", this.piattoService.piattoPerId(id));
        return "form/piattoFormUpdate";
    }

    @RequestMapping(value = "/admin/piatto/update", method = RequestMethod.POST)
    public String updatePiatto(@ModelAttribute("piatto") Piatto piatto,
                               Model model, BindingResult bindingResult) {
        this.piattoValidator.validate2(piatto, bindingResult);
        if (!bindingResult.hasErrors()) {
            this.piattoService.update(piatto);
            model.addAttribute("buffets", this.buffetService.tutti());
            model.addAttribute("chefs", this.chefService.tutti());
            model.addAttribute("piatti", this.piattoService.tutti());
            model.addAttribute("ingredienti", this.ingredienteService.tutti());
            return "admin/home";
        }
        model.addAttribute("piatti", piatto);
        return "form/piattoFormUpdate";
    }

    @GetMapping("piatto/mod/{id}")
    public String modIngredientiPiatto(@PathVariable("id") Long id, Model model) {
        model.addAttribute("ingredienti", this.ingredienteService.tutti());
        model.addAttribute("piatto", this.piattoService.piattoPerId(id));
        return "addIngrediente";
    }

    @GetMapping("piatto/addAttribute/{id}/{id2}")
    public String addIngredientiPiatto(@PathVariable("id") Long id,
                                       @PathVariable("id2") Long idIn,
                                       Model model) {
        this.piattoService.addIngredienteforPiatto(id, idIn);
        model.addAttribute("ingredienti", this.ingredienteService.tutti());
        model.addAttribute("piatto", this.piattoService.piattoPerId(id));

        return "addIngrediente";
    }

    @GetMapping("piatto/deleteAttribute/{id}/{id2}")
    public String deleteIngredientiPiatto(@PathVariable("id") Long id,
                                          @PathVariable("id2") Long idIn, Model model) {
        this.piattoService.deleteIngredienteforPiatto(id, idIn);
        model.addAttribute("ingredienti", this.ingredienteService.tutti());
        model.addAttribute("piatto", this.piattoService.piattoPerId(id));
        return "addIngrediente";
    }

    @RequestMapping(value = "/admin/ingredientePiatto/{id}", method = RequestMethod.GET)
    public String addIngrediente(@PathVariable("id") Long id, Model model) {
        model.addAttribute("piatto", this.piattoService.piattoPerId(id));
        model.addAttribute("ingrediente", new Ingrediente());
        return "form/ingredientePiattoForm";
    }
}
