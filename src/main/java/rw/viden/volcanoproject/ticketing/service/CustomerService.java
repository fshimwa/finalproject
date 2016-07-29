package rw.viden.volcanoproject.ticketing.service;

import org.springframework.transaction.annotation.Transactional;
import rw.viden.volcanoproject.ticketing.model.Customer;

import java.util.List;

/**
 * Created by Viden ltd on 18/05/2016.
 */
@Transactional
public interface CustomerService {

    void disable(Customer customer);
    void saveOrUpdate(Customer customer);
    List<Customer> getAll();
    List getByName(String name);
    List<Customer> getByTelephone(String telephone);

    Customer getById(Integer idCustomer);
}
