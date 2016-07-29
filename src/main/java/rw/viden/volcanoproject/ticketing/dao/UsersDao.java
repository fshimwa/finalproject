package rw.viden.volcanoproject.ticketing.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rw.viden.volcanoproject.ticketing.model.Users;

import java.util.List;
import java.util.Optional;

/**
 * Created by Viden ltd on 17/05/2016.
 */
@Repository
public interface UsersDao extends JpaRepository<Users, Integer> {

    Optional<Users> findByUsername(String username);

}
