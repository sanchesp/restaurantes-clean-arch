package br.com.fiap.techchalleger.restaurantescleanarch.infra.database.mapper;

import br.com.fiap.techchalleger.restaurantescleanarch.core.domain.MenuItem;
import br.com.fiap.techchalleger.restaurantescleanarch.infra.database.jpa.entity.MenuItemEntity;

public class MenuItemEntityMapper {

    public MenuItemEntity toEntity(MenuItem menuItem) {
        return new MenuItemEntity(
            menuItem.getId(),
            menuItem.getNome(),
            menuItem.getDescricao(),
            menuItem.getPreco(),
            menuItem.getDisponivel(),
            menuItem.getCaminhoFoto(),
            menuItem.getRestauranteId()
        );
    }

    public MenuItem toDomain(MenuItemEntity entity) {
        return new MenuItem(
            entity.getId(),
            entity.getNome(),
            entity.getDescricao(),
            entity.getPreco(),
            entity.getDisponivel(),
            entity.getCaminhoFoto(),
            entity.getRestauranteId()
        );
    }

}

