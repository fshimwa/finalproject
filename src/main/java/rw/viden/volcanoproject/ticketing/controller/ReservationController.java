package rw.viden.volcanoproject.ticketing.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
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
 * Created by Viden ltd on 27/05/2016.
 */
@Controller
public class ReservationController {
    @Autowired
    ReservationService reservationService;
    @Autowired
    UserService userService;
    @Autowired
    LigneService ligneService;
    @Autowired
    CustomerService customerService;
    @Autowired
    PaymentService paymentService;
    @Autowired
    JourneyService journeyService;
    //@Autowired
    //BusService busService;

    /**
     * @param model
     * @return
     */
    @PreAuthorize("hasAuthority('EMPLOYEE')")
    @RequestMapping(value = "/reservation", method = RequestMethod.GET)
    public String getReservationPage(Model model) {
        model.addAttribute("reservation", new Reservation());
        //model.addAttribute("trips",tripService.getAll());
        model.addAttribute("customers", customerService.getAll());
        model.addAttribute("lignes", ligneService.getAll());
        model.addAttribute("payment", paymentService.getAll());
        //model.addAttribute("buses",busService.getAll());
        return "reservation";
    }

    /**
     * @param reservation
     * @param bindingResult
     * @param model
     * @return
     */
    @PreAuthorize("hasAuthority('EMPLOYEE')")
    @RequestMapping(value = "/reservation/save", method = RequestMethod.POST)
    public String saveReservation(@Valid @ModelAttribute("reservation") Reservation reservation, BindingResult bindingResult, Authentication authentication, Model model, RedirectAttributes redirectAttrs) {
        if (!bindingResult.hasErrors()) {
            Date resDate = reservation.getDate();
            Ligne ligne = reservation.getLigne();

            List<Reservation> reservations = reservationService.getByDateAndLigne(resDate, ligne);
            List<Journey> journeys = journeyService.getByDateAndLigne(resDate, ligne);
            int busSpace = 0;
            int countReservations = 0;
            if(journeys.size()<=0){
                model.addAttribute("message","No Journey Scheduled on that time, Contact Administrator");
                return "error";
            }
            for (Journey j : journeys) {

                if (j.getTimeDeparture().replace(" ","").equalsIgnoreCase(reservation.getTime().replace(" ",""))) {
                    for (Bus b : j.getLigne().getAssignedBus()) {

                        busSpace += b.getSeats();

                    }

                }
            }
            for (Reservation r : reservations) {
                if (r.getTime().equalsIgnoreCase(reservation.getTime()))
                    countReservations += 1;
            }
            if (countReservations+1 <=busSpace) {

                CurrentUser currentUser = (CurrentUser) authentication.getPrincipal();
                Users users = userService.getByUsername(currentUser.getUsername()).get();
                reservation.setSaveBy(users);
                reservation.setSavedDate(new Date());
                reservationService.saveOrUpdate(reservation);
                model.addAttribute("reservation", new Reservation());
                redirectAttrs.addFlashAttribute("messages", "success");
                return "redirect:/customer/list";
            }else {
                model.addAttribute("message","Bus FULL Reservations made:" +countReservations +" with "+busSpace+ " available Places");
                return "errorCustom";

            }
        } else {

            model.addAttribute("lignes", ligneService.getAll());
            model.addAttribute("customers", customerService.getAll());
            model.addAttribute("payment", paymentService.getAll());
            model.addAttribute("reservation", reservation);
            model.addAttribute("messages", "unsuccess");
            return "/reservation";
        }
    }

    @RequestMapping(value = "/reservation/disable", method = RequestMethod.POST)
    public String disableReservation(@Valid @ModelAttribute("reservation") Reservation reservation, BindingResult
            bindingResult, Model model) {

        reservationService.disable(reservation);
        model.addAttribute("reservation", new Reservation());
        return "redirect:/reservation";

    }

    @RequestMapping(value = "/reservation/list", method = RequestMethod.GET)
    public String getListPage(Model model) {
        model.addAttribute("reservation", reservationService.getByPaid(false));
        return "reservationList";
    }
    @PreAuthorize("hasAuthority('EMPLOYEE')")
    @RequestMapping(value = "/reservation/edit/{id}", method = RequestMethod.GET)
    public String getEditPage(@PathVariable String id, Model model) {
        Integer idReservation = Integer.parseInt(id);
        Reservation reservation = reservationService.getById(idReservation);
        model.addAttribute("reservation", reservation);
//        Integer idCustomer = Integer.parseInt(id);
        Customer customer = reservation.getCustomer();
        model.addAttribute("customer", customer);
//        Reservation reservation = new Reservation();
//        reservation.setCustomer(customer);
        model.addAttribute("reservation", reservation);
        model.addAttribute("lignes", ligneService.getAll());
        return "reservationEdit";
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Date.class,
                new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true, 10));
    }
    @PreAuthorize("hasAuthority('EMPLOYEE')")
    @RequestMapping(value = "/customer/reserve/{id}", method = RequestMethod.GET)
    public String getReservePage(@PathVariable String id, Model model) {
        Integer idCustomer = Integer.parseInt(id);
        Customer customer = customerService.getById(idCustomer);
        model.addAttribute("customer", customer);
        Reservation reservation = new Reservation();
        reservation.setCustomer(customer);
        model.addAttribute("reservation", reservation);
        model.addAttribute("lignes", ligneService.getAll());
        return "customerReserve";
    }
}
