package food_ordering_system;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

// Controller handling HTTP requests for menu endpoints
@RestController
@RequestMapping("/api/menu")
public class MenuController {

    @Autowired
    private MenuService menuService;

    @PostMapping
    public ResponseEntity<Response<MenuDto>> create(@RequestBody @Valid MenuDto dto) {
        return ResponseEntity.ok(menuService.createMenu(dto));
    }

    @GetMapping
    public ResponseEntity<Page<MenuDto>> all(
            @RequestParam(required = false) Long categoryId,
            @RequestParam(required = false) String search,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) String sort) {
        return ResponseEntity.ok(menuService.getAllMenus(categoryId, search, page, size, sort));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response<MenuDto>> byId(@PathVariable Long id) {
        return ResponseEntity.ok(menuService.getMenuById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Response<MenuDto>> update(
            @PathVariable Long id,
            @RequestBody @Valid MenuDto dto) {
        return ResponseEntity.ok(menuService.updateMenu(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response<Void>> delete(@PathVariable Long id) {
        return ResponseEntity.ok(menuService.deleteMenu(id));
    }
}