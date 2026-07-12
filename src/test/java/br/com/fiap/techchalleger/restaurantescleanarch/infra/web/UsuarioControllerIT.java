package br.com.fiap.techchalleger.restaurantescleanarch.infra.web;

import br.com.fiap.techchalleger.restaurantescleanarch.infra.database.jpa.entity.UsuarioEntity;
import br.com.fiap.techchalleger.restaurantescleanarch.infra.database.jpa.repository.UsuarioRepository;
import br.com.fiap.techchalleger.restaurantescleanarch.infra.web.json.UsuarioJson;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DisplayName("UsuarioController - Testes de Integração")
class UsuarioControllerIT {

    private static final Long TIPO_CLIENTE = 1L;
    private static final Long TIPO_DONO = 2L;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @BeforeEach
    void setup() {
        usuarioRepository.deleteAll();
    }

    @Test
    @DisplayName("Deve atualizar tipo do usuário")
    void deveAtualizarUsuarioTipo() {

        UsuarioEntity usuario = criarUsuario();

        UsuarioJson update =
                new UsuarioJson(
                        usuario.getId(),
                        TIPO_DONO);

        restTemplate.put(
                "/usuarios/{id}",
                update,
                usuario.getId());

        UsuarioEntity atualizado =
                usuarioRepository.findById(usuario.getId()).orElse(null);

        assertNotNull(atualizado);
        assertEquals(TIPO_DONO, atualizado.getUsuarioTipoId());
    }

    @Test
    @DisplayName("Deve retornar 404 ao atualizar usuário inexistente")
    void deveRetornar404AoAtualizarUsuarioInexistente() {

        UsuarioJson update =
                new UsuarioJson(
                        null,
                        TIPO_DONO);

        ResponseEntity<String> response =
                restTemplate.exchange(
                        "/usuarios/{id}",
                        HttpMethod.PUT,
                        new HttpEntity<>(update),
                        String.class,
                        99999L);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    // ======================================================
    // Helpers
    // ======================================================

    private UsuarioEntity criarUsuario() {

        UsuarioEntity usuario = new UsuarioEntity(
                null,
                "João",
                "joao@teste.com",
                "123456",
                TIPO_CLIENTE);

        return usuarioRepository.save(usuario);
    }
}