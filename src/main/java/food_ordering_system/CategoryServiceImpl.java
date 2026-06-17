package food_ordering_system;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<CategoryDto> getAllCategories() {
        List<Category> categories = categoryRepository.findAll();
        List<CategoryDto> dtos = new ArrayList<>();
        for (Category c : categories) {
            CategoryDto dto = new CategoryDto();
            dto.setId(c.getId());
            dto.setName(c.getName());
            dtos.add(dto);
        }
        return dtos;
    }

    @Override
    public CategoryDto getCategoryById(Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new CategoryNotFoundException("Category not found with id: " + id));
        CategoryDto dto = new CategoryDto();
        dto.setId(category.getId());
        dto.setName(category.getName());
        return dto;
    }

    @Override
    public CategoryDto addCategory(CategoryDto dto) {
        Category category = new Category();
        category.setName(dto.getName());
        Category saved = categoryRepository.save(category);
        CategoryDto result = new CategoryDto();
        result.setId(saved.getId());
        result.setName(saved.getName());
        return result;
    }

    @Override
    public CategoryDto updateCategory(Long id, CategoryDto dto) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new CategoryNotFoundException("Category not found with id: " + id));
        category.setName(dto.getName());
        Category updated = categoryRepository.save(category);
        CategoryDto result = new CategoryDto();
        result.setId(updated.getId());
        result.setName(updated.getName());
        return result;
    }

    @Override
    public void deleteCategory(Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new CategoryNotFoundException("Category not found with id: " + id));
        categoryRepository.delete(category);
    }
}