package rw.viden.volcanoproject.ticketing.service;

import org.springframework.transaction.annotation.Transactional;
import rw.viden.volcanoproject.ticketing.model.Role;

import java.util.List;

/**
 * Created by Viden ltd on 24/05/2016.
 */
@Transactional
public interface RoleService {
    void saveOrUpdate(Role role);
    void disable(Role role);
    List<Role> getAll();
    List<Role> getByUserRole(String userRole);

    Role getById(Integer idRole);
}
