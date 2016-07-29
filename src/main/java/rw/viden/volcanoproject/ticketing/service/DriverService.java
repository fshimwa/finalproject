package rw.viden.volcanoproject.ticketing.service;

import org.springframework.transaction.annotation.Transactional;
import rw.viden.volcanoproject.ticketing.model.Driver;

import java.util.List;

/**
 * Created by Viden ltd on 19/05/2016.
 */
@Transactional
public interface DriverService {
    void saveOrUpdate(Driver driver);
    void disable(Driver driver);

    List<Driver> getAll();
    List getByDrivingLincence(String drivingLincence);
    List getByName(String name);

    Driver getById(Integer idDriver);
}

