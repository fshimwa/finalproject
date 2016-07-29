package rw.viden.volcanoproject.ticketing.service;

import rw.viden.volcanoproject.ticketing.model.Bus;
import rw.viden.volcanoproject.ticketing.model.Ligne;

import java.util.List;

/**
 * Created by Viden ltd on 7/8/2016.
 */
public interface LigneService {
    void saveOrUpdate(Ligne ligne);
    void disable(Ligne ligne);
    List<Ligne> getAll();
    Ligne getById(Integer idLigne);
    List<Bus> getBusByIdLigne(Integer idLigne);
    List<Ligne> getByFromAndTo(String fromDep, String toDep);
}
