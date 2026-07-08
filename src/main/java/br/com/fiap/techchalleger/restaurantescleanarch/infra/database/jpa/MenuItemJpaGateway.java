package br.com.fiap.techchalleger.restaurantescleanarch.infra.database.jpa;

import br.com.fiap.techchalleger.restaurantescleanarch.core.domain.MenuItem;
import br.com.fiap.techchalleger.restaurantescleanarch.core.gateway.MenuItemGateway;
import br.com.fiap.techchalleger.restaurantescleanarch.infra.database.jpa.entity.MenuItemEntity;
import br.com.fiap.techchalleger.restaurantescleanarch.infra.database.jpa.repository.MenuItemRepository;
import br.com.fiap.techchalleger.restaurantescleanarch.infra.database.mapper.MenuItemEntityMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class MenuItemJpaGateway implements MenuItemGateway {

    private final MenuItemRepository menuItemRepository;
    private final MenuItemEntityMapper menuItemEntityMapper;

    public MenuItemJpaGateway(MenuItemRepository menuItemRepository,
                            MenuItemEntityMapper menuItemEntityMapper) {
        this.menuItemRepository = menuItemRepository;
        this.menuItemEntityMapper = menuItemEntityMapper;
    }

    @Override
    public String criarMenuItem(MenuItem menuItem) {
        try {
            MenuItemEntity entity = menuItemEntityMapper.toEntity(menuItem);
            MenuItemEntity saved = menuItemRepository.save(entity);
            return "Item do menu criado com sucesso. ID: " + saved.getId();
        } catch (Exception e) {
            throw new RuntimeException("Erro ao criar item do menu", e);
        }
    }

    @Override
    public List<MenuItem> listarMenuItems() {
        try {
            return menuItemRepository.findAll()
                    .stream()
                    .map(menuItemEntityMapper::toDomain)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new RuntimeException("Erro ao listar itens do menu", e);
        }
    }

    @Override
    public List<MenuItem> listarPorRestaurante(Long restauranteId) {
        try {
            return menuItemRepository.findByRestauranteId(restauranteId)
                    .stream()
                    .map(menuItemEntityMapper::toDomain)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new RuntimeException("Erro ao listar itens do menu por restaurante", e);
        }
    }

    @Override
    public MenuItem buscarPorId(Long id) {
        try {
            Optional<MenuItemEntity> optional = menuItemRepository.findById(id);
            if (optional.isEmpty()) {
                return null;
            }
            return menuItemEntityMapper.toDomain(optional.get());
        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar item do menu por ID", e);
        }
    }

    @Override
    public MenuItem atualizarMenuItem(Long id, MenuItem menuItem) {
        try {
            Optional<MenuItemEntity> optional = menuItemRepository.findById(id);
            if (optional.isEmpty()) {
                throw new RuntimeException("Item do menu não encontrado: " + id);
            }

            MenuItemEntity entity = optional.get();
            entity.setNome(menuItem.getNome());
            entity.setDescricao(menuItem.getDescricao());
            entity.setPreco(menuItem.getPreco());
            entity.setDisponivel(menuItem.getDisponivel());
            entity.setCaminhoFoto(menuItem.getCaminhoFoto());

            MenuItemEntity saved = menuItemRepository.save(entity);

            return menuItemEntityMapper.toDomain(saved);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao atualizar item do menu", e);
        }
    }

    @Override
    public void deletarMenuItem(Long id) {
        try {
            Optional<MenuItemEntity> optional = menuItemRepository.findById(id);
            if (optional.isEmpty()) {
                throw new RuntimeException("Item do menu não encontrado: " + id);
            }
            menuItemRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao deletar item do menu", e);
        }
    }

}

