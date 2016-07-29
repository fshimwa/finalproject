package rw.viden.volcanoproject.ticketing.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import rw.viden.volcanoproject.ticketing.dao.CustomerDao;
import rw.viden.volcanoproject.ticketing.model.Customer;
import rw.viden.volcanoproject.ticketing.service.CustomerService;

import java.util.List;

/**
 * Created by Viden ltd on 18/05/2016.
 */
@Service
@Transactional
public class CustomerServiceImp implements CustomerService {

    @Autowired
    CustomerDao customerDao;

    @Override
    public void saveOrUpdate(Customer customer) {customerDao.save(customer);}

    @Override
    public void disable(Customer customer) {

    }

    @Override
    public List<Customer> getAll() {
        return customerDao.findAll();
    }

    @Override
    public List<Customer> getByName(String name) {
        return customerDao.findByName(name);
    }

    @Override
    public List<Customer> getByTelephone(String telephone) {
        return customerDao.findByTelephone(telephone);
    }

    @Override
    public Customer getById(Integer idCustomer) {
        return customerDao.findOne(idCustomer);
    }
}


