package rw.viden.volcanoproject.ticketing.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import rw.viden.volcanoproject.ticketing.model.Customer;
import rw.viden.volcanoproject.ticketing.model.CustomerLigne;
import rw.viden.volcanoproject.ticketing.model.Ligne;
import rw.viden.volcanoproject.ticketing.model.Reservation;
import rw.viden.volcanoproject.ticketing.service.CustomerService;
import rw.viden.volcanoproject.ticketing.service.LigneService;
import rw.viden.volcanoproject.ticketing.service.ReservationService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nic on 7/28/16.
 */
@RestController
public class ReportsController {
    @Autowired
    ReservationService reservationService;
    @Autowired
    LigneService ligneService;
    @Autowired
    CustomerService customerService;

    @RequestMapping(value = "/api/getCount", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<List<CustomerLigne>> getCustomerLigne(){

        List<CustomerLigne> customerLignes=new ArrayList<>();
        int count=0;
        for(Ligne ligne:ligneService.getAll()) {
            for(Customer customer:customerService.getAll()) {
                count = reservationService.countByCustomerAndLigne(customer, ligne);
                customerLignes.add(new CustomerLigne(customer,ligne,count));
            }

        }
        return new ResponseEntity<>(customerLignes, HttpStatus.OK);
    }

}
