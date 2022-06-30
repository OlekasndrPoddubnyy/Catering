package com.catering.controller;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import com.catering.controller.validator.ProdottoValidator;
import com.catering.model.Prodotto;
import com.catering.service.ProdottoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ProdottoController {

    @Autowired
    private ProdottoService prodottoService;

    @Autowired
    private ProdottoValidator prodottoValidator;

    @RequestMapping(value="/admin/prodotto", method = RequestMethod.GET)
    public String addProdotto(Model model) {
        model.addAttribute("prodotto", new Prodotto());
        return "prodottoForm";
    }

    @RequestMapping(value = "/prodotto/{id}", method = RequestMethod.GET)
    public String getPersona(@PathVariable("id") Long id, Model model) {
        model.addAttribute("prodotto", this.prodottoService.prodottoPerId(id));
        return "prodotto";
    }

    @RequestMapping(value = "/prodotto", method = RequestMethod.GET)
    public String getProdotti(Model model) {
        model.addAttribute("prodotti", this.prodottoService.tutti());
        return "prodotti";
    }

    @RequestMapping(value = "/admin/prodotto", method = RequestMethod.POST)
    public String addProdotto(@ModelAttribute("prodotto") Prodotto prodotto,
                              Model model, BindingResult bindingResult) {
        this.prodottoValidator.validate(prodotto, bindingResult);
        if (!bindingResult.hasErrors()) {
            this.prodottoService.inserisci(prodotto);
            model.addAttribute("prodotti", this.prodottoService.tutti());
            return "prodotti";
        }
        return "prodottoForm";
    }
}
