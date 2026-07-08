package br.com.fiap.techchalleger.restaurantescleanarch.infra.web;

import br.com.fiap.techchalleger.restaurantescleanarch.core.controller.MenuItemController;
import br.com.fiap.techchalleger.restaurantescleanarch.core.domain.MenuItem;
import br.com.fiap.techchalleger.restaurantescleanarch.core.dto.CriarMenuItemDto;
import br.com.fiap.techchalleger.restaurantescleanarch.core.dto.AtualizarMenuItemDto;
import br.com.fiap.techchalleger.restaurantescleanarch.infra.web.json.MenuItemJson;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/menu-items")
public class MenuItemApiController {

    private final MenuItemController menuItemController;

    public MenuItemApiController(MenuItemController menuItemController) {
        this.menuItemController = menuItemController;
    }

    @PostMapping
    public String criar(@RequestBody MenuItemJson menuItemJson) {
        return menuItemController.criarMenuItem(mapToCriarDto(menuItemJson));
    }

    @GetMapping
    public List<MenuItemJson> listar() {
        List<MenuItem> lista = menuItemController.listarMenuItems();
        return lista.stream()
                .map(this::mapToJson)
                .collect(Collectors.toList());
    }

    @GetMapping("/restaurante/{restauranteId}")
    public List<MenuItemJson> listarPorRestaurante(@PathVariable Long restauranteId) {
        List<MenuItem> lista = menuItemController.listarPorRestaurante(restauranteId);
        return lista.stream()
                .map(this::mapToJson)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public MenuItemJson buscarPorId(@PathVariable Long id) {
        MenuItem menuItem = menuItemController.buscarPorId(id);
        return mapToJson(menuItem);
    }

    @PutMapping("/{id}")
    public MenuItemJson atualizar(@PathVariable Long id, @RequestBody MenuItemJson menuItemJson) {
        MenuItem updated = menuItemController.atualizarMenuItem(id, mapToAtualizarDto(menuItemJson));
        return mapToJson(updated);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        menuItemController.deletarMenuItem(id);
    }

    private CriarMenuItemDto mapToCriarDto(MenuItemJson json) {
        return new CriarMenuItemDto(
            json.getNome(),
            json.getDescricao(),
            json.getPreco(),
            json.getDisponivel(),
            json.getCaminhoFoto(),
            json.getRestauranteId()
        );
    }

    private AtualizarMenuItemDto mapToAtualizarDto(MenuItemJson json) {
        return new AtualizarMenuItemDto(
            json.getNome(),
            json.getDescricao(),
            json.getPreco(),
            json.getDisponivel(),
            json.getCaminhoFoto()
        );
    }

    private MenuItemJson mapToJson(MenuItem menuItem) {
        return new MenuItemJson(
            menuItem.getId(),
            menuItem.getNome(),
            menuItem.getDescricao(),
            menuItem.getPreco(),
            menuItem.getDisponivel(),
            menuItem.getCaminhoFoto(),
            menuItem.getRestauranteId()
        );
    }

}

