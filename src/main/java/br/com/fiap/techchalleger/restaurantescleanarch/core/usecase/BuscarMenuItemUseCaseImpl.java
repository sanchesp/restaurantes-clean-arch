package br.com.fiap.techchalleger.restaurantescleanarch.core.usecase;

import br.com.fiap.techchalleger.restaurantescleanarch.core.domain.MenuItem;
import br.com.fiap.techchalleger.restaurantescleanarch.core.exception.EntidadeNaoEncontradaException;
import br.com.fiap.techchalleger.restaurantescleanarch.core.gateway.MenuItemGateway;

public class BuscarMenuItemUseCaseImpl implements BuscarMenuItemUseCase {

    private final MenuItemGateway menuItemGateway;

    public BuscarMenuItemUseCaseImpl(MenuItemGateway menuItemGateway) {
        this.menuItemGateway = menuItemGateway;
    }

    @Override
    public MenuItem buscarPorId(Long id) {
        MenuItem menuItem = menuItemGateway.buscarPorId(id);
        if (menuItem == null) {
            throw new EntidadeNaoEncontradaException("Item do menu não encontrado com o ID: " + id);
        }
        return menuItem;
    }

}

