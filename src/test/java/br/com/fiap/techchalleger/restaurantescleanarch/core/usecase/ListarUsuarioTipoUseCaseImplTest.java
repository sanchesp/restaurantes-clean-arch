package br.com.fiap.techchalleger.restaurantescleanarch.core.usecase;

import br.com.fiap.techchalleger.restaurantescleanarch.core.domain.UsuarioTipo;
import br.com.fiap.techchalleger.restaurantescleanarch.core.gateway.UsuarioTipoGateway;
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
@DisplayName("ListarUsuarioTipoUseCaseImpl")
class ListarUsuarioTipoUseCaseImplTest {

    private ListarUsuarioTipoUseCaseImpl useCase;

    @Mock
    private UsuarioTipoGateway usuarioTipoGateway;

    @BeforeEach
    void setUp() {
        useCase = new ListarUsuarioTipoUseCaseImpl(usuarioTipoGateway);
    }

    @Test
    @DisplayName("Deve listar todos os tipos de usuário com sucesso")
    void testListarUsuarioTipoComSucesso() {
        // Arrange
        List<UsuarioTipo> tipos = List.of(
                new UsuarioTipo(1L, "CLIENTE"),
                new UsuarioTipo(2L, "DONO_RESTAURANTE")
        );

        when(usuarioTipoGateway.listarUsuarioTipo()).thenReturn(tipos);

        // Act
        List<UsuarioTipo> resultado = useCase.listarUsuarioTipo();

        // Assert
        assertNotNull(resultado);
        assertEquals(2, resultado.size());
        assertEquals("CLIENTE", resultado.get(0).getTipo());
        assertEquals("DONO_RESTAURANTE", resultado.get(1).getTipo());
        verify(usuarioTipoGateway, times(1)).listarUsuarioTipo();
    }

    @Test
    @DisplayName("Deve retornar lista vazia quando não há tipos de usuário")
    void testListarUsuarioTipoVazio() {
        // Arrange
        when(usuarioTipoGateway.listarUsuarioTipo()).thenReturn(new ArrayList<>());

        // Act
        List<UsuarioTipo> resultado = useCase.listarUsuarioTipo();

        // Assert
        assertNotNull(resultado);
        assertEquals(0, resultado.size());
        verify(usuarioTipoGateway, times(1)).listarUsuarioTipo();
    }

    @Test
    @DisplayName("Deve listar um único tipo de usuário")
    void testListarUsuarioTipoUnico() {
        // Arrange
        List<UsuarioTipo> tipos = List.of(
                new UsuarioTipo(1L, "CLIENTE")
        );

        when(usuarioTipoGateway.listarUsuarioTipo()).thenReturn(tipos);

        // Act
        List<UsuarioTipo> resultado = useCase.listarUsuarioTipo();

        // Assert
        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals("CLIENTE", resultado.get(0).getTipo());
        verify(usuarioTipoGateway, times(1)).listarUsuarioTipo();
    }
}

