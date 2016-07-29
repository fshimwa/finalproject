package rw.viden.volcanoproject.ticketing.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import rw.viden.volcanoproject.ticketing.dao.UsersDao;
import rw.viden.volcanoproject.ticketing.model.Users;
import rw.viden.volcanoproject.ticketing.service.UserService;

import java.util.List;
import java.util.Optional;

/**
 * Created by Viden ltd on 17/05/2016.
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    UsersDao usersDao;

    @Override
    public void saveOrUpdate(Users users) {
        String pass = users.getPassword();
        BCryptPasswordEncoder b = new BCryptPasswordEncoder();
        users.setPassword(b.encode(pass));
        usersDao.save(users);
    }
    @Override
    public void disable(Users users) {
        Users us=usersDao.findOne(users.getId());
        us.setEnabled(false);
        usersDao.save(us);
    }

    @Override
    public List<Users> getAll() {
        return usersDao.findAll();
    }

    @Override
    public Optional<Users> getByUsername(String username) {
        return usersDao.findByUsername(username);
    }

    @Override
    public Users getById(Integer idUser) {
        return usersDao.findOne(idUser);
    }
}
