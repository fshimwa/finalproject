package rw.viden.volcanoproject.ticketing.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import rw.viden.volcanoproject.ticketing.dao.TicketDao;
import rw.viden.volcanoproject.ticketing.model.Ticket;
import rw.viden.volcanoproject.ticketing.service.TicketService;

import java.util.List;

/**
 * Created by Viden ltd on 7/5/2016.
 */
@Service
@Transactional
public class TicketServiceImpl implements TicketService {
    @Autowired
    TicketDao ticketDao;

    @Override
    public void saveOrUpdate(Ticket ticket) {ticketDao.save(ticket);

    }

    @Override
    public void disable(Ticket ticket) {

    }

    @Override
    public List<Ticket> getAll() {
        return ticketDao.findAll();
    }

    @Override
    public Ticket getById(Integer idTicket) {
        return ticketDao.findOne(idTicket);
    }
}
