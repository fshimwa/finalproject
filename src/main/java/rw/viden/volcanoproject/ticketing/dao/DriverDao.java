package rw.viden.volcanoproject.ticketing.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import rw.viden.volcanoproject.ticketing.model.Driver;

import java.util.List;

/**
 * Created by Viden ltd on 20/05/2016.
 */
public interface DriverDao extends JpaRepository<Driver,Integer> {
    List<Driver> findByName(String name);
}
