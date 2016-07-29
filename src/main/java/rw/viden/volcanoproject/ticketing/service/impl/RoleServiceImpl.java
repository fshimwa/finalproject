package rw.viden.volcanoproject.ticketing.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import rw.viden.volcanoproject.ticketing.dao.RoleDao;
import rw.viden.volcanoproject.ticketing.model.Role;
import rw.viden.volcanoproject.ticketing.service.RoleService;

import java.util.List;

/**
 * Created by Viden ltd on 24/05/2016.
 */
@Service
@Transactional
public class RoleServiceImpl implements RoleService {
    @Autowired
    RoleDao roleDao;
    @Override
    public void saveOrUpdate(Role role) {roleDao.save(role);}

    @Override
    public void disable(Role role) {
    }

    @Override
    public List<Role> getAll() {
        return roleDao.findAll();
    }

    @Override
    public List<Role> getByUserRole(String userRole) {
        return null;//roleDao.findByUserRole(userRole);
    }

    @Override
    public Role getById(Integer idRole) {
        return roleDao.findOne(idRole);
    }
}
