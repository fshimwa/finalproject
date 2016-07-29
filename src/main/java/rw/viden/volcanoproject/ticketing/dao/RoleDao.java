package rw.viden.volcanoproject.ticketing.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import rw.viden.volcanoproject.ticketing.model.Role;

/**
 * Created by Viden ltd on 24/05/2016.
 */
public interface RoleDao extends JpaRepository<Role, Integer> {
//    List<Role> findByUserRole(String userRole);
}
