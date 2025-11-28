package ma.casablanca.ensam.jeeproject.configuration;

import ma.casablanca.ensam.jeeproject.dao.entities.User;
import ma.casablanca.ensam.jeeproject.dao.repositories.UserRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DefaultUserLoader implements ApplicationRunner {
    private final UserRepository userRepo;
    private final PasswordEncoder passwordEncoder;

    public DefaultUserLoader(UserRepository userRepo, PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(ApplicationArguments args) {
        userRepo.deleteAll();
        User admin = new User();
        admin.setEmail("admin@example.com");
        admin.setPassword(passwordEncoder.encode("adminPassword"));
        admin.setUsername("admin");
        admin.setRole("ROLE_ADMIN");
        userRepo.save(admin);
        System.out.println("Default admin user created: admin@example.com / adminPassword");
    }
}
