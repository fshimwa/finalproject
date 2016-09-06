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
import rw.viden.volcanoproject.ticketing.service.PaymentService;
import rw.viden.volcanoproject.ticketing.service.ReservationService;
import rw.viden.volcanoproject.ticketing.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by Viden ltd on 27/05/2016.
 */

@Controller
public class PaymentController {
    @Autowired
    PaymentService paymentService;
    @Autowired
    UserService userService;
    @Autowired
    ReservationService reservationService;

    /*
     * @param model
     * @return
     */
    @PreAuthorize("hasAnyAuthority('EMPLOYEE','ADMIN')")
    @RequestMapping(value = "/payment", method = RequestMethod.GET)
    public String getPaymentPage(Model model) {
        model.addAttribute("payment", new Payment());
        model.addAttribute("reservations", reservationService.getAll());
        return "payment";
    }

    /**
     * @param payment
     * @param bindingResult
     * @param model
     * @return
     */
    @PreAuthorize("hasAnyAuthority('EMPLOYEE','ADMIN')")
    @RequestMapping(value = "/payment/save", method = RequestMethod.POST)
    public String saveJourney(@Valid @ModelAttribute("payment") Payment payment, BindingResult bindingResult, Authentication authentication, Model model, RedirectAttributes redirectAttrs) {
        if (!bindingResult.hasErrors()) {
            CurrentUser currentUser = (CurrentUser) authentication.getPrincipal();
            Users users = userService.getByUsername(currentUser.getUsername()).get();
            payment.setSaveBy(users);
            payment.setSavedDate(new Date());
            Reservation reservation = reservationService.getById(payment.getReservation().getId());
            reservation.setPaid(true);
            reservationService.saveOrUpdate(reservation);
            paymentService.saveOrUpdate(payment);
            model.addAttribute("payment", new Payment());
            redirectAttrs.addFlashAttribute("messages", "success");
            return "redirect:/payment/list";
        } else {
            System.out.println(bindingResult.getAllErrors().size()+" "+bindingResult.getFieldError().toString());
            model.addAttribute("reservations", reservationService.getAll());
            model.addAttribute("payment", payment);
            model.addAttribute("messages", "unsuccess");
            return "/payment/list";
        }
    }
    @PreAuthorize("hasAnyAuthority('EMPLOYEE','ADMIN')")
    @RequestMapping(value = "/payment/list", method = RequestMethod.GET)
    public String getListPage(Model model) {
        model.addAttribute("payment", paymentService.getAll());
        return "paymentList";
    }
    @PreAuthorize("hasAnyAuthority('EMPLOYEE','ADMIN')")
    @RequestMapping(value = {"/payment/report"}, method = RequestMethod.GET)
    public String reportPage(Model model, HttpServletRequest request) {
        return "paymentreport";
    }

    @PreAuthorize("hasAnyAuthority('EMPLOYEE','ADMIN')")
    @RequestMapping(value = {"/payment/report"}, method = RequestMethod.POST)
    public String listPage(Model model, HttpServletRequest request) {


        try {

            String dateString = request.getParameter("startdate");
            DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
            Date startdate = format.parse(dateString);

            String enddateString = request.getParameter("enddate");
            Date enddate = format.parse(enddateString);

            List<Payment> dataEntryReports = paymentService.getByDateBetween(startdate,enddate);
//            System.out.println(dataEntryReports.size());


            model.addAttribute("payment", dataEntryReports);
            return "paymentList";
        }catch (ParseException e){
            return "redirect:/paymentreport";
        }
    }
    @PreAuthorize("hasAnyAuthority('EMPLOYEE','ADMIN')")
    @RequestMapping(value = "/payment/edit/{id}", method = RequestMethod.GET)
    public String getEditPage(@PathVariable String id, Model model) {
        Integer idPayment = Integer.parseInt(id);
        Payment payment = paymentService.getById(idPayment);
        model.addAttribute("payment", payment);
        return "paymentEdit";
    }
    @PreAuthorize("hasAnyAuthority('EMPLOYEE','ADMIN')")
    @RequestMapping(value = "/reservation/pay/{id}", method = RequestMethod.GET)
    public String getPayPage(@PathVariable String id, Model model) {
        Integer idReservation = Integer.parseInt(id);
        Reservation reservation = reservationService.getById(idReservation);

        Date date =new Date();
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("HH:mm:ss");
        String time=simpleDateFormat.format(new Date());
        model.addAttribute("reservation", reservation);
        Payment payment=new Payment();
        payment.setReservation(reservation);
        payment.setDatePayment(date);
        payment.setTimePayment(time);
        payment.setAmount(reservation.getLigne().getPrice());
        model.addAttribute("payment", payment);
        model.addAttribute("date", date);
        model.addAttribute("time", time);
        return "payment";
    }
    @InitBinder
    public void initBinder(WebDataBinder binder){
        binder.registerCustomEditor(       Date.class,
                new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true, 10));
    }
}
