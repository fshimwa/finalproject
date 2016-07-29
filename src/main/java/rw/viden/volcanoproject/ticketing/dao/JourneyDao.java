package rw.viden.volcanoproject.ticketing.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import rw.viden.volcanoproject.ticketing.model.Customer;
import rw.viden.volcanoproject.ticketing.model.Journey;

import java.util.Date;
import java.util.List;

/**
 * Created by Viden ltd on 20/05/2016.
 */
public interface JourneyDao extends JpaRepository<Journey,Integer> {

    List<Journey> findByDate(Date date);
}
