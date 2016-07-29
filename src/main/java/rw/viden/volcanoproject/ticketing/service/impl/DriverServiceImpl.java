package rw.viden.volcanoproject.ticketing.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import rw.viden.volcanoproject.ticketing.dao.DriverDao;
import rw.viden.volcanoproject.ticketing.model.Driver;
import rw.viden.volcanoproject.ticketing.service.DriverService;

import java.util.List;

/**
 * Created by Viden ltd on 19/05/2016.
 */
@Service
@Transactional
public class DriverServiceImpl implements DriverService {
    @Autowired
    DriverDao driverDao;

    @Override
    public void saveOrUpdate(Driver driver) {driverDao.save(driver);}

    @Override
    public void disable(Driver driver) {
    }

    @Override
    public List<Driver> getAll() {
        return driverDao.findAll();
    }

    @Override
    public List<Driver> getByDrivingLincence(String drivingLincence) {
        return driverDao.findAll();
    }

    @Override
    public List<Driver> getByName(String name) {
        return driverDao.findByName(name);
    }

    @Override
    public Driver getById(Integer idDriver) {
        return driverDao.findOne(idDriver);
    }
}
