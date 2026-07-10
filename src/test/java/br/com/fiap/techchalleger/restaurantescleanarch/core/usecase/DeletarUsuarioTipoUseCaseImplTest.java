package br.com.fiap.techchalleger.restaurantescleanarch.core.usecase;

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
@DisplayName("DeletarUsuarioTipoUseCaseImpl")
class DeletarUsuarioTipoUseCaseImplTest {

    private DeletarUsuarioTipoUseCaseImpl useCase;

    @Mock
    private UsuarioTipoGateway usuarioTipoGateway;

    @BeforeEach
    void setUp() {
        useCase = new DeletarUsuarioTipoUseCaseImpl(usuarioTipoGateway);
    }

    @Test
    @DisplayName("Deve deletar tipo de usuário com sucesso")
    void testDeletarUsuarioTipoComSucesso() {
        // Arrange
        Long id = 1L;
        doNothing().when(usuarioTipoGateway).deletarUsuarioTipo(id);

        // Act
        assertDoesNotThrow(() -> useCase.deletarUsuarioTipo(id));

        // Assert
        verify(usuarioTipoGateway, times(1)).deletarUsuarioTipo(id);
    }

    @Test
    @DisplayName("Deve deletar tipo de usuário sem validações")
    void testDeletarUsuarioTipoSemValidacoes() {
        // Arrange
        Long id = 2L;
        doNothing().when(usuarioTipoGateway).deletarUsuarioTipo(id);

        // Act
        assertDoesNotThrow(() -> useCase.deletarUsuarioTipo(id));

        // Assert
        verify(usuarioTipoGateway, times(1)).deletarUsuarioTipo(id);
    }
}

