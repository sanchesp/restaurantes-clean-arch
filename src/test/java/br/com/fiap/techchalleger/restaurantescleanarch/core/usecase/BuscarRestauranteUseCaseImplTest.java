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
@DisplayName("BuscarRestauranteUseCaseImpl")
class BuscarRestauranteUseCaseImplTest {

    private BuscarRestauranteUseCaseImpl useCase;

    @Mock
    private RestauranteGateway restauranteGateway;

    @BeforeEach
    void setUp() {
        useCase = new BuscarRestauranteUseCaseImpl(restauranteGateway);
    }

    @Test
    @DisplayName("Deve buscar restaurante por ID com sucesso")
    void testBuscarRestauranteComSucesso() {
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

        // Act
        Restaurante resultado = useCase.buscarPorId(id);

        // Assert
        assertNotNull(resultado);
        assertEquals(1L, resultado.getId());
        assertEquals("Pizzaria do João", resultado.getNome());
        verify(restauranteGateway, times(1)).buscarPorId(id);
    }

    @Test
    @DisplayName("Deve lançar exceção quando restaurante não é encontrado")
    void testBuscarRestauranteNaoEncontrado() {
        // Arrange
        Long id = 99L;
        when(restauranteGateway.buscarPorId(id)).thenReturn(null);

        // Act & Assert
        EntidadeNaoEncontradaException exception = assertThrows(
                EntidadeNaoEncontradaException.class,
                () -> useCase.buscarPorId(id)
        );

        assertEquals("Restaurante não encontrado com o ID: " + id, exception.getMessage());
        verify(restauranteGateway, times(1)).buscarPorId(id);
    }
}

