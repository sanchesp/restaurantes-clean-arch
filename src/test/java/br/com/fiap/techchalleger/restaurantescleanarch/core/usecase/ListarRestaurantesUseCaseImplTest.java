package br.com.fiap.techchalleger.restaurantescleanarch.core.usecase;

import br.com.fiap.techchalleger.restaurantescleanarch.core.domain.Restaurante;
import br.com.fiap.techchalleger.restaurantescleanarch.core.gateway.RestauranteGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("ListarRestaurantesUseCaseImpl")
class ListarRestaurantesUseCaseImplTest {

    private ListarRestaurantesUseCaseImpl useCase;

    @Mock
    private RestauranteGateway restauranteGateway;

    @BeforeEach
    void setUp() {
        useCase = new ListarRestaurantesUseCaseImpl(restauranteGateway);
    }

    @Test
    @DisplayName("Deve listar todos os restaurantes com sucesso")
    void testListarRestaurantesComSucesso() {
        // Arrange
        List<Restaurante> restaurantes = List.of(
                new Restaurante(1L, "Pizzaria do João", "Rua A, 123", "Italiana", "11:00-23:00", 1L),
                new Restaurante(2L, "Churrascaria do Pedro", "Rua B, 456", "Brasileira", "12:00-00:00", 2L)
        );

        when(restauranteGateway.listarRestaurantes()).thenReturn(restaurantes);

        // Act
        List<Restaurante> resultado = useCase.listarRestaurantes();

        // Assert
        assertNotNull(resultado);
        assertEquals(2, resultado.size());
        assertEquals("Pizzaria do João", resultado.get(0).getNome());
        assertEquals("Churrascaria do Pedro", resultado.get(1).getNome());
        verify(restauranteGateway, times(1)).listarRestaurantes();
    }

    @Test
    @DisplayName("Deve retornar lista vazia quando não há restaurantes")
    void testListarRestaurantesVazio() {
        // Arrange
        when(restauranteGateway.listarRestaurantes()).thenReturn(new ArrayList<>());

        // Act
        List<Restaurante> resultado = useCase.listarRestaurantes();

        // Assert
        assertNotNull(resultado);
        assertEquals(0, resultado.size());
        verify(restauranteGateway, times(1)).listarRestaurantes();
    }

    @Test
    @DisplayName("Deve listar um único restaurante")
    void testListarRestaurantesUnico() {
        // Arrange
        List<Restaurante> restaurantes = List.of(
                new Restaurante(1L, "Pizzaria do João", "Rua A, 123", "Italiana", "11:00-23:00", 1L)
        );

        when(restauranteGateway.listarRestaurantes()).thenReturn(restaurantes);

        // Act
        List<Restaurante> resultado = useCase.listarRestaurantes();

        // Assert
        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals("Pizzaria do João", resultado.get(0).getNome());
        verify(restauranteGateway, times(1)).listarRestaurantes();
    }
}

