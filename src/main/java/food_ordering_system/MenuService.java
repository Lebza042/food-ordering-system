package food_ordering_system;

import java.util.List;

// Service interface defining menu business logic contract
public interface MenuService {
    Response<MenuDto> createMenu(MenuDto dto);
    Response<List<MenuDto>> getAllMenus();
    Response<MenuDto> getMenuById(Long id);
}