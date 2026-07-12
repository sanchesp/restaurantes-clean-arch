package br.com.fiap.techchalleger.restaurantescleanarch.core.usecase;

import br.com.fiap.techchalleger.restaurantescleanarch.core.exception.EntidadeNaoEncontradaException;
import br.com.fiap.techchalleger.restaurantescleanarch.core.gateway.MenuItemGateway;

public class DeletarMenuItemUseCaseImpl implements DeletarMenuItemUseCase {

    private final MenuItemGateway menuItemGateway;

    public DeletarMenuItemUseCaseImpl(MenuItemGateway menuItemGateway) {
        this.menuItemGateway = menuItemGateway;
    }

    @Override
    public void deletarMenuItem(Long id) {
        validarMenuItemExiste(id);
        menuItemGateway.deletarMenuItem(id);
    }

    private void validarMenuItemExiste(Long id) {
        var menuItem = menuItemGateway.buscarPorId(id);
        if (menuItem == null) {
            throw new EntidadeNaoEncontradaException("Item do menu não encontrado com o ID: " + id);
        }
    }

}

