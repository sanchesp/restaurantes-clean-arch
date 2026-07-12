package br.com.fiap.techchalleger.restaurantescleanarch.core.usecase;

import br.com.fiap.techchalleger.restaurantescleanarch.core.domain.Usuario;
import br.com.fiap.techchalleger.restaurantescleanarch.core.dto.AtualizarUsuarioTipoDto;
import br.com.fiap.techchalleger.restaurantescleanarch.core.exception.EntidadeNaoEncontradaException;
import br.com.fiap.techchalleger.restaurantescleanarch.core.gateway.UsuarioGateway;
import br.com.fiap.techchalleger.restaurantescleanarch.core.mapper.UsuarioMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
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

    @Mock
    private UsuarioMapper usuarioMapper;

    @BeforeEach
    void setUp() {
        useCase = new AtualizarUsuarioUseCaseImpl(
                usuarioGateway,
                usuarioMapper);
    }

    @Test
    @DisplayName("Deve atualizar usuário com sucesso")
    void testAtualizarUsuarioComSucesso() {

        Long id = 1L;

        AtualizarUsuarioTipoDto dto =
                new AtualizarUsuarioTipoDto(2L);

        Usuario usuarioExistente = new Usuario(
                id,
                "João Silva",
                "joao@email.com",
                "senha123",
                1L
        );

        Usuario usuarioAtualizado = new Usuario(
                id,
                "João Silva",
                "joao@email.com",
                "senha123",
                2L
        );

        when(usuarioGateway.buscarPorId(id))
                .thenReturn(usuarioExistente);

        when(usuarioMapper.map(
                usuarioExistente.getId(),
                usuarioExistente.getNome(),
                usuarioExistente.getEmail(),
                usuarioExistente.getSenha(),
                dto))
                .thenReturn(usuarioAtualizado);

        when(usuarioGateway.atualizarUsuarioTipo(id, usuarioAtualizado))
                .thenReturn(usuarioAtualizado);

        Usuario resultado = useCase.atualizarUsuarioTipo(id, dto);

        assertNotNull(resultado);
        assertEquals(2L, resultado.getUsuarioTipoId());

        verify(usuarioGateway).buscarPorId(id);

        verify(usuarioMapper).map(
                usuarioExistente.getId(),
                usuarioExistente.getNome(),
                usuarioExistente.getEmail(),
                usuarioExistente.getSenha(),
                dto);

        verify(usuarioGateway).atualizarUsuarioTipo(id, usuarioAtualizado);
    }

    @Test
    @DisplayName("Deve lançar exceção quando usuário não existe")
    void testAtualizarUsuarioNaoExistente() {

        Long id = 99L;

        AtualizarUsuarioTipoDto dto =
                new AtualizarUsuarioTipoDto(2L);

        when(usuarioGateway.buscarPorId(id))
                .thenReturn(null);

        EntidadeNaoEncontradaException exception =
                assertThrows(
                        EntidadeNaoEncontradaException.class,
                        () -> useCase.atualizarUsuarioTipo(id, dto));

        assertEquals(
                "Usuário não encontrado com o ID: " + id,
                exception.getMessage());

        verify(usuarioGateway).buscarPorId(id);

        verify(usuarioMapper, never()).map(
                anyLong(),
                anyString(),
                anyString(),
                anyString(),
                any(AtualizarUsuarioTipoDto.class));

        verify(usuarioGateway, never())
                .atualizarUsuarioTipo(anyLong(), any(Usuario.class));
    }
}