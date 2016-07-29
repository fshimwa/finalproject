package rw.viden.volcanoproject.ticketing.service;

import org.springframework.transaction.annotation.Transactional;
import rw.viden.volcanoproject.ticketing.model.Bus;
import rw.viden.volcanoproject.ticketing.model.Journey;
import rw.viden.volcanoproject.ticketing.model.Ligne;

import java.util.Date;
import java.util.List;

/**
 * Created by Viden ltd on 19/05/2016.
 */
@Transactional
public interface JourneyService {
    void saveOrUpdate(Journey journey);
    void disable(Journey journey);
    List<Journey> getAll();

    Journey getById(Integer idJourney);
     List<Journey> getByDateAndLigne(Date date, Ligne ligne);
    //List getByIdLigne(Integer idLigne);


}
