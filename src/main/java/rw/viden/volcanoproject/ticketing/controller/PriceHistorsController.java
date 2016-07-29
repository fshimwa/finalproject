package rw.viden.volcanoproject.ticketing.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import rw.viden.volcanoproject.ticketing.model.*;
import rw.viden.volcanoproject.ticketing.service.PriceHistorsService;
import rw.viden.volcanoproject.ticketing.service.UserService;

import javax.validation.Valid;

/**
 * Created by Viden ltd on 7/5/2016.
 */
public class PriceHistorsController {

    @Autowired
    PriceHistorsService priceHistorsService;
    @Autowired
    UserService userService;

    @RequestMapping(value = "/priceHistors",method = RequestMethod.GET)
    public String getPriceHistorsPage(Model model){
        model.addAttribute("priceHistors",new Price());
        return "priceHistors";
    }
    @RequestMapping(value = "/priceHistors/save",method = RequestMethod.POST)
    public String savePriceHistors(@Valid @ModelAttribute("priceHistors") Price priceHistors, Authentication authentication, BindingResult bindingResult, Model model) {
        CurrentUser currentUser = (CurrentUser) authentication.getPrincipal();
        Users users=userService.getByUsername(currentUser.getUsername()).get();
        priceHistorsService.saveOrUpdate(priceHistors);
        model.addAttribute("priceHistors", new Price());
        return "redirect:/priceHistors";
    }

    @RequestMapping(value = "/priceHistors/disable",method = RequestMethod.POST)
    public String disablePriceHistors(@Valid @ModelAttribute("priceHistors") Price priceHistors, BindingResult bindingResult, Model model) {

        priceHistorsService.disable(priceHistors);
        model.addAttribute("priceHistors",new Price());
        return "redirect:/priceHistors";
    }
    @RequestMapping(value = "/priceHistors/list",method = RequestMethod.GET)
    public String getListPage(Model model){
        model.addAttribute("priceHistors",priceHistorsService.getAll());
        return "priceHistorsList";
    }
    @RequestMapping(value = "/priceHistors/edit/{id}", method = RequestMethod.GET)
    public String getEditPage(@PathVariable String id, Model model) {
        Integer idPriceHistors = Integer.parseInt(id);
       Price priceHistors = priceHistorsService.getById(idPriceHistors);
        model.addAttribute("PriceHistors", priceHistors);
        return "priceHistorsEdit";
    }

}
