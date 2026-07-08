package br.com.fiap.techchalleger.restaurantescleanarch.core.controller;

import br.com.fiap.techchalleger.restaurantescleanarch.core.domain.MenuItem;
import br.com.fiap.techchalleger.restaurantescleanarch.core.dto.CriarMenuItemDto;
import br.com.fiap.techchalleger.restaurantescleanarch.core.dto.AtualizarMenuItemDto;
import br.com.fiap.techchalleger.restaurantescleanarch.core.mapper.MenuItemMapper;
import br.com.fiap.techchalleger.restaurantescleanarch.core.usecase.CriarMenuItemUseCase;
import br.com.fiap.techchalleger.restaurantescleanarch.core.usecase.ListarMenuItemsUseCase;
import br.com.fiap.techchalleger.restaurantescleanarch.core.usecase.BuscarMenuItemUseCase;
import br.com.fiap.techchalleger.restaurantescleanarch.core.usecase.AtualizarMenuItemUseCase;
import br.com.fiap.techchalleger.restaurantescleanarch.core.usecase.DeletarMenuItemUseCase;
import br.com.fiap.techchalleger.restaurantescleanarch.core.gateway.MenuItemGateway;

import java.util.List;

public class MenuItemController {

    private final CriarMenuItemUseCase criarMenuItemUseCase;
    private final ListarMenuItemsUseCase listarMenuItemsUseCase;
    private final BuscarMenuItemUseCase buscarMenuItemUseCase;
    private final AtualizarMenuItemUseCase atualizarMenuItemUseCase;
    private final DeletarMenuItemUseCase deletarMenuItemUseCase;
    private final MenuItemMapper menuItemMapper;
    private final MenuItemGateway menuItemGateway;

    public MenuItemController(CriarMenuItemUseCase criarMenuItemUseCase,
                            ListarMenuItemsUseCase listarMenuItemsUseCase,
                            BuscarMenuItemUseCase buscarMenuItemUseCase,
                            AtualizarMenuItemUseCase atualizarMenuItemUseCase,
                            DeletarMenuItemUseCase deletarMenuItemUseCase,
                            MenuItemMapper menuItemMapper,
                            MenuItemGateway menuItemGateway) {
        this.criarMenuItemUseCase = criarMenuItemUseCase;
        this.listarMenuItemsUseCase = listarMenuItemsUseCase;
        this.buscarMenuItemUseCase = buscarMenuItemUseCase;
        this.atualizarMenuItemUseCase = atualizarMenuItemUseCase;
        this.deletarMenuItemUseCase = deletarMenuItemUseCase;
        this.menuItemMapper = menuItemMapper;
        this.menuItemGateway = menuItemGateway;
    }

    public String criarMenuItem(CriarMenuItemDto dto) {
        MenuItem menuItem = menuItemMapper.map(dto);
        return criarMenuItemUseCase.criarMenuItem(menuItem);
    }

    public List<MenuItem> listarMenuItems() {
        return listarMenuItemsUseCase.listarMenuItems();
    }

    public List<MenuItem> listarPorRestaurante(Long restauranteId) {
        return listarMenuItemsUseCase.listarPorRestaurante(restauranteId);
    }

    public MenuItem buscarPorId(Long id) {
        return buscarMenuItemUseCase.buscarPorId(id);
    }

    public MenuItem atualizarMenuItem(Long id, AtualizarMenuItemDto dto) {
        MenuItem menuItemExistente = menuItemGateway.buscarPorId(id);
        MenuItem menuItem = menuItemMapper.map(id, dto, menuItemExistente.getRestauranteId());
        return atualizarMenuItemUseCase.atualizarMenuItem(id, menuItem);
    }

    public void deletarMenuItem(Long id) {
        deletarMenuItemUseCase.deletarMenuItem(id);
    }

}

