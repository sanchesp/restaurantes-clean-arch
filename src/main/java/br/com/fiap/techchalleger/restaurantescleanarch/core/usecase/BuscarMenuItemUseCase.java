package br.com.fiap.techchalleger.restaurantescleanarch.core.usecase;

import br.com.fiap.techchalleger.restaurantescleanarch.core.domain.MenuItem;

public interface BuscarMenuItemUseCase {

    MenuItem buscarPorId(Long id);

}

