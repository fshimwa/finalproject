package rw.viden.volcanoproject.ticketing.config;

        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.context.ApplicationListener;
        import org.springframework.context.event.ContextRefreshedEvent;
        import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
        import org.springframework.stereotype.Component;
        import org.springframework.transaction.annotation.Transactional;
        import rw.viden.volcanoproject.ticketing.model.Role;
        import rw.viden.volcanoproject.ticketing.model.Users;
        import rw.viden.volcanoproject.ticketing.service.UserService;

        import java.util.Collection;
        import java.util.Date;

/**
 * Created by Viden ltd on 6/20/2016.
 */
@Component
public class SetupDataLoader implements ApplicationListener<ContextRefreshedEvent> {

    private boolean alreadySetup = false;

    @Autowired
    private UserService userService;




//    @Autowired
//    private PasswordEncoder passwordEncoder;

    // API

    @Override
    @Transactional
    public void onApplicationEvent(final ContextRefreshedEvent event) {
        if (!isAlreadySetup()) {
            return;
        }


        createRoleIfNotFound("Admin");

        final Role adminRole = Role.ADMIN;
        final Users user = new Users();
        user.setUsername("admin");
        String pass = "demo";
        BCryptPasswordEncoder b = new BCryptPasswordEncoder();
        user.setPassword(b.encode(pass));
        user.setSavedDate(new Date());
        user.setFirstName("firstaName");
        user.setLastName("lastName");
        user.setEnabled(true);
        user.setRole(adminRole);
        userService.saveOrUpdate(user);

        alreadySetup = true;
    }



    @Transactional
    private final Role createRoleIfNotFound(final String name) {
        return Role.valueOf(name);

    }

    @Transactional
    private boolean isAlreadySetup() {
        Collection<Users> users= userService.getAll();
        return users.isEmpty();
    }

}

