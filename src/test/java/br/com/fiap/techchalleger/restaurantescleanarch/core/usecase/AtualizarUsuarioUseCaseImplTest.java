package br.com.fiap.techchalleger.restaurantescleanarch.core.usecase;

import br.com.fiap.techchalleger.restaurantescleanarch.core.domain.Usuario;
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
@DisplayName("AtualizarUsuarioUseCaseImpl")
class AtualizarUsuarioUseCaseImplTest {

    private AtualizarUsuarioUseCaseImpl useCase;

    @Mock
    private UsuarioGateway usuarioGateway;

    @BeforeEach
    void setUp() {
        useCase = new AtualizarUsuarioUseCaseImpl(usuarioGateway);
    }

    @Test
    @DisplayName("Deve atualizar usuário com sucesso")
    void testAtualizarUsuarioComSucesso() {
        // Arrange
        Long id = 1L;
        Usuario usuario = new Usuario(
                1L,
                "João Silva",
                "joao@email.com",
                "senha123",
                1L
        );
        Usuario usuarioAtualizado = new Usuario(
                1L,
                "João Silva Atualizado",
                "joao.novo@email.com",
                "novaSenha123",
                1L
        );

        when(usuarioGateway.buscarPorId(id)).thenReturn(usuario);
        when(usuarioGateway.atualizarUsuarioTipo(id, usuarioAtualizado))
                .thenReturn(usuarioAtualizado);

        // Act
        Usuario resultado = useCase.atualizarUsuarioTipo(id, usuarioAtualizado);

        // Assert
        assertNotNull(resultado);
        assertEquals("João Silva Atualizado", resultado.getNome());
        assertEquals("joao.novo@email.com", resultado.getEmail());
        verify(usuarioGateway, times(1)).buscarPorId(id);
        verify(usuarioGateway, times(1)).atualizarUsuarioTipo(id, usuarioAtualizado);
    }

    @Test
    @DisplayName("Deve lançar exceção quando usuário não existe")
    void testAtualizarUsuarioNaoExistente() {
        // Arrange
        Long id = 99L;
        Usuario usuario = new Usuario(
                1L,
                "João Silva",
                "joao@email.com",
                "senha123",
                1L
        );

        when(usuarioGateway.buscarPorId(id)).thenReturn(null);

        // Act & Assert
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> useCase.atualizarUsuarioTipo(id, usuario)
        );

        assertEquals("Usuário não encontrado com o ID: " + id, exception.getMessage());
        verify(usuarioGateway, times(1)).buscarPorId(id);
        verify(usuarioGateway, never()).atualizarUsuarioTipo(anyLong(), any(Usuario.class));
    }
}

