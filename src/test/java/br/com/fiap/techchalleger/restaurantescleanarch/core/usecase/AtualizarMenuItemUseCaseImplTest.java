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
@DisplayName("AtualizarMenuItemUseCaseImpl")
class AtualizarMenuItemUseCaseImplTest {

    private AtualizarMenuItemUseCaseImpl useCase;

    @Mock
    private MenuItemGateway menuItemGateway;

    @BeforeEach
    void setUp() {
        useCase = new AtualizarMenuItemUseCaseImpl(menuItemGateway);
    }

    @Test
    @DisplayName("Deve atualizar item do menu com sucesso")
    void testAtualizarMenuItemComSucesso() {
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
        MenuItem menuItemAtualizado = new MenuItem(
                1L,
                "Hambúrguer Premium",
                "Hambúrguer gourmet premium",
                35.90,
                true,
                "/fotos/hamburguer_premium.jpg",
                1L
        );

        when(menuItemGateway.buscarPorId(id)).thenReturn(menuItem);
        when(menuItemGateway.atualizarMenuItem(id, menuItemAtualizado)).thenReturn(menuItemAtualizado);

        // Act
        MenuItem resultado = useCase.atualizarMenuItem(id, menuItemAtualizado);

        // Assert
        assertNotNull(resultado);
        assertEquals("Hambúrguer Premium", resultado.getNome());
        assertEquals(35.90, resultado.getPreco());
        verify(menuItemGateway, times(1)).buscarPorId(id);
        verify(menuItemGateway, times(1)).atualizarMenuItem(id, menuItemAtualizado);
    }

    @Test
    @DisplayName("Deve lançar exceção quando item não existe")
    void testAtualizarMenuItemNaoExistente() {
        // Arrange
        Long id = 99L;
        MenuItem menuItem = new MenuItem(
                1L,
                "Hambúrguer",
                "Hambúrguer gourmet",
                29.90,
                true,
                "/fotos/hamburguer.jpg",
                1L
        );

        when(menuItemGateway.buscarPorId(id)).thenReturn(null);

        // Act & Assert
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> useCase.atualizarMenuItem(id, menuItem)
        );

        assertEquals("Item do menu não encontrado com o ID: " + id, exception.getMessage());
        verify(menuItemGateway, times(1)).buscarPorId(id);
        verify(menuItemGateway, never()).atualizarMenuItem(anyLong(), any(MenuItem.class));
    }
}

