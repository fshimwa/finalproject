package rw.viden.volcanoproject.ticketing.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rw.viden.volcanoproject.ticketing.model.Ligne;

import java.util.List;

/**
 * Created by Viden ltd on 20/05/2016.
 */
@Repository
public interface LigneDao extends JpaRepository<Ligne,Integer> {
    List<Ligne> findByFromDestinationAndToDestination(String fromDep, String toDep);
//    List<Ligne> findByName(String name);
}
