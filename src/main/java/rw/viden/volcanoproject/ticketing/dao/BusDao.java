package rw.viden.volcanoproject.ticketing.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import rw.viden.volcanoproject.ticketing.model.Bus;

import java.util.List;
import java.util.Optional;

/**
 * Created by Viden ltd on 18/05/2016.
 */
public interface BusDao extends JpaRepository<Bus, Integer> {
    Optional<Bus> findByPlaque(String plaque);
}

