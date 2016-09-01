package rw.viden.volcanoproject.ticketing.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import rw.viden.volcanoproject.ticketing.model.*;
import rw.viden.volcanoproject.ticketing.service.*;
import rw.viden.volcanoproject.ticketing.util.ReservationBusUtil;

import javax.validation.constraints.NotNull;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by nic on 7/28/16.
 */
@RestController
public class APIController {
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

    @RequestMapping(value = "/api/sms", method = RequestMethod.GET)
    @ResponseBody
    public String saveSms(@RequestParam("text") String text, @RequestParam("sender") String sender, @RequestParam("time") String time) {
       System.out.println(text+"====================");
        String[] mess = text.split(" ");
        if (mess.length >= 4) {
            String hack = mess[0];
            String keyword = mess[1];
            String fromDep = mess[2];
            String toDest = mess[3];
            String dateJou = mess[4];
            String timeJou = mess[5];
            if (keyword.equalsIgnoreCase("check")) {

                List<Ligne> lignes = ligneService.getByFromAndTo(fromDep, toDest);
                if (lignes.size() <= 0) {
                    System.out.println("Departure Destination not exist" +" ====================");
                    return "Departure Destination not exist";
                }

                DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
                Date date = new Date();
                try {
                    date = format.parse(dateJou);
                } catch (ParseException e) {
                    System.out.println("Date is incorrect" +" ====================");
                    return "Date is incorrect";
                }
                return reserveAndCheck(date,lignes.get(0),timeJou,true,sender);

            }else if (keyword.equalsIgnoreCase("reserve")) {

                List<Ligne> lignes = ligneService.getByFromAndTo(fromDep, toDest);
                if (lignes.size() <= 0) {
                    System.out.println("Date is incorrect" +" ====================");
                    return "Date is incorrect";
                }
                DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
                Date date ;
                try {
                    date = format.parse(dateJou);
                } catch (ParseException e) {
                    System.out.println("Date is incorrect" +" ====================");
                    return "Date is incorrect";
                }
                return reserveAndCheck(date,lignes.get(0),timeJou,false,sender);

            }

        }
        else if(mess[1].equals("pay")){
            Reservation reservation=reservationService.getById(Integer.parseInt(mess[2]));
            try {
                checkNotNull(reservation);
            }catch (NullPointerException e){
                return "Couldn't find reservation with ID"+mess[2];
            }
            return "Payment initiated for Reservation with ID: "+reservation.getId();
        }
        return "Welcome To Ticketing System Test";
    }

    private String reserveAndCheck(Date resDate, Ligne ligne, String time, boolean check, String telephone) {
        List<Reservation> reservations = reservationService.getByDateAndLigne(resDate, ligne);
        List<Journey> journeys = journeyService.getByDateAndLigne(resDate, ligne);
        int busSpace = 0;
        int countReservations = 0;
        if (journeys.size() <= 0) {
            return "No Journey Scheduled on that time, Contact Administrator";
        }
        for (Journey j : journeys) {

            if (j.getTimeDeparture().replace(" ", "").equalsIgnoreCase(time)) {
                for (Bus b : j.getLigne().getAssignedBus()) {

                    busSpace += b.getSeats();

                }

            }
        }
        for (Reservation r : reservations) {
            if (r.getTime().equalsIgnoreCase(time))
                countReservations += 1;
        }

        if (countReservations + 1 <= busSpace) {
            if (check) {
                int availablePlaces = busSpace - countReservations;
                return "Place available " + availablePlaces;
            }
            List<Customer> customers = customerService.getByTelephone(telephone);
            if (customers.size() <= 0) {
                return "You are not registered, please visit our office to register";
            }
            Customer customer = customers.get(0);

            Users users = userService.getByUsername("API").get();
            Reservation reservation = new Reservation();
            reservation.setCustomer(customer);
            reservation.setSaveBy(users);
            reservation.setSavedDate(new Date());
            reservation.setLigne(ligne);
            reservation.setDate(resDate);
            reservation.setTime(time);
            reservationService.saveOrUpdate(reservation);
            ReservationBusUtil reservationBusUtil =new ReservationBusUtil();
            reservationBusUtil.addBusAfterReservation(reservation.getDate(), reservation.getLigne(),reservation.getTime());

            return "Reservation Saved, to pay use this number: " + reservation.getId();
        } else {
            return "Bus FULL Reservations Not made";


        }
    }
}
