package rw.viden.volcanoproject.ticketing.service;

import rw.viden.volcanoproject.ticketing.model.Users;

import java.util.List;
import java.util.Optional;

/**
 * Created by Viden ltd on 17/05/2016.
 */

public interface UserService {
    void saveOrUpdate(Users users);
    void disable(Users users);
    List<Users> getAll();
    Optional<Users> getByUsername(String username);

    Users getById(Integer idUser);
}
