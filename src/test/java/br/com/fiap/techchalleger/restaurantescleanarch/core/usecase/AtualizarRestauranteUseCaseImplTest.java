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
@DisplayName("AtualizarRestauranteUseCaseImpl")
class AtualizarRestauranteUseCaseImplTest {

    private AtualizarRestauranteUseCaseImpl useCase;

    @Mock
    private RestauranteGateway restauranteGateway;

    @BeforeEach
    void setUp() {
        useCase = new AtualizarRestauranteUseCaseImpl(restauranteGateway);
    }

    @Test
    @DisplayName("Deve atualizar restaurante com sucesso")
    void testAtualizarRestauranteComSucesso() {
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
        Restaurante restauranteAtualizado = new Restaurante(
                1L,
                "Pizzaria do João Premium",
                "Rua A, 123",
                "Italiana",
                "10:00-00:00",
                1L
        );

        when(restauranteGateway.buscarPorId(id)).thenReturn(restaurante);
        when(restauranteGateway.atualizarRestaurante(id, restauranteAtualizado))
                .thenReturn(restauranteAtualizado);

        // Act
        Restaurante resultado = useCase.atualizarRestaurante(id, restauranteAtualizado);

        // Assert
        assertNotNull(resultado);
        assertEquals("Pizzaria do João Premium", resultado.getNome());
        assertEquals("10:00-00:00", resultado.getHorarioFuncionamento());
        verify(restauranteGateway, times(1)).buscarPorId(id);
        verify(restauranteGateway, times(1)).atualizarRestaurante(id, restauranteAtualizado);
    }

    @Test
    @DisplayName("Deve lançar exceção quando restaurante não existe")
    void testAtualizarRestauranteNaoExistente() {
        // Arrange
        Long id = 99L;
        Restaurante restaurante = new Restaurante(
                1L,
                "Pizzaria do João",
                "Rua A, 123",
                "Italiana",
                "11:00-23:00",
                1L
        );

        when(restauranteGateway.buscarPorId(id)).thenReturn(null);

        // Act & Assert
        EntidadeNaoEncontradaException exception = assertThrows(
                EntidadeNaoEncontradaException.class,
                () -> useCase.atualizarRestaurante(id, restaurante)
        );

        assertEquals("Restaurante não encontrado com o ID: " + id, exception.getMessage());
        verify(restauranteGateway, times(1)).buscarPorId(id);
        verify(restauranteGateway, never()).atualizarRestaurante(anyLong(), any(Restaurante.class));
    }
}

