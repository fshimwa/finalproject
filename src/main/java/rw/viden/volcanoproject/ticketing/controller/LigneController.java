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
import rw.viden.volcanoproject.ticketing.model.*;
import rw.viden.volcanoproject.ticketing.service.BusService;
import rw.viden.volcanoproject.ticketing.service.LigneService;
import rw.viden.volcanoproject.ticketing.service.UserService;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
    @PreAuthorize("hasAuthority('MANAGER')")
    @RequestMapping(value = "/ligne", method = RequestMethod.GET)
    public String getLignePage(Model model) {
        model.addAttribute("ligne", new Ligne());
        model.addAttribute("buses",getUnassignedBus(busService.getAll()));
        return "ligne";
    }
    private List<Bus> getUnassignedBus(List<Bus> buses){
        List<Bus> busList=new ArrayList<>();
        for(Bus bus:buses){

            if(!ligneService.isBusUsed(bus)){
                busList.add(bus);
            }

        }
        return busList;
    }

    /**
     * @param ligne
     * @param bindingResult
     * @param model
     * @return
     */
    @PreAuthorize("hasAuthority('MANAGER')")
    @RequestMapping(value = "/ligne/save", method = RequestMethod.POST)
    public String saveLigne(@Valid @ModelAttribute("ligne") Ligne ligne,BindingResult bindingResult, Authentication authentication , Model model, RedirectAttributes redirectAttrs) {
       if(!bindingResult.hasErrors()){
           CurrentUser currentUser = (CurrentUser) authentication.getPrincipal();
           Users users = userService.getByUsername(currentUser.getUsername()).get();
           ligne.setSaveBy(users);
           ligne.setSavedDate(new Date());
           ligneService.saveOrUpdate(ligne);
           model.addAttribute("ligne", new Ligne());
           redirectAttrs.addFlashAttribute("messages", "success");
           return "redirect:/ligne";
       }else{
           model.addAttribute("buses",getUnassignedBus(busService.getAll()));
           model.addAttribute("ligne",ligne);
           model.addAttribute("messages", "unsuccess");

           return "ligne";
       }
    }

    @RequestMapping(value = "/ligne/disable", method = RequestMethod.POST)
    public String disableLigne(@Valid @ModelAttribute("ligne") Ligne ligne, BindingResult bindingResult, Model model){

        ligneService.disable(ligne);
        model.addAttribute("ligne", new Ligne());
        return "redirect:/ligne";

    }
    @PreAuthorize("hasAuthority('MANAGER')")
    @RequestMapping(value = "/ligne/list",method = RequestMethod.GET)
    public String getListPage(Model model){
        model.addAttribute("ligne",ligneService.getAll());
        return "ligneList";
    }
    @PreAuthorize("hasAuthority('MANAGER')")
    @RequestMapping(value = "/ligne/edit/{id}", method = RequestMethod.GET)
    public String getEditPage(@PathVariable String id, Model model) {
        Integer idLigne = Integer.parseInt(id);
        Ligne ligne = ligneService.getById(idLigne);
        model.addAttribute("ligne", ligne);
        model.addAttribute("buses",busService.getAll());
        return "ligneEdit";
    }
    @PreAuthorize("hasAuthority('MANAGER')")
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
