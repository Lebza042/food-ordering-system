package food_ordering_system;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private MenuRepository menuRepository;

    @Override
    public void run(String... args) {
        if (menuRepository.count() > 0) return; // don't seed if data exists

        // Categories
        Category burgers = categoryRepository.findByName("Burgers")
                .orElseGet(() -> categoryRepository.save(new Category(null, "Burgers")));
        Category pizza = categoryRepository.findByName("Pizza")
                .orElseGet(() -> categoryRepository.save(new Category(null, "Pizza")));
        Category drinks = categoryRepository.findByName("Drinks")
                .orElseGet(() -> categoryRepository.save(new Category(null, "Drinks")));

        // Burgers
        menuRepository.save(new Menu(null, "Classic Burger", "Beef patty with lettuce", new BigDecimal("49.99"), "https://placehold.co/300", burgers));
        menuRepository.save(new Menu(null, "Cheese Burger", "Beef patty with cheese", new BigDecimal("54.99"), "https://placehold.co/300", burgers));
        menuRepository.save(new Menu(null, "Veggie Burger", "Plant based patty", new BigDecimal("44.99"), "https://placehold.co/300", burgers));
        menuRepository.save(new Menu(null, "Double Burger", "Double beef patty", new BigDecimal("69.99"), "https://placehold.co/300", burgers));

        // Pizza
        menuRepository.save(new Menu(null, "Margherita Pizza", "Tomato and mozzarella", new BigDecimal("89.99"), "https://placehold.co/300", pizza));
        menuRepository.save(new Menu(null, "Pepperoni Pizza", "Pepperoni and cheese", new BigDecimal("99.99"), "https://placehold.co/300", pizza));
        menuRepository.save(new Menu(null, "BBQ Chicken Pizza", "BBQ sauce and chicken", new BigDecimal("109.99"), "https://placehold.co/300", pizza));
        menuRepository.save(new Menu(null, "Veggie Pizza", "Mixed vegetables", new BigDecimal("84.99"), "https://placehold.co/300", pizza));

        // Drinks
        menuRepository.save(new Menu(null, "Coke 500ml", "Ice cold coke", new BigDecimal("15.00"), "https://placehold.co/300", drinks));
        menuRepository.save(new Menu(null, "Sprite 500ml", "Ice cold sprite", new BigDecimal("15.00"), "https://placehold.co/300", drinks));
        menuRepository.save(new Menu(null, "Water 500ml", "Still water", new BigDecimal("10.00"), "https://placehold.co/300", drinks));
        menuRepository.save(new Menu(null, "Orange Juice", "Fresh squeezed", new BigDecimal("25.00"), "https://placehold.co/300", drinks));
    }
}