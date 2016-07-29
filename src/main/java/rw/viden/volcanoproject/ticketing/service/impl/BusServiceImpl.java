package rw.viden.volcanoproject.ticketing.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import rw.viden.volcanoproject.ticketing.dao.BusDao;
import rw.viden.volcanoproject.ticketing.model.Bus;
import rw.viden.volcanoproject.ticketing.model.Driver;
import rw.viden.volcanoproject.ticketing.service.BusService;

import java.util.List;
import java.util.Optional;

/**
 * Created by Viden ltd on 18/05/2016.
 */
@Service
@Transactional
public class BusServiceImpl implements BusService {

    @Autowired
    BusDao busDao;

    @Override
    public void saveOrUpdate(Bus bus) {busDao.save(bus);
    }

    @Override
    public void disable(Bus bus) {

    }

    @Override
    public List<Bus> getAll() {
        return busDao.findAll();
    }

    @Override
    public Optional<Bus> getByPlaque(String plaque) {
        return busDao.findByPlaque(plaque);
    }

    @Override
    public List<Bus> getByDriver(Driver driver) {

        return busDao.findAll();
    }

    @Override
    public boolean isPlaqueUsed(String plaque) {
        Optional<Bus> bus1=getByPlaque(plaque);
        if(bus1.isPresent())
            return true;
        else
            return false;
    }

    @Override
    public Bus getById(Integer idBus) {
        return busDao.findOne(idBus);
    }
}
