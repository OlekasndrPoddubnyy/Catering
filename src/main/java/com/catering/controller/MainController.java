package com.catering.controller;

import com.catering.model.Credentials;
import com.catering.service.BuffetService;
import com.catering.service.CredentialsService;
import com.catering.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class MainController {

    @Autowired
    private BuffetService buffetService;

    @Autowired
    private  CredentialsService credentialsService;

    @Autowired
    public UserService userService;


    @RequestMapping(value = {"/", "index"}, method = RequestMethod.GET)
    public String index(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            String currentUserName = authentication.getName();
            Credentials credentials = this.credentialsService.getCredentials(currentUserName);
            model.addAttribute("user", this.userService.getUser(credentials.getUser().getId()));
        }
        model.addAttribute("buffets",this.buffetService.tutti());
        return "index";
    }

    @GetMapping("/user/addBuffet/{idB}")
    public String addBuffet(@PathVariable("idB") Long idB, Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserName = authentication.getName();
        Credentials credentials = this.credentialsService.getCredentials(currentUserName);
        this.userService.addBookmark(credentials.getUser().getId(), idB);
        model.addAttribute("user", this.userService.getUser(credentials.getUser().getId()));
        model.addAttribute("buffets",this.buffetService.tutti());
        return "index";
    }

    @GetMapping("/userHome/deleteBuffet/{idB}")
    public String deleteBuffet(@PathVariable("idB") Long idB, Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserName = authentication.getName();
        Credentials credentials = this.credentialsService.getCredentials(currentUserName);
        this.userService.deleteBookmark(credentials.getUser().getId(), idB);
        model.addAttribute("user", this.userService.getUser(credentials.getUser().getId()));
        model.addAttribute("buffets",this.buffetService.tutti());
        return "index";
    }

    @GetMapping("/userProfile/deleteBuffet/{idB}")
    public String deleteProfileBuffet(@PathVariable("idB") Long idB, Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserName = authentication.getName();
        Credentials credentials = this.credentialsService.getCredentials(currentUserName);
        this.userService.deleteBookmark(credentials.getUser().getId(), idB);
        model.addAttribute("user", this.userService.getUser(credentials.getUser().getId()));
        model.addAttribute("buffets",this.buffetService.tutti());
        return "home";
    }

}
