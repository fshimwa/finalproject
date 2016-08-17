package rw.viden.volcanoproject.ticketing.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import rw.viden.volcanoproject.ticketing.model.*;
import rw.viden.volcanoproject.ticketing.service.*;

import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by Viden ltd on 26/05/2016.
 */
@Controller
public class JourneyContoller {
    @Autowired
    JourneyService journeyService;
    @Autowired
    UserService userService;
    @Autowired
    LigneService ligneService;
    @Autowired
    BusService busService;

    /**
     * @param model
     * @return
     */
    @PreAuthorize("hasAuthority('MANAGER')")
    @RequestMapping(value = "/journey", method = RequestMethod.GET)
    public String getJourneyPage(Model model) {
        model.addAttribute("journey", new Journey());
        //model.addAttribute("trips",tripService.getAll());
        //model.addAttribute("customers",customerService.getAll());
        model.addAttribute("lignes",ligneService.getAll());
        model.addAttribute("buses",busService.getAll());
        return "journey";
    }

    /**
     * @param journey
     * @param bindingResult
     * @param model
     * @return
     */
    @PreAuthorize("hasAuthority('MANAGER')")
    @RequestMapping(value = "/journey/save", method = RequestMethod.POST)
    public String saveJourney(@Valid @ModelAttribute("journey") Journey journey,BindingResult bindingResult, Authentication authentication , Model model, RedirectAttributes redirectAttrs) {
        if(!bindingResult.hasErrors()) {
            CurrentUser currentUser = (CurrentUser) authentication.getPrincipal();
            Users users = userService.getByUsername(currentUser.getUsername()).get();
            journey.setSaveBy(users);
            journey.setSavedDate(new Date());
            journeyService.saveOrUpdate(journey);
            model.addAttribute("journey", new Journey());
            redirectAttrs.addFlashAttribute("messages", "success");
            return "redirect:/ligne/list";
        }else{

            model.addAttribute("lignes",ligneService.getAll());
            model.addAttribute("buses",busService.getAll());
            model.addAttribute("journey", journey);
            model.addAttribute("messages", "unsuccess");
            return "/journey";
        }
    }

        @RequestMapping(value = "/journey/disable", method = RequestMethod.POST)
        public String disableJourney (@Valid @ModelAttribute("journey") Journey journey, BindingResult
        bindingResult, Model model){

            journeyService.disable(journey);
            model.addAttribute("journey", new Journey());
            return "redirect:/journey";

        }
    @PreAuthorize("hasAuthority('MANAGER')")
    @RequestMapping(value = "/journey/list",method = RequestMethod.GET)
    public String getListPage(Model model){
        model.addAttribute("journey",journeyService.getAll());
        return "journeyList";
    }
    @PreAuthorize("hasAuthority('MANAGER')")
    @RequestMapping(value = "/journey/edit/{id}", method = RequestMethod.GET)
    public String getEditPage(@PathVariable String id, Model model) {
        Integer idJourney = Integer.parseInt(id);
        Journey journey = journeyService.getById(idJourney);
        model.addAttribute("journey", journey);
        model.addAttribute("lignes",ligneService.getAll());
        model.addAttribute("buses",busService.getAll());
        return "journeyEdit";
    }
    @InitBinder
    public void initBinder(WebDataBinder binder){
        binder.registerCustomEditor(       Date.class,
                new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true, 10));
    }
    @RequestMapping(value = "/getbus", method = RequestMethod.GET)
    public @ResponseBody
    ResponseEntity<List<Bus>>getBusPage(@RequestParam("idLigne") String idLigne) {
        List<Bus> buses=ligneService.getBusByIdLigne(Integer.parseInt(idLigne));
        return new ResponseEntity<List<Bus>>(buses, HttpStatus.OK);
    }
    }




