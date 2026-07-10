package br.com.fiap.techchalleger.restaurantescleanarch.core.usecase;

import br.com.fiap.techchalleger.restaurantescleanarch.core.domain.MenuItem;
import br.com.fiap.techchalleger.restaurantescleanarch.core.gateway.MenuItemGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("BuscarMenuItemUseCaseImpl")
class BuscarMenuItemUseCaseImplTest {

    private BuscarMenuItemUseCaseImpl useCase;

    @Mock
    private MenuItemGateway menuItemGateway;

    @BeforeEach
    void setUp() {
        useCase = new BuscarMenuItemUseCaseImpl(menuItemGateway);
    }

    @Test
    @DisplayName("Deve buscar item do menu por ID com sucesso")
    void testBuscarMenuItemComSucesso() {
        // Arrange
        Long id = 1L;
        MenuItem menuItem = new MenuItem(
                1L,
                "Hambúrguer",
                "Hambúrguer gourmet",
                29.90,
                true,
                "/fotos/hamburguer.jpg",
                1L
        );

        when(menuItemGateway.buscarPorId(id)).thenReturn(menuItem);

        // Act
        MenuItem resultado = useCase.buscarPorId(id);

        // Assert
        assertNotNull(resultado);
        assertEquals(1L, resultado.getId());
        assertEquals("Hambúrguer", resultado.getNome());
        verify(menuItemGateway, times(1)).buscarPorId(id);
    }

    @Test
    @DisplayName("Deve lançar exceção quando item não é encontrado")
    void testBuscarMenuItemNaoEncontrado() {
        // Arrange
        Long id = 99L;
        when(menuItemGateway.buscarPorId(id)).thenReturn(null);

        // Act & Assert
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> useCase.buscarPorId(id)
        );

        assertEquals("Item do menu não encontrado com o ID: " + id, exception.getMessage());
        verify(menuItemGateway, times(1)).buscarPorId(id);
    }
}

