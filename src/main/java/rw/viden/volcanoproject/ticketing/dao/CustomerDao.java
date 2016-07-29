package rw.viden.volcanoproject.ticketing.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import rw.viden.volcanoproject.ticketing.model.Customer;

import java.util.List;

/**
 * Created by Viden ltd on 18/05/2016.
 */
public interface CustomerDao extends JpaRepository<Customer,Integer> {
    List<Customer> findByName(String name);


    List<Customer> findByTelephone(String telephone);
}
