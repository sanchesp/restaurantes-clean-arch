package br.com.fiap.techchalleger.restaurantescleanarch.core.usecase;

import br.com.fiap.techchalleger.restaurantescleanarch.core.domain.Restaurante;
import br.com.fiap.techchalleger.restaurantescleanarch.core.exception.EntidadeNaoEncontradaException;
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
@DisplayName("DeletarRestauranteUseCaseImpl")
class DeletarRestauranteUseCaseImplTest {

    private DeletarRestauranteUseCaseImpl useCase;

    @Mock
    private RestauranteGateway restauranteGateway;

    @BeforeEach
    void setUp() {
        useCase = new DeletarRestauranteUseCaseImpl(restauranteGateway);
    }

    @Test
    @DisplayName("Deve deletar restaurante com sucesso")
    void testDeletarRestauranteComSucesso() {
        // Arrange
        Long id = 1L;
        Restaurante restaurante = new Restaurante(
                1L,
                "Pizzaria do João",
                "Rua A, 123",
                "Italiana",
                "11:00-23:00",
                1L
        );

        when(restauranteGateway.buscarPorId(id)).thenReturn(restaurante);
        doNothing().when(restauranteGateway).deletarRestaurante(id);

        // Act
        assertDoesNotThrow(() -> useCase.deletarRestaurante(id));

        // Assert
        verify(restauranteGateway, times(1)).buscarPorId(id);
        verify(restauranteGateway, times(1)).deletarRestaurante(id);
    }

    @Test
    @DisplayName("Deve lançar exceção quando restaurante não existe")
    void testDeletarRestauranteNaoExistente() {
        // Arrange
        Long id = 99L;
        when(restauranteGateway.buscarPorId(id)).thenReturn(null);

        // Act & Assert
        EntidadeNaoEncontradaException exception = assertThrows(
                EntidadeNaoEncontradaException.class,
                () -> useCase.deletarRestaurante(id)
        );

        assertEquals("Restaurante não encontrado com o ID: " + id, exception.getMessage());
        verify(restauranteGateway, times(1)).buscarPorId(id);
        verify(restauranteGateway, never()).deletarRestaurante(anyLong());
    }
}

