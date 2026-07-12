package br.com.fiap.techchalleger.restaurantescleanarch.core.usecase;

import br.com.fiap.techchalleger.restaurantescleanarch.core.domain.MenuItem;
import br.com.fiap.techchalleger.restaurantescleanarch.core.exception.EntidadeNaoEncontradaException;
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
@DisplayName("DeletarMenuItemUseCaseImpl")
class DeletarMenuItemUseCaseImplTest {

    private DeletarMenuItemUseCaseImpl useCase;

    @Mock
    private MenuItemGateway menuItemGateway;

    @BeforeEach
    void setUp() {
        useCase = new DeletarMenuItemUseCaseImpl(menuItemGateway);
    }

    @Test
    @DisplayName("Deve deletar item do menu com sucesso")
    void testDeletarMenuItemComSucesso() {
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
        doNothing().when(menuItemGateway).deletarMenuItem(id);

        // Act
        assertDoesNotThrow(() -> useCase.deletarMenuItem(id));

        // Assert
        verify(menuItemGateway, times(1)).buscarPorId(id);
        verify(menuItemGateway, times(1)).deletarMenuItem(id);
    }

    @Test
    @DisplayName("Deve lançar exceção quando item não existe")
    void testDeletarMenuItemNaoExistente() {
        // Arrange
        Long id = 99L;
        when(menuItemGateway.buscarPorId(id)).thenReturn(null);

        // Act & Assert
        EntidadeNaoEncontradaException exception = assertThrows(
                EntidadeNaoEncontradaException.class,
                () -> useCase.deletarMenuItem(id)
        );

        assertEquals("Item do menu não encontrado com o ID: " + id, exception.getMessage());
        verify(menuItemGateway, times(1)).buscarPorId(id);
        verify(menuItemGateway, never()).deletarMenuItem(anyLong());
    }
}

