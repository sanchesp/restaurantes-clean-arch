package br.com.fiap.techchalleger.restaurantescleanarch.core.usecase;

import br.com.fiap.techchalleger.restaurantescleanarch.core.domain.MenuItem;
import br.com.fiap.techchalleger.restaurantescleanarch.core.exception.EntidadeNaoEncontradaException;
import br.com.fiap.techchalleger.restaurantescleanarch.core.gateway.MenuItemGateway;
import br.com.fiap.techchalleger.restaurantescleanarch.core.gateway.RestauranteGateway;

public class CriarMenuItemUseCaseImpl implements CriarMenuItemUseCase {

    private final MenuItemGateway menuItemGateway;
    private final RestauranteGateway restauranteGateway;

    public CriarMenuItemUseCaseImpl(MenuItemGateway menuItemGateway, RestauranteGateway restauranteGateway) {
        this.menuItemGateway = menuItemGateway;
        this.restauranteGateway = restauranteGateway;
    }

    @Override
    public String criarMenuItem(MenuItem menuItem) {
        validarRestaurante(menuItem.getRestauranteId());
        return menuItemGateway.criarMenuItem(menuItem);
    }

    private void validarRestaurante(Long restauranteId) {
        var restaurante = restauranteGateway.buscarPorId(restauranteId);
        if (restaurante == null) {
            throw new EntidadeNaoEncontradaException("Restaurante não encontrado com o ID: " + restauranteId);
        }
    }

}

