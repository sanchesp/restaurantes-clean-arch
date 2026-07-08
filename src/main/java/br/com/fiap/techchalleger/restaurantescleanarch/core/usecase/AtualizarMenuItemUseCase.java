package br.com.fiap.techchalleger.restaurantescleanarch.core.usecase;

import br.com.fiap.techchalleger.restaurantescleanarch.core.domain.MenuItem;

public interface AtualizarMenuItemUseCase {

    MenuItem atualizarMenuItem(Long id, MenuItem menuItem);

}

