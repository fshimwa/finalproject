package rw.viden.volcanoproject.ticketing.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import rw.viden.volcanoproject.ticketing.model.CurrentUser;
import rw.viden.volcanoproject.ticketing.model.Users;
import rw.viden.volcanoproject.ticketing.service.UserService;

/**
 * Created by Viden ltd on 01/06/2016.
 */
@Service
public class CurrentUserServiceImpl implements UserDetailsService {

    private UserService userService;

    @Autowired
    public CurrentUserServiceImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public CurrentUser loadUserByUsername(String s) throws UsernameNotFoundException {
        Users users = userService.getByUsername(s).orElseThrow(() -> new UsernameNotFoundException(String.format("Username not found ", s)));
        return new CurrentUser(users);
    }
}
