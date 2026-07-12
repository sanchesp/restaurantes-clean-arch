package br.com.fiap.techchalleger.restaurantescleanarch.core.usecase;

import br.com.fiap.techchalleger.restaurantescleanarch.core.domain.UsuarioTipo;
import br.com.fiap.techchalleger.restaurantescleanarch.core.exception.EntidadeJaExisteException;
import br.com.fiap.techchalleger.restaurantescleanarch.core.gateway.UsuarioTipoGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("CriarUsuarioTipoUseCaseImpl")
class CriarUsuarioTipoUseCaseImplTest {

    private CriarUsuarioTipoUseCaseImpl useCase;

    @Mock
    private UsuarioTipoGateway usuarioTipoGateway;

    @BeforeEach
    void setUp() {
        useCase = new CriarUsuarioTipoUseCaseImpl(usuarioTipoGateway);
    }

    @Test
    @DisplayName("Deve criar tipo de usuário com sucesso")
    void testCriarUsuarioTipoComSucesso() {
        // Arrange
        UsuarioTipo tipo = new UsuarioTipo("CLIENTE");

        when(usuarioTipoGateway.usuarioTipoJaExiste(tipo)).thenReturn(false);
        when(usuarioTipoGateway.criarUsuarioTipo(tipo)).thenReturn("1");

        // Act
        String resultado = useCase.criarUsuarioTipo(tipo);

        // Assert
        assertNotNull(resultado);
        assertEquals("1", resultado);
        verify(usuarioTipoGateway, times(1)).usuarioTipoJaExiste(tipo);
        verify(usuarioTipoGateway, times(1)).criarUsuarioTipo(tipo);
    }

    @Test
    @DisplayName("Deve lançar exceção quando tipo de usuário já existe")
    void testCriarUsuarioTipoJaExistente() {
        // Arrange
        UsuarioTipo tipo = new UsuarioTipo("CLIENTE");

        when(usuarioTipoGateway.usuarioTipoJaExiste(tipo)).thenReturn(true);

        // Act & Assert
        EntidadeJaExisteException exception = assertThrows(
                EntidadeJaExisteException.class,
                () -> useCase.criarUsuarioTipo(tipo)
        );

        assertEquals("O tipo de usuário já existe.", exception.getMessage());
        verify(usuarioTipoGateway, times(1)).usuarioTipoJaExiste(tipo);
        verify(usuarioTipoGateway, never()).criarUsuarioTipo(any(UsuarioTipo.class));
    }

    @Test
    @DisplayName("Deve criar tipo DONO_RESTAURANTE com sucesso")
    void testCriarUsuarioTipoDonoComSucesso() {
        // Arrange
        UsuarioTipo tipo = new UsuarioTipo("DONO_RESTAURANTE");

        when(usuarioTipoGateway.usuarioTipoJaExiste(tipo)).thenReturn(false);
        when(usuarioTipoGateway.criarUsuarioTipo(tipo)).thenReturn("2");

        // Act
        String resultado = useCase.criarUsuarioTipo(tipo);

        // Assert
        assertNotNull(resultado);
        assertEquals("2", resultado);
        verify(usuarioTipoGateway, times(1)).usuarioTipoJaExiste(tipo);
        verify(usuarioTipoGateway, times(1)).criarUsuarioTipo(tipo);
    }
}

