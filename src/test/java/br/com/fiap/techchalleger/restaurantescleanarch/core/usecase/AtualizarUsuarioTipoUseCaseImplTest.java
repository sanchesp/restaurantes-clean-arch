package br.com.fiap.techchalleger.restaurantescleanarch.core.usecase;

import br.com.fiap.techchalleger.restaurantescleanarch.core.domain.UsuarioTipo;
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
@DisplayName("AtualizarUsuarioTipoUseCaseImpl")
class AtualizarUsuarioTipoUseCaseImplTest {

    private AtualizarUsuarioTipoUseCaseImpl useCase;

    @Mock
    private UsuarioTipoGateway usuarioTipoGateway;

    @BeforeEach
    void setUp() {
        useCase = new AtualizarUsuarioTipoUseCaseImpl(usuarioTipoGateway);
    }

    @Test
    @DisplayName("Deve atualizar tipo de usuário com sucesso")
    void testAtualizarUsuarioTipoComSucesso() {
        // Arrange
        Long id = 1L;
        UsuarioTipo tipoAtualizado = new UsuarioTipo(1L, "DONO_RESTAURANTE");

        when(usuarioTipoGateway.usuarioTipoJaExiste(tipoAtualizado)).thenReturn(false);
        when(usuarioTipoGateway.atualizarUsuarioTipo(id, tipoAtualizado))
                .thenReturn(tipoAtualizado);

        // Act
        UsuarioTipo resultado = useCase.atualizarUsuarioTipo(id, tipoAtualizado);

        // Assert
        assertNotNull(resultado);
        assertEquals("DONO_RESTAURANTE", resultado.getTipo());
        verify(usuarioTipoGateway, times(1)).usuarioTipoJaExiste(tipoAtualizado);
        verify(usuarioTipoGateway, times(1)).atualizarUsuarioTipo(id, tipoAtualizado);
    }

    @Test
    @DisplayName("Deve lançar exceção quando tipo de usuário já existe")
    void testAtualizarUsuarioTipoJaExistente() {
        // Arrange
        Long id = 1L;
        UsuarioTipo tipo = new UsuarioTipo(1L, "CLIENTE");

        when(usuarioTipoGateway.usuarioTipoJaExiste(tipo)).thenReturn(true);

        // Act & Assert
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> useCase.atualizarUsuarioTipo(id, tipo)
        );

        assertEquals("O tipo de usuário já existe.", exception.getMessage());
        verify(usuarioTipoGateway, times(1)).usuarioTipoJaExiste(tipo);
        verify(usuarioTipoGateway, never()).atualizarUsuarioTipo(anyLong(), any(UsuarioTipo.class));
    }
}

