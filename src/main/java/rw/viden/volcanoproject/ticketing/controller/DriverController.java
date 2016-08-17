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
import rw.viden.volcanoproject.ticketing.model.CurrentUser;
import rw.viden.volcanoproject.ticketing.model.Customer;
import rw.viden.volcanoproject.ticketing.model.Driver;
import rw.viden.volcanoproject.ticketing.model.Users;
import rw.viden.volcanoproject.ticketing.service.DriverService;
import rw.viden.volcanoproject.ticketing.service.UserService;

import javax.validation.Valid;
import java.util.Date;

/**
 * Created by Viden ltd on 26/05/2016.
 */
@Controller
public class DriverController {
    @Autowired
    DriverService driverService;
    @Autowired
    UserService userService;


    @PreAuthorize("hasAuthority('MANAGER')")
    @RequestMapping(value = "/driver",method = RequestMethod.GET)
    public String getDriverPage(Model model){
        model.addAttribute("driver",new Driver());
        return "driver";
    }
    @PreAuthorize("hasAuthority('MANAGER')")
    @RequestMapping(value = "/driver/save",method = RequestMethod.POST)
    public String saveDriver(@Valid @ModelAttribute("driver") Driver driver, Authentication authentication, BindingResult bindingResult, Model model) {
        CurrentUser currentUser = (CurrentUser) authentication.getPrincipal();
        Users users=userService.getByUsername(currentUser.getUsername()).get();
        driver.setSaveBy(users);
        driver.setSavedDate(new Date());
        driverService.saveOrUpdate(driver);
        model.addAttribute("driver", new Driver());
        return "redirect:/driver";
    }

        @RequestMapping(value = "/driver/disable",method = RequestMethod.POST)
        public String disableDriver(@Valid @ModelAttribute("driver") Driver driver, BindingResult bindingResult, Model model){

        driverService.disable(driver);
        model.addAttribute("driver",new Driver());
        return "redirect:/driver";
    }
    @PreAuthorize("hasAuthority('MANAGER')")
    @RequestMapping(value = "/driver/list",method = RequestMethod.GET)
    public String getListPage(Model model){
        model.addAttribute("driver",driverService.getAll());
        return "driverList";
    }
    @PreAuthorize("hasAuthority('MANAGER')")
    @RequestMapping(value = "/driver/edit/{id}", method = RequestMethod.GET)
    public String getEditPage(@PathVariable String id, Model model) {
        Integer idDriver = Integer.parseInt(id);
        Driver driver = driverService.getById(idDriver);
        model.addAttribute("driver", driver);
        return "driverEdit";
    }

}
