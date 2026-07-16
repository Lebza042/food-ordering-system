package food_ordering_system.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

// Seeds required roles on application startup so they always exist
@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public void run(String... args) {
        seedRole("ADMIN");
        seedRole("CUSTOMER");
    }

    private void seedRole(String roleName) {
        boolean exists = roleRepository.findByName(roleName).isPresent();
        if (!exists) {
            Role role = new Role(roleName);
            roleRepository.save(role);
            System.out.println("Seeded role: " + roleName);
        }
    }
}