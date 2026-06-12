package food_ordering_system;

import org.springframework.data.jpa.repository.JpaRepository;

// Repository handles all database operations for Category
public interface CategoryRepository extends JpaRepository<Category, Long> {
}