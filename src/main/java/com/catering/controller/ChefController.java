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
        return "form/chefForm";
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
        return "form/chefForm";
    }

    @GetMapping("chef/delete/{id}")
    public String deleteById(@PathVariable("id") Long id, Model model) {
        this.buffetService.delChefBuffet(id);
        this.buffetService.delAllChefBuffet(id);
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
        return "form/chefFormUpdate";
    }

    @RequestMapping(value = "/admin/chef/update", method = RequestMethod.POST)
    public String updateChef(@ModelAttribute("chef") Chef chef,
                          Model model, BindingResult bindingResult) {
        this.chefValidator.validate2(chef, bindingResult);
        if (!bindingResult.hasErrors()) {
            this.chefService.update(chef);
            model.addAttribute("buffets", this.buffetService.tutti());
            model.addAttribute("chefs", this.chefService.tutti());
            model.addAttribute("piatti", this.piattoService.tutti());
            model.addAttribute("ingredienti", this.ingredienteService.tutti());
            return "admin/home";
        }
        model.addAttribute("chef", chef);
        return "form/chefFormUpdate";
    }

    @GetMapping("chef/mod/{id}")
    public String modBuffetChef(@PathVariable("id") Long id, Model model) {
        model.addAttribute("buffets", this.buffetService.tutti());
        model.addAttribute("chef", this.chefService.chefPerId(id));
        return "addBuffet";
    }

    @GetMapping("chef/addBuffet/{id}/{id2}")
    public String addBuffetChef(@PathVariable("id") Long id,
                                       @PathVariable("id2") Long idBf,Model model) {
        this.buffetService.delChefBuffet(idBf);
        this.chefService.deleteBuffetforAll(idBf);
        this.chefService.addBuffetforChef(id,idBf);
        this.buffetService.setChefBuffet(id,idBf);
        model.addAttribute("buffets", this.buffetService.tutti());
        model.addAttribute("chef", this.chefService.chefPerId(id));
        return "addBuffet";
    }

    @GetMapping("chef/deleteBuffets/{id}/{id2}")
    public String deleteBuffetChef(@PathVariable("id") Long id,
                                          @PathVariable("id2") Long idBf, Model model){
        this.buffetService.delChefBuffet(idBf);
        this.chefService.deleteBuffetforChef2(id,idBf);
        model.addAttribute("buffets", this.buffetService.tutti());
        model.addAttribute("chef", this.chefService.chefPerId(id));
        return "addBuffet";
    }
}
