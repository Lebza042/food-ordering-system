package food_ordering_system;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

// Controller handles HTTP requests and returns wrapped Response objects
@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    // GET all categories
    @GetMapping
    public ResponseEntity<Response<List<CategoryDto>>> getAllCategories() {
        List<CategoryDto> categories = categoryService.getAllCategories();
        return ResponseEntity.ok(Response.success("Categories retrieved", categories));
    }

    // GET category by id
    @GetMapping("/{id}")
    public ResponseEntity<Response<CategoryDto>> getCategoryById(@PathVariable Long id) {
        CategoryDto dto = categoryService.getCategoryById(id);
        return ResponseEntity.ok(Response.success("Category retrieved", dto));
    }

    // POST create new category
    @PostMapping
    public ResponseEntity<Response<CategoryDto>> addCategory(@RequestBody @Valid CategoryDto dto) {
        CategoryDto created = categoryService.addCategory(dto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(Response.success("Category created", created));
    }

    // PUT update category
    @PutMapping("/{id}")
    public ResponseEntity<Response<CategoryDto>> updateCategory(
            @PathVariable Long id,
            @RequestBody @Valid CategoryDto dto) {
        CategoryDto updated = categoryService.updateCategory(id, dto);
        return ResponseEntity.ok(Response.success("Category updated", updated));
    }

    // DELETE category
    @DeleteMapping("/{id}")
    public ResponseEntity<Response<Void>> deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategory(id);
        return ResponseEntity.ok(Response.success("Category deleted", null));
    }
}