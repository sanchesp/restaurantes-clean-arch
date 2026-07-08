package br.com.fiap.techchalleger.restaurantescleanarch.core.mapper;

import br.com.fiap.techchalleger.restaurantescleanarch.core.domain.MenuItem;
import br.com.fiap.techchalleger.restaurantescleanarch.core.dto.CriarMenuItemDto;
import br.com.fiap.techchalleger.restaurantescleanarch.core.dto.AtualizarMenuItemDto;

public class MenuItemMapper {

    public MenuItem map(CriarMenuItemDto dto) {
        return new MenuItem(null, dto.getNome(), dto.getDescricao(), dto.getPreco(), dto.getDisponivel(), dto.getCaminhoFoto(), dto.getRestauranteId());
    }

    public MenuItem map(Long id, AtualizarMenuItemDto dto, Long restauranteId) {
        return new MenuItem(id, dto.getNome(), dto.getDescricao(), dto.getPreco(), dto.getDisponivel(), dto.getCaminhoFoto(), restauranteId);
    }

}

