package rw.viden.volcanoproject.ticketing.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import rw.viden.volcanoproject.ticketing.model.Bus;
import rw.viden.volcanoproject.ticketing.model.Journey;
import rw.viden.volcanoproject.ticketing.model.Ligne;
import rw.viden.volcanoproject.ticketing.model.Reservation;
import rw.viden.volcanoproject.ticketing.service.*;

import java.util.Date;
import java.util.List;

/**
 * Created by nic on 9/1/16.
 */
@Component
public class ReservationBusUtil {

    private ReservationService reservationService;

    private UserService userService;

    private LigneService ligneService;


    private JourneyService journeyService;

    @Autowired
    public ReservationBusUtil(ReservationService reservationService, UserService userService, LigneService ligneService, JourneyService journeyService) {
        this.reservationService = reservationService;
        this.userService = userService;
        this.ligneService = ligneService;
        this.journeyService = journeyService;
    }

    public ReservationBusUtil() {
    }

    public boolean addBusAfterReservation(Date date, Ligne ligne, String time) {
        List<Reservation> reservations = reservationService.getByDateAndLigne(date, ligne);
        int reserved = 0;
        for (Reservation reservation1 : reservations) {
            if (reservation1.getTime().trim().equalsIgnoreCase(time)) {
                reserved += 1;
            }
        }
        if (reserved > 30)
            reserved -= 30;
        if (reserved >= 20) {
            List<Journey> byDateAndLigne = journeyService.getByDateAndLigne(date, ligne);
            List<Bus> buses = ligneService.getBusByIdLigne(ligne.getIdLigne());
            if (byDateAndLigne.size() <= 0) {
                Journey journey = new Journey();
                journey.setLigne(ligne);
                journey.setDate(date);
                journey.setTimeDeparture(time);
                journey.setSavedDate(new Date());
                journey.setSaveBy(userService.getByUsername("API").get());
                journeyService.saveOrUpdate(journey);
                return true;
            }

        }
        return false;
    }
}
