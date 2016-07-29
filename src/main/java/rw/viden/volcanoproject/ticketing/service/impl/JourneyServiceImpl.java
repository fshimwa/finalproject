package rw.viden.volcanoproject.ticketing.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import rw.viden.volcanoproject.ticketing.dao.JourneyDao;
import rw.viden.volcanoproject.ticketing.model.Journey;
import rw.viden.volcanoproject.ticketing.model.Ligne;
import rw.viden.volcanoproject.ticketing.service.JourneyService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Viden ltd on 19/05/2016.
 */
@Service
@Transactional
public class JourneyServiceImpl implements JourneyService {
    @Autowired
    JourneyDao journeyDao;


    @Override
    public void saveOrUpdate(Journey journey) {
        journeyDao.save(journey);
    }

    @Override
    public void disable(Journey journey) {

    }

    @Override
    public List<Journey> getAll() {
        return journeyDao.findAll();
    }

    @Override
    public Journey getById(Integer idJourney) {
        return journeyDao.findOne(idJourney);
    }

    @Override
    public List<Journey> getByDateAndLigne(Date date, Ligne ligne) {
        List<Journey> journeys=journeyDao.findByDate(date);
        List<Journey> journeyList=new ArrayList<>();
        for(Journey j:journeys){
            if(j.getLigne().equals(ligne))
                journeyList.add(j);
        }
        return journeyList;
    }
}

