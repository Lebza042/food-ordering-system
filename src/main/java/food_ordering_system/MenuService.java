package food_ordering_system;

import org.springframework.data.domain.Page;

public interface MenuService {
    Response<MenuDto> createMenu(MenuDto dto);
    Page<MenuDto> getAllMenus(Long categoryId, String search, int page, int size, String sort);
    Response<MenuDto> getMenuById(Long id);
    Response<MenuDto> updateMenu(Long id, MenuDto dto);
    Response<Void> deleteMenu(Long id);
}