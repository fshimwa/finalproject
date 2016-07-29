package rw.viden.volcanoproject.ticketing.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import rw.viden.volcanoproject.ticketing.dao.CustomerDao;
import rw.viden.volcanoproject.ticketing.dao.LigneDao;
import rw.viden.volcanoproject.ticketing.model.Bus;
import rw.viden.volcanoproject.ticketing.model.Ligne;
import rw.viden.volcanoproject.ticketing.service.LigneService;

import java.util.List;

/**
 * Created by Viden ltd on 7/8/2016.
 */
@Service
@Transactional
public class LigneServiceImpl implements LigneService {
    @Autowired
    LigneDao ligneDao;

    @Override
    public void saveOrUpdate(Ligne ligne) {ligneDao.save(ligne);

    }

    @Override
    public void disable(Ligne ligne) {

    }

    @Override
    public List<Ligne> getAll() {
        return ligneDao.findAll();
    }

    @Override
    public Ligne getById(Integer idLigne) {
        return ligneDao.findOne(idLigne);
    }

    @Override
    public List<Bus> getBusByIdLigne(Integer idLigne) {
        Ligne ligne=getById(idLigne);

        return ligne.getAssignedBus() ;
    }

    @Override
    public List<Ligne> getByFromAndTo(String fromDep, String toDep) {

        return ligneDao.findByFromDestinationAndToDestination(fromDep,toDep);
    }
}
