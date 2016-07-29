package rw.viden.volcanoproject.ticketing.service;

import rw.viden.volcanoproject.ticketing.model.Price;

import java.util.List;

/**
 * Created by Viden ltd on 7/5/2016.
 */
public interface PriceHistorsService {
    void saveOrUpdate(Price priceHistors);
    void disable(Price priceHistors);
    List<Price> getAll();
    Price getById(Integer id);

}
