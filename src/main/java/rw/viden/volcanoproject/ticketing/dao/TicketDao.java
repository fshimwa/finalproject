package rw.viden.volcanoproject.ticketing.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import rw.viden.volcanoproject.ticketing.model.Reservation;
import rw.viden.volcanoproject.ticketing.model.Ticket;

import java.util.List;

/**
 * Created by Viden ltd on 7/5/2016.
 */
public interface TicketDao extends JpaRepository<Ticket, Integer> {
   //List<Ticket> findBy(Reservation reservation);
}
