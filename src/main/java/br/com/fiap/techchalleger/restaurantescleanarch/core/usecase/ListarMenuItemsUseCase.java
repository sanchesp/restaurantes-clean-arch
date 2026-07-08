package br.com.fiap.techchalleger.restaurantescleanarch.core.usecase;

import br.com.fiap.techchalleger.restaurantescleanarch.core.domain.MenuItem;
import java.util.List;

public interface ListarMenuItemsUseCase {

    List<MenuItem> listarMenuItems();

    List<MenuItem> listarPorRestaurante(Long restauranteId);

}

