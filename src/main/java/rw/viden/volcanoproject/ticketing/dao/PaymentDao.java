package rw.viden.volcanoproject.ticketing.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import rw.viden.volcanoproject.ticketing.model.Payment;


import java.sql.Date;
import java.util.List;

/**
 * Created by Viden ltd on 20/05/2016.
 */
public interface PaymentDao extends JpaRepository<Payment,Integer> {
    //List<Payment> findByReceipt(String receipt);
    List<Payment> findByDatePayment(Date date);

    List<Payment> findByDatePaymentBetween(Date start, Date end);
}
