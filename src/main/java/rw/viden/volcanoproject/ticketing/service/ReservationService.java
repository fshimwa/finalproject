package rw.viden.volcanoproject.ticketing.service;

import org.springframework.transaction.annotation.Transactional;
import rw.viden.volcanoproject.ticketing.model.Customer;
import rw.viden.volcanoproject.ticketing.model.Ligne;
import rw.viden.volcanoproject.ticketing.model.Reservation;

import java.util.Date;
import java.util.List;

/**
 * Created by Viden ltd on 19/05/2016.
 */
@Transactional
public interface ReservationService {
    void saveOrUpdate(Reservation reservation);
    void disable(Reservation reservation);
    List<Reservation> getAll();
    List<Reservation> getByDate(Date date);
    List<Reservation> getByPaid(boolean paid);

    List<Reservation> getByDateAndLigne(Date date, Ligne ligne);
    Reservation getById(Integer idReservation);
    int countByCustomerAndLigne(Customer customer, Ligne ligne);

    List<Reservation> getByLigne(Ligne ligne);
}
