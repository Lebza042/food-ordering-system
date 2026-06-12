package food_ordering_system;

// Custom exception thrown when a category is not found
public class CategoryNotFoundException extends RuntimeException {
    public CategoryNotFoundException(String message) {
        super(message);
    }
}