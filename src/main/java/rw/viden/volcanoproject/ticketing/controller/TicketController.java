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
import rw.viden.volcanoproject.ticketing.model.Bus;
import rw.viden.volcanoproject.ticketing.model.CurrentUser;
import rw.viden.volcanoproject.ticketing.model.Ticket;
import rw.viden.volcanoproject.ticketing.model.Users;
import rw.viden.volcanoproject.ticketing.service.*;

import javax.validation.Valid;
import java.util.Date;

/**
 * Created by Viden ltd on 7/5/2016.
 */
@Controller
public class TicketController {
    @Autowired
    TicketService ticketService;
    @Autowired
    UserService userService;
    @Autowired
    ReservationService reservationService;
    @Autowired
    PaymentService paymentService;
    @Autowired
    BusService busService;

    @RequestMapping(value = "/ticket", method = RequestMethod.GET)
    public String getTicketPage(Model model) {
        model.addAttribute("ticket", new Ticket());
        return "ticket";
    }

    @RequestMapping(value = "/ticket/save", method = RequestMethod.POST)
    public String saveTicket(@Valid @ModelAttribute("ticket") Ticket ticket, Authentication authentication, BindingResult bindingResult, Model model) {
        CurrentUser currentUser = (CurrentUser) authentication.getPrincipal();
        Users users=userService.getByUsername(currentUser.getUsername()).get();
        ticket.setSaveBy(users);
        ticket.setSavedDate(new Date());
        ticketService.saveOrUpdate(ticket);
        model.addAttribute("ticket", new Ticket());
        return "redirect:/ticket";
    }

    @RequestMapping(value = "/ticket/disable", method = RequestMethod.POST)
    public String disableTicket(@Valid @ModelAttribute("ticket") Ticket ticket, BindingResult bindingResult, Model model) {

        ticketService.disable(ticket);
        model.addAttribute("ticket", new Ticket());
        return "redirect:/ticket";
    }
    @RequestMapping(value = "/ticket/list",method = RequestMethod.GET)
    public String getListPage(Model model){
        model.addAttribute("ticket",ticketService.getAll());
        return "ticketList";
    }

    @RequestMapping(value = "/ticket/edit/{id}", method = RequestMethod.GET)
    public String getEditPage(@PathVariable String id, Model model) {
        Integer idTicket = Integer.parseInt(id);
        Ticket ticket = ticketService.getById(idTicket);
        model.addAttribute("ticket", ticket);
        model.addAttribute("buses",busService.getAll());
        model.addAttribute("payment", paymentService.getAll());
        model.addAttribute("reservation", reservationService.getAll());
        return "ticketEdit";
    }

}


