package food_ordering_system;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuRepository menuRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Response<MenuDto> createMenu(MenuDto dto) {
        Category category = categoryRepository.findById(dto.getCategoryId())
                .orElseThrow(() -> new CategoryNotFoundException(
                        "Category not found with id: " + dto.getCategoryId()));
        Menu menu = mapToEntity(dto, category);
        Menu saved = menuRepository.save(menu);
        return Response.success("Menu created", mapToDto(saved));
    }

    @Override
    public Page<MenuDto> getAllMenus(Long categoryId, String search, int page, int size, String sort) {
        Sort sortObj = Sort.unsorted();
        if (sort != null && !sort.isEmpty()) {
            String[] parts = sort.split(",");
            String field = parts[0];
            Sort.Direction direction = parts.length > 1 && parts[1].equalsIgnoreCase("desc")
                    ? Sort.Direction.DESC : Sort.Direction.ASC;
            sortObj = Sort.by(direction, field);
        }
        Pageable pageable = PageRequest.of(page, size, sortObj);
        return menuRepository.findByFilters(categoryId, search, pageable)
                .map(this::mapToDto);
    }

    @Override
    public Response<MenuDto> getMenuById(Long id) {
        Menu menu = menuRepository.findById(id)
                .orElseThrow(() -> new MenuNotFoundException(
                        "Menu not found with id: " + id));
        return Response.success("Menu retrieved", mapToDto(menu));
    }

    @Override
    public Response<MenuDto> updateMenu(Long id, MenuDto dto) {
        Menu menu = menuRepository.findById(id)
                .orElseThrow(() -> new MenuNotFoundException(
                        "Menu not found with id: " + id));
        Category category = categoryRepository.findById(dto.getCategoryId())
                .orElseThrow(() -> new CategoryNotFoundException(
                        "Category not found with id: " + dto.getCategoryId()));
        menu.setName(dto.getName());
        menu.setDescription(dto.getDescription());
        menu.setPrice(dto.getPrice());
        menu.setImageUrl(dto.getImageUrl());
        menu.setCategory(category);
        Menu updated = menuRepository.save(menu);
        return Response.success("Menu updated", mapToDto(updated));
    }

    @Override
    public Response<Void> deleteMenu(Long id) {
        Menu menu = menuRepository.findById(id)
                .orElseThrow(() -> new MenuNotFoundException(
                        "Menu not found with id: " + id));
        menuRepository.delete(menu);
        return Response.success("Menu deleted", null);
    }

    private MenuDto mapToDto(Menu menu) {
        MenuDto dto = new MenuDto();
        dto.setId(menu.getId());
        dto.setName(menu.getName());
        dto.setDescription(menu.getDescription());
        dto.setPrice(menu.getPrice());
        dto.setImageUrl(menu.getImageUrl());
        dto.setCategoryId(menu.getCategory().getId());
        dto.setCategoryName(menu.getCategory().getName());
        return dto;
    }

    private Menu mapToEntity(MenuDto dto, Category category) {
        Menu menu = new Menu();
        menu.setName(dto.getName());
        menu.setDescription(dto.getDescription());
        menu.setPrice(dto.getPrice());
        menu.setImageUrl(dto.getImageUrl());
        menu.setCategory(category);
        return menu;
    }
}