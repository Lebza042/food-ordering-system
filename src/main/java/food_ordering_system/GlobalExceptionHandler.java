package food_ordering_system;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CategoryNotFoundException.class)
    public ResponseEntity<Response<Void>> handleCategoryNotFound(CategoryNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Response.error(404, ex.getMessage()));
    }

    @ExceptionHandler(MenuNotFoundException.class)
    public ResponseEntity<Response<Void>> handleMenuNotFound(MenuNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Response.error(404, ex.getMessage()));
    }

    @ExceptionHandler(CategoryInUseException.class)
    public ResponseEntity<Response<Void>> handleCategoryInUse(CategoryInUseException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(Response.error(409, ex.getMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Response<Void>> handleValidation(MethodArgumentNotValidException ex) {
        String errors = ex.getBindingResult().getFieldErrors()
                .stream()
                .map(e -> e.getField() + ": " + e.getDefaultMessage())
                .collect(Collectors.joining(", "));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(Response.error(400, errors));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Response<Void>> handleGeneral(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(Response.error(500, "An unexpected error occurred"));
    }
}