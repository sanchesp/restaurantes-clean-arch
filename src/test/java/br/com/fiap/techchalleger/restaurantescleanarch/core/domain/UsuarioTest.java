package br.com.fiap.techchalleger.restaurantescleanarch.core.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class UsuarioTest {

    @Test
    void deveCriarUsuarioERetornarAtributos() {
        Usuario usuario = new Usuario(1L, "Fulano", "fulano@example.com", "senha123", 2L);

        assertEquals(1L, usuario.getId());
        assertEquals("Fulano", usuario.getNome());
        assertEquals("fulano@example.com", usuario.getEmail());
        assertEquals("senha123", usuario.getSenha());
        assertEquals(2L, usuario.getUsuarioTipoId());
    }

    @Test
    void deveLancarErroQuandoUsuarioTipoIdForNulo() {
        assertThrows(IllegalArgumentException.class,
                () -> new Usuario(1L, "Fulano", "fulano@example.com", "senha123", null));
    }

}

