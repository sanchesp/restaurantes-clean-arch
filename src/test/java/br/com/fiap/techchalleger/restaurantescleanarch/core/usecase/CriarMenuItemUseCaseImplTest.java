package br.com.fiap.techchalleger.restaurantescleanarch.core.usecase;

import br.com.fiap.techchalleger.restaurantescleanarch.core.domain.MenuItem;
import br.com.fiap.techchalleger.restaurantescleanarch.core.domain.Restaurante;
import br.com.fiap.techchalleger.restaurantescleanarch.core.gateway.MenuItemGateway;
import br.com.fiap.techchalleger.restaurantescleanarch.core.gateway.RestauranteGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("CriarMenuItemUseCaseImpl")
class CriarMenuItemUseCaseImplTest {

    private CriarMenuItemUseCaseImpl useCase;

    @Mock
    private MenuItemGateway menuItemGateway;

    @Mock
    private RestauranteGateway restauranteGateway;

    @BeforeEach
    void setUp() {
        useCase = new CriarMenuItemUseCaseImpl(menuItemGateway, restauranteGateway);
    }

    @Test
    @DisplayName("Deve criar item do menu com sucesso")
    void testCriarMenuItemComSucesso() {
        // Arrange
        MenuItem menuItem = new MenuItem(
                null,
                "Hambúrguer",
                "Hambúrguer gourmet",
                29.90,
                true,
                "/fotos/hamburguer.jpg",
                1L
        );
        Restaurante restaurante = new Restaurante(
                1L,
                "Pizzaria do João",
                "Rua A, 123",
                "Italiana",
                "11:00-23:00",
                1L
        );

        when(restauranteGateway.buscarPorId(1L)).thenReturn(restaurante);
        when(menuItemGateway.criarMenuItem(menuItem)).thenReturn("1");

        // Act
        String resultado = useCase.criarMenuItem(menuItem);

        // Assert
        assertNotNull(resultado);
        assertEquals("1", resultado);
        verify(restauranteGateway, times(1)).buscarPorId(1L);
        verify(menuItemGateway, times(1)).criarMenuItem(menuItem);
    }

    @Test
    @DisplayName("Deve lançar exceção quando restaurante não existe")
    void testCriarMenuItemRestauranteNaoExistente() {
        // Arrange
        MenuItem menuItem = new MenuItem(
                null,
                "Hambúrguer",
                "Hambúrguer gourmet",
                29.90,
                true,
                "/fotos/hamburguer.jpg",
                99L
        );

        when(restauranteGateway.buscarPorId(99L)).thenReturn(null);

        // Act & Assert
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> useCase.criarMenuItem(menuItem)
        );

        assertEquals("Restaurante não encontrado com o ID: 99", exception.getMessage());
        verify(restauranteGateway, times(1)).buscarPorId(99L);
        verify(menuItemGateway, never()).criarMenuItem(any(MenuItem.class));
    }
}

