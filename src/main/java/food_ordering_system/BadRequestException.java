package food_ordering_system;

public class BadRequestException extends RuntimeException {
    public BadRequestException(String message) {
        super(message);
    }
}