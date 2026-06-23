package food_ordering_system;

import org.springframework.data.jpa.repository.JpaRepository;

// Repository for Menu database operations
public interface MenuRepository extends JpaRepository<Menu, Long> {
}