package rw.viden.volcanoproject.ticketing.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import rw.viden.volcanoproject.ticketing.model.Reservation;

import java.util.Date;
import java.util.List;

/**
 * Created by Viden ltd on 20/05/2016.
 */
public interface ReservationDao extends JpaRepository<Reservation,Integer> {
    List<Reservation> findByDate(Date date);


    List<Reservation> findByPaid(boolean paid);
}
