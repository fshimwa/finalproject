package rw.viden.volcanoproject.ticketing.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import rw.viden.volcanoproject.ticketing.dao.PriceHistorsDao;
import rw.viden.volcanoproject.ticketing.model.Price;
import rw.viden.volcanoproject.ticketing.service.PriceHistorsService;

import java.util.List;

/**
 * Created by Viden ltd on 7/5/2016.
 */
@Service
@Transactional
public class PriceHistorsServiceImpl implements PriceHistorsService {
    @Autowired
    PriceHistorsDao priceHistorsDao;
    @Override
    public void saveOrUpdate(Price priceHistors) {priceHistorsDao.save(priceHistors);

    }

    @Override
    public void disable(Price priceHistors) {

    }

    @Override
    public List<Price> getAll() {
        return null;
    }

    @Override
    public Price getById(Integer id) {
        return priceHistorsDao.findOne(id);
    }
}
