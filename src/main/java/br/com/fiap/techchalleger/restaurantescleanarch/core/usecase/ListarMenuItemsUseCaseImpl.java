package br.com.fiap.techchalleger.restaurantescleanarch.core.usecase;

import br.com.fiap.techchalleger.restaurantescleanarch.core.domain.MenuItem;
import br.com.fiap.techchalleger.restaurantescleanarch.core.gateway.MenuItemGateway;
import java.util.List;

public class ListarMenuItemsUseCaseImpl implements ListarMenuItemsUseCase {

    private final MenuItemGateway menuItemGateway;

    public ListarMenuItemsUseCaseImpl(MenuItemGateway menuItemGateway) {
        this.menuItemGateway = menuItemGateway;
    }

    @Override
    public List<MenuItem> listarMenuItems() {
        return menuItemGateway.listarMenuItems();
    }

    @Override
    public List<MenuItem> listarPorRestaurante(Long restauranteId) {
        return menuItemGateway.listarPorRestaurante(restauranteId);
    }

}

