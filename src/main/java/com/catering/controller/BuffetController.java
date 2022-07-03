package com.catering.controller;

import com.catering.controller.validator.BuffetValidator;
import com.catering.model.Buffet;
import com.catering.model.Chef;
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

import java.util.List;

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
        return "form/buffetForm";
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
        return "form/buffetForm";
    }

    @GetMapping("buffet/delete/{id}")
    public String deleteById(@PathVariable("id") Long id, Model model) {
        Buffet buffet = this.buffetService.buffetPerId(id);
        List<Chef> chefs = this.chefService.findAllChefContains(buffet);
        if(!chefs.isEmpty()) {
            for (Chef chef : chefs) {
                chef.deleteBuffet(buffet);
            }
        }
        buffet.setChef(null);
        this.buffetService.inserisci(buffet);
        this.chefService.inserisciTutti(chefs);
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
        return "form/buffetFormUpdate";
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
        return "form/buffetFormUpdate";
    }

    @GetMapping("buffet/modPiatto/{id}")
    public String modPiattiBuffet(@PathVariable("id") Long id, Model model) {
        model.addAttribute("piatti", this.piattoService.tutti());
        model.addAttribute("buffet", this.buffetService.buffetPerId(id));
        return "addPiatto";
    }

    @GetMapping("buffet/addPiatto/{id}/{id2}")
    public String addPiattoBuffet(@PathVariable("id") Long id,
                                       @PathVariable("id2") Long idPt,
                                       Model model) {
        Buffet buffet = this.buffetService.buffetPerId(id);
        Piatto piatto = this.piattoService.piattoPerId(idPt);
        buffet.addPiatto(piatto);
        this.buffetService.inserisci(buffet);
        model.addAttribute("piatti", this.piattoService.tutti());
        model.addAttribute("buffet", this.buffetService.buffetPerId(id));
        return "addPiatto";
    }

    @GetMapping("buffet/deletePiatto/{id}/{id2}")
    public String deletePiattoBuffet(@PathVariable("id") Long id,
                                          @PathVariable("id2") Long idPt, Model model){
        Buffet buffet = this.buffetService.buffetPerId(id);
        Piatto piatto = this.piattoService.piattoPerId(idPt);

        buffet.deletePiatto(piatto);
        this.buffetService.inserisci(buffet);
        model.addAttribute("piatti", this.piattoService.tutti());
        model.addAttribute("buffet", this.buffetService.buffetPerId(id));
        return "addPiatto";
    }

    @GetMapping("buffet/modChef/{id}")
    public String modChefBuffet(@PathVariable("id") Long id, Model model) {
        model.addAttribute("chefs", this.chefService.tutti());
        model.addAttribute("buffet", this.buffetService.buffetPerId(id));
        return "addChef";
    }

    @GetMapping("buffet/addChef/{id}/{id2}")
    public String addChefBuffet(@PathVariable("id") Long id,
                                  @PathVariable("id2") Long idCf,
                                  Model model) {
        Buffet buffet = this.buffetService.buffetPerId(id);
        List<Chef> chefs = this.chefService.findAllChefContains(buffet);
        if(!chefs.isEmpty()) {
            for (Chef chef : chefs) {
                chef.deleteBuffet(buffet);
            }
        }
        Chef chef = this.chefService.chefPerId(idCf);
        chef.addBuffet(buffet);
        chefs.add(chef);
        buffet.setChef(chef);
        this.chefService.inserisciTutti(chefs);
        this.buffetService.inserisci(buffet);
        model.addAttribute("chefs", this.chefService.tutti());
        model.addAttribute("buffet", this.buffetService.buffetPerId(id));
        return "addChef";
    }

    @GetMapping("buffet/deleteChef/{id}")
    public String deleteChefBuffet(@PathVariable("id") Long id, Model model) {
        Buffet buffet = this.buffetService.buffetPerId(id);
        List<Chef> chefs = this.chefService.findAllChefContains(buffet);
        if(!chefs.isEmpty()) {
            for (Chef chef : chefs) {
                chef.deleteBuffet(buffet);
            }
        }
        buffet.setChef(null);
        this.chefService.inserisciTutti(chefs);
        this.buffetService.inserisci(buffet);
        model.addAttribute("chefs", this.chefService.tutti());
        model.addAttribute("buffet", this.buffetService.buffetPerId(id));
        return "addChef";
    }


}
