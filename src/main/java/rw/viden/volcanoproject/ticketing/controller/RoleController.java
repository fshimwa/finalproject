package rw.viden.volcanoproject.ticketing.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import rw.viden.volcanoproject.ticketing.model.CurrentUser;
import rw.viden.volcanoproject.ticketing.model.Payment;
import rw.viden.volcanoproject.ticketing.model.Role;
import rw.viden.volcanoproject.ticketing.model.Users;
import rw.viden.volcanoproject.ticketing.service.RoleService;
import rw.viden.volcanoproject.ticketing.service.UserService;

import javax.validation.Valid;
import java.util.Date;

/**
 * Created by Viden ltd on 27/05/2016.
 */
@Controller
public class RoleController {
    @Autowired
    RoleService roleService;
    @Autowired
    UserService userService;

    /**
     * @param model
     * @return
     */
    @RequestMapping(value = "/role", method = RequestMethod.GET)
    public String getRolePage(Model model) {
        model.addAttribute("role", new Role());
        return "role";
    }

    /**
     * @param role
     * @param bindingResult
     * @param model
     * @return
     */
    @RequestMapping(value = "/role/save", method = RequestMethod.POST)
    public String saveRole(@Valid @ModelAttribute("role") Role role, Authentication authentication, BindingResult bindingResult, Model model) {
        CurrentUser currentUser = (CurrentUser) authentication.getPrincipal();
        Users users=userService.getByUsername(currentUser.getUsername()).get();
        role.setSaveBy(users);
        role.setSavedDate(new Date());
        roleService.saveOrUpdate(role);
        model.addAttribute("role", new Role());
        return "redirect:/role";
    }

    @RequestMapping(value = "/role/disable", method = RequestMethod.POST)
    public String disableRole(@Valid @ModelAttribute("role") Role role, BindingResult bindingResult, Model model){

        roleService.disable(role);
        model.addAttribute("role", new Role());
        return "redirect:/role";

    }
    @RequestMapping(value = "/role/list",method = RequestMethod.GET)
    public String getListPage(Model model){
        model.addAttribute("role",roleService.getAll());
        return "roleList";
    }
    @RequestMapping(value = "/role/edit/{id}", method = RequestMethod.GET)
    public String getEditPage(@PathVariable String id, Model model) {
        Integer idRole = Integer.parseInt(id);
        Role role = roleService.getById(idRole);
        model.addAttribute("role", role);
        return "roleEdit";
    }
}
