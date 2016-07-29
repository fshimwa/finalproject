package rw.viden.volcanoproject.ticketing.service;

import org.springframework.transaction.annotation.Transactional;
import rw.viden.volcanoproject.ticketing.model.Ticket;

import java.util.List;

/**
 * Created by Viden ltd on 7/5/2016.
 */
@Transactional
public interface TicketService {
    void saveOrUpdate(Ticket ticket);
    void disable(Ticket ticket);
    List<Ticket> getAll();
    Ticket getById(Integer idTicket);
}
