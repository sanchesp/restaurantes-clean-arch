package br.com.fiap.techchalleger.restaurantescleanarch.core.domain;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.jupiter.params.provider.NullSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class MenuItemTest {

    @Test
    void deveCriarMenuItemERetornarAtributos() {
        MenuItem item = new MenuItem(1L, "Pizza", "Deliciosa", 25.0, true, "/img/pizza.jpg", 2L);

        assertEquals(1L, item.getId());
        assertEquals("Pizza", item.getNome());
        assertEquals("Deliciosa", item.getDescricao());
        assertEquals(25.0, item.getPreco());
        assertEquals(true, item.getDisponivel());
        assertEquals("/img/pizza.jpg", item.getCaminhoFoto());
        assertEquals(2L, item.getRestauranteId());
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {" "})
    void deveLancarErroQuandoNomeInvalido(String nomeInvalido) {
        assertThrows(IllegalArgumentException.class,
                () -> new MenuItem(1L, nomeInvalido, "desc", 1.0, true, "/p", 1L));
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {" "})
    void deveLancarErroQuandoDescricaoInvalida(String descInvalida) {
        assertThrows(IllegalArgumentException.class,
                () -> new MenuItem(1L, "nome", descInvalida, 1.0, true, "/p", 1L));
    }

    @Test
    void deveLancarErroQuandoPrecoForNulo() {
        assertThrows(IllegalArgumentException.class,
                () -> new MenuItem(1L, "nome", "desc", null, true, "/p", 1L));
    }

    @ParameterizedTest
    @ValueSource(doubles = {0.0, -1.0})
    void deveLancarErroQuandoPrecoInvalido(double precoInvalido) {
        assertThrows(IllegalArgumentException.class,
                () -> new MenuItem(1L, "nome", "desc", precoInvalido, true, "/p", 1L));
    }

    @ParameterizedTest
    @NullSource
    void deveLancarErroQuandoDisponivelForNulo(Boolean disponivel) {
        assertThrows(IllegalArgumentException.class,
                () -> new MenuItem(1L, "nome", "desc", 1.0, disponivel, "/p", 1L));
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {" "})
    void deveLancarErroQuandoCaminhoFotoInvalido(String caminhoInvalido) {
        assertThrows(IllegalArgumentException.class,
                () -> new MenuItem(1L, "nome", "desc", 1.0, true, caminhoInvalido, 1L));
    }

    @Test
    void deveLancarErroQuandoRestauranteIdForNulo() {
        assertThrows(IllegalArgumentException.class,
                () -> new MenuItem(1L, "nome", "desc", 1.0, true, "/p", null));
    }

}

