package rw.viden.volcanoproject.ticketing.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import rw.viden.volcanoproject.ticketing.model.Bus;
import rw.viden.volcanoproject.ticketing.model.CurrentUser;
import rw.viden.volcanoproject.ticketing.model.Customer;
import rw.viden.volcanoproject.ticketing.model.Users;
import rw.viden.volcanoproject.ticketing.service.BusService;
import rw.viden.volcanoproject.ticketing.service.UserService;


import javax.validation.Valid;
import java.util.Date;

/**
 * Created by Viden ltd on 26/05/2016.
 */


@Controller
public class BusController {

    @Autowired
    BusService busService;
    @Autowired
    UserService userService;


    @PreAuthorize("hasAnyAuthority('MANAGER','ADMIN')")
    @RequestMapping(value = "/bus", method = RequestMethod.GET)
    public String getBusPage(Model model) {
        model.addAttribute("bus", new Bus());
        return "busPage";
    }
    @PreAuthorize("hasAnyAuthority('MANAGER','ADMIN')")
    @RequestMapping(value = "/bus/save", method = RequestMethod.POST)
    public String saveBus(@Valid @ModelAttribute("bus") Bus bus, Authentication authentication, BindingResult bindingResult, Model model,RedirectAttributes redirectAttrs) {
        CurrentUser currentUser = (CurrentUser) authentication.getPrincipal();
        Users users = userService.getByUsername(currentUser.getUsername()).get();
        if (busService.isPlaqueUsed(bus.getPlaque())) {
            model.addAttribute("bus", bus);
            model.addAttribute("mss", "Plate already used");
            redirectAttrs.addFlashAttribute("messages", "unsuccess");
            return "busPage";
        }
        bus.setSaveBy(users);
        bus.setSavedDate(new Date());
        busService.saveOrUpdate(bus);
        model.addAttribute("bus", new Bus());
        model.addAttribute("messages", "success");
        return "redirect:/bus";
    }

    @RequestMapping(value = "/bus/disable", method = RequestMethod.POST)
    public String disableBus(@Valid @ModelAttribute("bus") Bus bus, BindingResult bindingResult, Model model) {

        busService.disable(bus);
        model.addAttribute("bus", new Bus());
        return "redirect:/bus";
    }
    @PreAuthorize("hasAnyAuthority('MANAGER','ADMIN')")
    @RequestMapping(value = "/bus/list", method = RequestMethod.GET)
    public String getListPage(Model model) {
        model.addAttribute("bus", busService.getAll());
        return "busList";
    }
    @PreAuthorize("hasAnyAuthority('MANAGER','ADMIN')")
    @RequestMapping(value = "/bus/edit/{id}", method = RequestMethod.GET)
    public String getEditPage(@PathVariable String id, Model model) {
        Integer idBus = Integer.parseInt(id);
        Bus bus = busService.getById(idBus);
        model.addAttribute("bus", bus);
        return "busEdit";
    }

}


