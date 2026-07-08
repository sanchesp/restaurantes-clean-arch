package br.com.fiap.techchalleger.restaurantescleanarch.core.gateway;

import br.com.fiap.techchalleger.restaurantescleanarch.core.domain.MenuItem;
import java.util.List;

public interface MenuItemGateway {

    String criarMenuItem(MenuItem menuItem);

    List<MenuItem> listarMenuItems();

    List<MenuItem> listarPorRestaurante(Long restauranteId);

    MenuItem buscarPorId(Long id);

    MenuItem atualizarMenuItem(Long id, MenuItem menuItem);

    void deletarMenuItem(Long id);

}

