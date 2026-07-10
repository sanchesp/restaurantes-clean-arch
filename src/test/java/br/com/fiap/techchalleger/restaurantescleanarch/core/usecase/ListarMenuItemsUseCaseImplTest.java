package br.com.fiap.techchalleger.restaurantescleanarch.core.usecase;

import br.com.fiap.techchalleger.restaurantescleanarch.core.domain.MenuItem;
import br.com.fiap.techchalleger.restaurantescleanarch.core.gateway.MenuItemGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("ListarMenuItemsUseCaseImpl")
class ListarMenuItemsUseCaseImplTest {

    private ListarMenuItemsUseCaseImpl useCase;

    @Mock
    private MenuItemGateway menuItemGateway;

    @BeforeEach
    void setUp() {
        useCase = new ListarMenuItemsUseCaseImpl(menuItemGateway);
    }

    @Test
    @DisplayName("Deve listar todos os itens do menu com sucesso")
    void testListarMenuItemsComSucesso() {
        // Arrange
        List<MenuItem> menuItems = Arrays.asList(
                new MenuItem(1L, "Hambúrguer", "Hambúrguer gourmet", 29.90, true, "/fotos/hamburguer.jpg", 1L),
                new MenuItem(2L, "Pizza", "Pizza mozzarela", 45.90, true, "/fotos/pizza.jpg", 1L)
        );

        when(menuItemGateway.listarMenuItems()).thenReturn(menuItems);

        // Act
        List<MenuItem> resultado = useCase.listarMenuItems();

        // Assert
        assertNotNull(resultado);
        assertEquals(2, resultado.size());
        assertEquals("Hambúrguer", resultado.get(0).getNome());
        assertEquals("Pizza", resultado.get(1).getNome());
        verify(menuItemGateway, times(1)).listarMenuItems();
    }

    @Test
    @DisplayName("Deve retornar lista vazia quando não há itens")
    void testListarMenuItemsVazio() {
        // Arrange
        when(menuItemGateway.listarMenuItems()).thenReturn(new ArrayList<>());

        // Act
        List<MenuItem> resultado = useCase.listarMenuItems();

        // Assert
        assertNotNull(resultado);
        assertEquals(0, resultado.size());
        verify(menuItemGateway, times(1)).listarMenuItems();
    }

    @Test
    @DisplayName("Deve listar itens por restaurante com sucesso")
    void testListarPorRestauranteComSucesso() {
        // Arrange
        Long restauranteId = 1L;
        List<MenuItem> menuItems = Arrays.asList(
                new MenuItem(1L, "Hambúrguer", "Hambúrguer gourmet", 29.90, true, "/fotos/hamburguer.jpg", 1L),
                new MenuItem(2L, "Fritas", "Fritas crocantes", 15.90, true, "/fotos/fritas.jpg", 1L)
        );

        when(menuItemGateway.listarPorRestaurante(restauranteId)).thenReturn(menuItems);

        // Act
        List<MenuItem> resultado = useCase.listarPorRestaurante(restauranteId);

        // Assert
        assertNotNull(resultado);
        assertEquals(2, resultado.size());
        verify(menuItemGateway, times(1)).listarPorRestaurante(restauranteId);
    }

    @Test
    @DisplayName("Deve retornar lista vazia para restaurante sem itens")
    void testListarPorRestauranteVazio() {
        // Arrange
        Long restauranteId = 99L;
        when(menuItemGateway.listarPorRestaurante(restauranteId)).thenReturn(new ArrayList<>());

        // Act
        List<MenuItem> resultado = useCase.listarPorRestaurante(restauranteId);

        // Assert
        assertNotNull(resultado);
        assertEquals(0, resultado.size());
        verify(menuItemGateway, times(1)).listarPorRestaurante(restauranteId);
    }
}

