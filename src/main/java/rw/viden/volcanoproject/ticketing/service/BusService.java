package rw.viden.volcanoproject.ticketing.service;

import org.springframework.transaction.annotation.Transactional;
import rw.viden.volcanoproject.ticketing.model.Bus;
import rw.viden.volcanoproject.ticketing.model.Driver;

import java.util.List;
import java.util.Optional;

/**
 * Created by Viden ltd on 18/05/2016.
 */
@Transactional
public interface BusService {
    void saveOrUpdate(Bus bus);
    void disable(Bus bus);
    List<Bus> getAll();
    Optional<Bus> getByPlaque(String plaque);
    List getByDriver(Driver driver);
    boolean isPlaqueUsed(String plaque);
    Bus getById(Integer idBus);

}
