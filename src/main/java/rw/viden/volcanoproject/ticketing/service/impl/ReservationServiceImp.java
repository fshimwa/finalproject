package rw.viden.volcanoproject.ticketing.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import rw.viden.volcanoproject.ticketing.dao.ReservationDao;
import rw.viden.volcanoproject.ticketing.model.Customer;
import rw.viden.volcanoproject.ticketing.model.Ligne;
import rw.viden.volcanoproject.ticketing.model.Reservation;

import rw.viden.volcanoproject.ticketing.service.ReservationService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Viden ltd on 19/05/2016.
 */
@Service
@Transactional
public class ReservationServiceImp implements ReservationService {
    @Autowired
    ReservationDao reservationDao;

    @Override
    public void saveOrUpdate(Reservation reservation) {reservationDao.save(reservation);

    }

    @Override
    public void disable(Reservation reservation) {

       // Reservation us =  reservationDao.findOne(reservation.getId());
       // us.setVoided(false);
        //reservationDao.save(us);
    }

    @Override
    public List<Reservation> getAll() {
        return reservationDao.findAll();
    }

    @Override
    public List<Reservation> getByDate(Date date) {
        return reservationDao.findByDate(date);
    }

    @Override
    public List<Reservation> getByPaid(boolean paid) {
        return reservationDao.findByPaid(paid);
    }

    @Override
    public List<Reservation> getByDateAndLigne(Date date, Ligne ligne) {
        List<Reservation> reservations=new ArrayList<>();
        for(Reservation reservation: getByDate(date)){
            if(reservation.getLigne().equals(ligne)){
                reservations.add(reservation);
            }
        }
        return reservations;
    }

    // @Override
   // public List<Reservation> getByTrip(Trip trip) {
       // return reservationDao.findAll();
   // }

    @Override
    public Reservation getById(Integer idReservation) {
        return reservationDao.findOne(idReservation);
    }

    @Override
    public int countByCustomerAndLigne(Customer customer, Ligne ligne) {
        List<Reservation> reservations=getByPaid(true);
        int count=0;
        for(Reservation reservation:reservations){
            if(reservation.getCustomer().equals(customer))
                if(reservation.getLigne().equals(ligne))
                    count+=1;
        }
        return count;
    }

    @Override
    public List<Reservation> getByLigne(Ligne ligne) {
        List<Reservation> reservations=new ArrayList<>();
        for(Reservation reservation:getAll()){
            if(reservation.getLigne().equals(ligne))
                reservations.add(reservation);
        }
        return reservations;
    }
}
