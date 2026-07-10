package br.com.fiap.techchalleger.restaurantescleanarch.core.domain;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class RestauranteTest {

    @Test
    void deveCriarRestauranteERetornarAtributos() {
        Restaurante r = new Restaurante(1L, "Bela Bistrô", "Rua A, 123", "Italiana", "09:00-22:00", 5L);

        assertEquals(1L, r.getId());
        assertEquals("Bela Bistrô", r.getNome());
        assertEquals("Rua A, 123", r.getEndereco());
        assertEquals("Italiana", r.getTipoCozinha());
        assertEquals("09:00-22:00", r.getHorarioFuncionamento());
        assertEquals(5L, r.getDonoId());
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {" "})
    void deveLancarErroQuandoNomeInvalido(String nomeInvalido) {
        assertThrows(IllegalArgumentException.class,
                () -> new Restaurante(1L, nomeInvalido, "end", "tipo", "hor", 1L));
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {" "})
    void deveLancarErroQuandoEnderecoInvalido(String enderecoInvalido) {
        assertThrows(IllegalArgumentException.class,
                () -> new Restaurante(1L, "nome", enderecoInvalido, "tipo", "hor", 1L));
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {" "})
    void deveLancarErroQuandoTipoCozinhaInvalido(String tipoInvalido) {
        assertThrows(IllegalArgumentException.class,
                () -> new Restaurante(1L, "nome", "end", tipoInvalido, "hor", 1L));
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {" "})
    void deveLancarErroQuandoHorarioInvalido(String horarioInvalido) {
        assertThrows(IllegalArgumentException.class,
                () -> new Restaurante(1L, "nome", "end", "tipo", horarioInvalido, 1L));
    }

    @Test
    void deveLancarErroQuandoDonoIdForNulo() {
        assertThrows(IllegalArgumentException.class,
                () -> new Restaurante(1L, "nome", "end", "tipo", "hor", null));
    }

}

