package rw.viden.volcanoproject.ticketing.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import rw.viden.volcanoproject.ticketing.model.Price;

/**
 * Created by Viden ltd on 7/5/2016.
 */
public interface PriceHistorsDao extends JpaRepository<Price, Integer> {
   //List<PriceHistors> findBy(PriceAmount priceAmount);
}
