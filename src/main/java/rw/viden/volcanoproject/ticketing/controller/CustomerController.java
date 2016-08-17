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
import rw.viden.volcanoproject.ticketing.model.CurrentUser;
import rw.viden.volcanoproject.ticketing.model.Customer;
import rw.viden.volcanoproject.ticketing.model.Reservation;
import rw.viden.volcanoproject.ticketing.model.Users;
import rw.viden.volcanoproject.ticketing.service.CustomerService;
import rw.viden.volcanoproject.ticketing.service.UserService;

import javax.validation.Valid;
import java.util.Date;

/**
 * Created by Viden ltd on 26/05/2016.
 */
@Controller
public class CustomerController {
    @Autowired
    CustomerService customerService;
    @Autowired
    UserService userService;


    @PreAuthorize("hasAuthority('EMPLOYEE')")
    @RequestMapping(value = "/customer",method = RequestMethod.GET)
    public String getCustomerPage(Model model){
        model.addAttribute("customer",new Customer());
        return "customer";
    }
    @PreAuthorize("hasAuthority('EMPLOYEE')")
    @RequestMapping(value = "/customer/save",method = RequestMethod.POST)
    public String saveCustomer(@Valid @ModelAttribute("customer") Customer customer, Authentication authentication, BindingResult bindingResult, Model model, RedirectAttributes redirectAttrs) {
        CurrentUser currentUser = (CurrentUser) authentication.getPrincipal();
        Users users=userService.getByUsername(currentUser.getUsername()).get();
        customer.setSaveBy(users);
        customer.setSavedDate(new Date());
        customerService.saveOrUpdate(customer);
        model.addAttribute("customer", new Customer());
        Integer idCustomer = customer.getId();
        redirectAttrs.addFlashAttribute("messages", "success");
        return "redirect:/customer/reserve/"+idCustomer;
    }

    @RequestMapping(value = "/customer/disable",method = RequestMethod.POST)
    public String disableCustomer(@Valid @ModelAttribute("customer") Customer customer, BindingResult bindingResult, Model model) {

        customerService.disable(customer);
        model.addAttribute("customer",new Customer());
        model.addAttribute("messages", "unsuccess");
        return "redirect:/customer";
    }
    @PreAuthorize("hasAuthority('EMPLOYEE')")
    @RequestMapping(value = "/customer/list",method = RequestMethod.GET)
    public String getListPage(Model model){
        model.addAttribute("customer",customerService.getAll());
        return "customerList";
    }
    @PreAuthorize("hasAuthority('EMPLOYEE')")
    @RequestMapping(value = "/customer/edit/{id}", method = RequestMethod.GET)
    public String getEditPage(@PathVariable String id, Model model) {
        Integer idCustomer = Integer.parseInt(id);
        Customer customer = customerService.getById(idCustomer);
        model.addAttribute("customer", customer);
        return "customerEdit";
    }

}
