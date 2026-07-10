package br.com.fiap.techchalleger.restaurantescleanarch.core.usecase;

import br.com.fiap.techchalleger.restaurantescleanarch.core.domain.Restaurante;
import br.com.fiap.techchalleger.restaurantescleanarch.core.domain.Usuario;
import br.com.fiap.techchalleger.restaurantescleanarch.core.gateway.RestauranteGateway;
import br.com.fiap.techchalleger.restaurantescleanarch.core.gateway.UsuarioGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("CriarRestauranteUseCaseImpl")
class CriarRestauranteUseCaseImplTest {

    private CriarRestauranteUseCaseImpl useCase;

    @Mock
    private RestauranteGateway restauranteGateway;

    @Mock
    private UsuarioGateway usuarioGateway;

    @BeforeEach
    void setUp() {
        useCase = new CriarRestauranteUseCaseImpl(restauranteGateway, usuarioGateway);
    }

    @Test
    @DisplayName("Deve criar restaurante com sucesso")
    void testCriarRestauranteComSucesso() {
        // Arrange
        Restaurante restaurante = new Restaurante(
                null,
                "Pizzaria do João",
                "Rua A, 123",
                "Italiana",
                "11:00-23:00",
                1L
        );
        Usuario usuario = new Usuario(
                1L,
                "João Silva",
                "joao@email.com",
                "senha123",
                2L // DONO_RESTAURANTE
        );

        when(usuarioGateway.buscarPorId(1L)).thenReturn(usuario);
        when(restauranteGateway.criarRestaurante(restaurante)).thenReturn("1");

        // Act
        String resultado = useCase.criarRestaurante(restaurante);

        // Assert
        assertNotNull(resultado);
        assertEquals("1", resultado);
        verify(usuarioGateway, times(1)).buscarPorId(1L);
        verify(restauranteGateway, times(1)).criarRestaurante(restaurante);
    }

    @Test
    @DisplayName("Deve lançar exceção quando usuário não existe")
    void testCriarRestauranteUsuarioNaoExistente() {
        // Arrange
        Restaurante restaurante = new Restaurante(
                null,
                "Pizzaria do João",
                "Rua A, 123",
                "Italiana",
                "11:00-23:00",
                99L
        );

        when(usuarioGateway.buscarPorId(99L)).thenReturn(null);

        // Act & Assert
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> useCase.criarRestaurante(restaurante)
        );

        assertEquals("Usuário proprietário não encontrado com o ID: 99", exception.getMessage());
        verify(usuarioGateway, times(1)).buscarPorId(99L);
        verify(restauranteGateway, never()).criarRestaurante(any(Restaurante.class));
    }

    @Test
    @DisplayName("Deve lançar exceção quando usuário não é DONO_RESTAURANTE")
    void testCriarRestauranteUsuarioNaoEhDono() {
        // Arrange
        Restaurante restaurante = new Restaurante(
                null,
                "Pizzaria do João",
                "Rua A, 123",
                "Italiana",
                "11:00-23:00",
                1L
        );
        Usuario usuario = new Usuario(
                1L,
                "João Silva",
                "joao@email.com",
                "senha123",
                1L // CLIENTE
        );

        when(usuarioGateway.buscarPorId(1L)).thenReturn(usuario);

        // Act & Assert
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> useCase.criarRestaurante(restaurante)
        );

        assertEquals(
                "O usuário deve ser do tipo DONO_RESTAURANTE para criar um restaurante.",
                exception.getMessage()
        );
        verify(usuarioGateway, times(1)).buscarPorId(1L);
        verify(restauranteGateway, never()).criarRestaurante(any(Restaurante.class));
    }
}

