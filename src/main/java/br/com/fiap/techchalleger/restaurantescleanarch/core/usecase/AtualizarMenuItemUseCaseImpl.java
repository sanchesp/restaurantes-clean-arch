package br.com.fiap.techchalleger.restaurantescleanarch.core.usecase;

import br.com.fiap.techchalleger.restaurantescleanarch.core.domain.MenuItem;
import br.com.fiap.techchalleger.restaurantescleanarch.core.gateway.MenuItemGateway;

public class AtualizarMenuItemUseCaseImpl implements AtualizarMenuItemUseCase {

    private final MenuItemGateway menuItemGateway;

    public AtualizarMenuItemUseCaseImpl(MenuItemGateway menuItemGateway) {
        this.menuItemGateway = menuItemGateway;
    }

    @Override
    public MenuItem atualizarMenuItem(Long id, MenuItem menuItem) {
        validarMenuItemExiste(id);
        return menuItemGateway.atualizarMenuItem(id, menuItem);
    }

    private void validarMenuItemExiste(Long id) {
        MenuItem menuItem = menuItemGateway.buscarPorId(id);
        if (menuItem == null) {
            throw new IllegalArgumentException("Item do menu não encontrado com o ID: " + id);
        }
    }

}

