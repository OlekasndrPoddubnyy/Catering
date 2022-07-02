package com.catering.controller;

import com.catering.controller.validator.BuffetValidator;
import com.catering.model.Buffet;
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
public class BuffetController {

    @Autowired
    private BuffetService buffetService;

    @Autowired
    private BuffetValidator buffetValidator;

    @Autowired
    private PiattoService piattoService;

    @Autowired
    private IngredienteService ingredienteService;

    @Autowired
    private ChefService chefService;

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
            model.addAttribute("chefs", this.chefService.tutti());
            model.addAttribute("piatti", this.piattoService.tutti());
            model.addAttribute("ingredienti", this.ingredienteService.tutti());
            return "admin/home";
        }
        return "buffetForm";
    }

    @GetMapping("buffet/delete/{id}")
    public String deleteById(@PathVariable("id") Long id, Model model) {
        this.buffetService.deleteBuffet(id);
        model.addAttribute("buffets", this.buffetService.tutti());
        model.addAttribute("chefs", this.chefService.tutti());
        model.addAttribute("piatti", this.piattoService.tutti());
        model.addAttribute("ingredienti", this.ingredienteService.tutti());
        return "admin/home";
    }


    @GetMapping("buffet/update/{id}")
    public String updateBuffet(@PathVariable("id") Long id, Model model) {
        model.addAttribute("buffet", this.buffetService.buffetPerId(id));
        return "buffetFormUpdate";
    }

    @RequestMapping(value = "/admin/buffet/update", method = RequestMethod.POST)
    public String updateBuffet(@ModelAttribute("buffet") Buffet buffet,
                             Model model, BindingResult bindingResult) {
        this.buffetValidator.validate2(buffet, bindingResult);
        if (!bindingResult.hasErrors()) {
            this.buffetService.inserisci(buffet);
            model.addAttribute("buffets", this.buffetService.tutti());
            model.addAttribute("chefs", this.chefService.tutti());
            model.addAttribute("piatti", this.piattoService.tutti());
            model.addAttribute("ingredienti", this.ingredienteService.tutti());
            return "admin/home";
        }
        model.addAttribute("buffet", buffet);
        return "buffetFormUpdate";
    }
}
