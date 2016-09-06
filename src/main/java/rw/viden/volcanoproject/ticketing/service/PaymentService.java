package rw.viden.volcanoproject.ticketing.service;

import org.springframework.transaction.annotation.Transactional;
import rw.viden.volcanoproject.ticketing.model.Payment;

import java.util.Date;
import java.util.List;

/**
 * Created by Viden ltd on 19/05/2016.
 */
@Transactional
public interface  PaymentService {
    void saveOrUpdate(Payment payment);
    void disable(Payment payment);
    List<Payment> getAll();
    List<Payment> getByDate(Date date);
    List<Payment> getByDateBetween(Date start, Date end);

    Payment getById(Integer idPayment);
}
