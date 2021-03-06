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
import rw.viden.volcanoproject.ticketing.model.Users;
import rw.viden.volcanoproject.ticketing.service.UserService;

import javax.validation.Valid;
import java.util.Date;

/**
 * Created by Viden ltd on 26/05/2016.
 */
@Controller
public class UsersController {

    @Autowired
    UserService userService;

    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value = "/users",method = RequestMethod.GET)
    public String getUserPage(Model model){
        model.addAttribute("user",new Users());

        return "users";
    }
    @RequestMapping(value = "/user/save",method = RequestMethod.POST)
    public String saveUser(@Valid @ModelAttribute("user") Users users, BindingResult bindingResult, Authentication authentication, Model model, RedirectAttributes redirectAttrs){
        users.setSavedDate(new Date());
        userService.saveOrUpdate(users);
        model.addAttribute("user",new Users());
        redirectAttrs.addFlashAttribute("messages", "success");
        return "redirect:/users";
    }
    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value = "/users/list",method = RequestMethod.GET)
    public String getListPage(Model model){
        model.addAttribute("users",userService.getAll());
        return "userList";
    }
    @RequestMapping(value = "/users/edit/{id}", method = RequestMethod.GET)
    public String getEditPage(@PathVariable String id, Model model) {
        Integer idUser = Integer.parseInt(id);
        Users users = userService.getById(idUser);
        model.addAttribute("user", users);

        return "userEdit";
    }

}
