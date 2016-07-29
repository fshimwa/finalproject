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
import rw.viden.volcanoproject.ticketing.model.Journey;
import rw.viden.volcanoproject.ticketing.model.Ligne;
import rw.viden.volcanoproject.ticketing.model.Users;
import rw.viden.volcanoproject.ticketing.service.BusService;
import rw.viden.volcanoproject.ticketing.service.LigneService;
import rw.viden.volcanoproject.ticketing.service.UserService;

import javax.validation.Valid;
import java.util.Date;

/**
 * Created by Viden ltd on 27/05/2016.
 */
@Controller
public class LigneController {
    @Autowired
    LigneService ligneService;
    @Autowired
    UserService userService;
    @Autowired
    BusService busService;

    /**
     * @param model
     * @return
     */
    @RequestMapping(value = "/ligne", method = RequestMethod.GET)
    public String getLignePage(Model model) {
        model.addAttribute("ligne", new Ligne());
        model.addAttribute("buses",busService.getAll());
        return "ligne";
    }

    /**
     * @param ligne
     * @param bindingResult
     * @param model
     * @return
     */
    @RequestMapping(value = "/ligne/save", method = RequestMethod.POST)
    public String saveLigne(@Valid @ModelAttribute("ligne") Ligne ligne,BindingResult bindingResult, Authentication authentication , Model model) {
//       if(!bindingResult.hasErrors()){
           CurrentUser currentUser = (CurrentUser) authentication.getPrincipal();
           Users users = userService.getByUsername(currentUser.getUsername()).get();
           ligne.setSaveBy(users);
           ligne.setSavedDate(new Date());
           ligneService.saveOrUpdate(ligne);
           model.addAttribute("ligne", new Ligne());
           return "redirect:/ligne";
//       }else{
//           model.addAttribute("ligne",ligne);
//           return "ligne";
//       }
    }

    @RequestMapping(value = "/ligne/disable", method = RequestMethod.POST)
    public String disableLigne(@Valid @ModelAttribute("ligne") Ligne ligne, BindingResult bindingResult, Model model){

        ligneService.disable(ligne);
        model.addAttribute("ligne", new Ligne());
        return "redirect:/ligne";

    }
    @RequestMapping(value = "/ligne/list",method = RequestMethod.GET)
    public String getListPage(Model model){
        model.addAttribute("ligne",ligneService.getAll());
        return "ligneList";
    }

    @RequestMapping(value = "/ligne/edit/{id}", method = RequestMethod.GET)
    public String getEditPage(@PathVariable String id, Model model) {
        Integer idLigne = Integer.parseInt(id);
        Ligne ligne = ligneService.getById(idLigne);
        model.addAttribute("ligne", ligne);
        model.addAttribute("buses",busService.getAll());
        return "ligneEdit";
    }
    @RequestMapping(value = "/ligne/addJourney/{id}", method = RequestMethod.GET)
    public String getJourneyPage(@PathVariable String id, Model model) {
        Integer idLigne = Integer.parseInt(id);
        Ligne ligne = ligneService.getById(idLigne);
        model.addAttribute("line", ligne);
        model.addAttribute("buses",ligne.getAssignedBus());
        Journey journey=new Journey();
        journey.setLigne(ligne);
        model.addAttribute("journey",journey);
        return "journeyAdd";
    }
}
