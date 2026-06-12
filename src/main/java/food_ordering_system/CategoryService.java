package food_ordering_system;

import java.util.List;

// Interface defining the business logic contract
public interface CategoryService {
    List<CategoryDto> getAllCategories();
}